package ec.edu.monster.service;

import android.util.Log;

import ec.edu.monster.model.MovimientoModel;
import ec.edu.monster.util.SoapHelper;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Parser SOAP con XmlPullParser y control por profundidad.
 * - No asume nombre del contenedor del ítem.
 * - No depende del orden de los campos.
 * - No aplica reglas especiales (importe siempre el del tag <importeMovimiento>).
 */
public class MovimientoService {

    private static final String TAG = "MovimientoService";

    public List<MovimientoModel> obtenerMovimientos(String cuenta) {
        List<MovimientoModel> out = new ArrayList<>();
        try {
            String soapBody = "<numcuenta>" + cuenta + "</numcuenta>";
            String response = SoapHelper.callWebService("WSMovimiento", "consultarMovimientos", soapBody);

            if (response == null || response.trim().isEmpty()) {
                Log.d(TAG, "Respuesta vacía para cuenta=" + cuenta);
                return out;
            }

            // 1) Normalizar namespaces y cabecera XML (dejamos solo nombres simples de tag)
            String normalized = response
                    .replaceAll("<(/)?[a-zA-Z0-9]+:", "<$1")       // quita prefijos de namespace
                    .replaceAll("^\\s*<\\?xml[\\s\\S]*?\\?>", "")  // quita declaración XML
                    .trim();

            // Envolvemos en <root> si la respuesta trae múltiples raíces (común en SOAP "flat")
            if (!normalized.startsWith("<root")) {
                normalized = "<root>" + normalized + "</root>";
            }

            Log.d(TAG, "Normalized (primeros 1.5k): " +
                    normalized.substring(0, Math.min(normalized.length(), 1500)));

            // 2) Pull Parser
            XmlPullParserFactory f = XmlPullParserFactory.newInstance();
            f.setNamespaceAware(false);
            XmlPullParser xpp = f.newPullParser();
            xpp.setInput(new StringReader(normalized));

            MovimientoModel cur = null;
            int itemDepth = -1; // profundidad del contenedor del ítem actual
            int event = xpp.getEventType();

            while (event != XmlPullParser.END_DOCUMENT) {
                if (event == XmlPullParser.START_TAG) {
                    String name = xpp.getName();

                    if (isFieldTag(name)) {
                        // Si aún no tenemos item, lo inicializamos "anclado" al padre del campo
                        if (cur == null) {
                            cur = new MovimientoModel();
                            itemDepth = xpp.getDepth() - 1; // el contenedor inmediato del campo
                        }

                        // Leer valor del campo
                        String val = safeNextText(xpp).trim();

                        switch (name) {
                            case "numeroMovimiento":
                                cur.setNumeroMovimiento(parseInt(val));
                                break;
                            case "fechaMovimiento":
                                cur.setFechaMovimiento(val);
                                break;
                            case "codigoTipoMovimiento":
                                cur.setCodigoTipoMovimiento(val);
                                break;
                            case "tipoDescripcion":
                                cur.setTipoDescripcion(val);
                                break;
                            case "importeMovimiento":
                                cur.setImporteMovimiento(parseDecimal(val));
                                break;
                            case "cuentaReferencia":
                                cur.setCuentaReferencia(val);
                                break;
                            case "saldo":
                                cur.setSaldo(parseDecimal(val));
                                break;
                        }

                        // Ojo: usamos nextText() que ya avanza el cursor al END_TAG de este campo,
                        // así que no debemos hacer nada más en este START_TAG.

                        // Forzamos a que el "event" continúe en el loop ya actualizado por nextText()
                        event = xpp.getEventType();
                        continue; // evita que caiga en el manejo genérico de START_TAG al final
                    }

                } else if (event == XmlPullParser.END_TAG) {
                    // Si cerramos el contenedor del ítem actual, lo agregamos (si es válido)
                    if (cur != null && xpp.getDepth() == itemDepth) {
                        if (cur.getNumeroMovimiento() != 0) {
                            out.add(cur);
                        } else {
                            // descartamos si no tiene numeroMovimiento (no es un ítem válido)
                            Log.d(TAG, "Descartado bloque sin numeroMovimiento en depth=" + itemDepth);
                        }
                        cur = null;
                        itemDepth = -1;
                    }
                }

                event = xpp.next();
            }

            Log.d(TAG, "Movimientos parseados: " + out.size());
            if (!out.isEmpty()) {
                MovimientoModel first = out.get(0);
                Log.d(TAG, "Primer mov parseado -> nro="
                        + first.getNumeroMovimiento()
                        + " tipo=" + first.getCodigoTipoMovimiento()
                        + " desc=" + first.getTipoDescripcion()
                        + " imp=" + first.getImporteMovimiento()
                        + " saldo=" + first.getSaldo());
            }

        } catch (Exception e) {
            Log.e(TAG, "Error al obtener movimientos", e);
        }

        return out;
    }

    // ==========================================================
    // Helpers
    // ==========================================================

    /** Indica si el tag es uno de los campos del movimiento. */
    private boolean isFieldTag(String name) {
        return "numeroMovimiento".equalsIgnoreCase(name)
                || "fechaMovimiento".equalsIgnoreCase(name)
                || "codigoTipoMovimiento".equalsIgnoreCase(name)
                || "tipoDescripcion".equalsIgnoreCase(name)
                || "importeMovimiento".equalsIgnoreCase(name)
                || "cuentaReferencia".equalsIgnoreCase(name)
                || "saldo".equalsIgnoreCase(name);
    }

    /** Similar a nextText() pero seguro ante nulos y espacios. */
    private String safeNextText(XmlPullParser xpp) throws Exception {
        String t = xpp.nextText();
        return (t == null) ? "" : t;
    }

    private int parseInt(String s) {
        try {
            if (s == null) return 0;
            s = s.trim();
            if (s.isEmpty()) return 0;
            return Integer.parseInt(s);
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * Parser decimal robusto:
     * - "1.800,00" → 1800.00
     * - "1,800.00" → 1800.00
     * - "1800,00"  → 1800.00
     * - "1800.00"  → 1800.00
     * - "1800"     → 1800.00
     * - Se ignoran símbolos extraños.
     */
    private double parseDecimal(String raw) {
        if (raw == null) return 0.0;
        String s = raw.trim();

        // Eliminar cualquier símbolo no numérico salvo separadores
        // (por si viniera con $ o espacios duros)
        s = s.replaceAll("[^0-9,\\.]", "");

        int lastComma = s.lastIndexOf(',');
        int lastDot   = s.lastIndexOf('.');

        if (lastComma != -1 && lastDot != -1) {
            if (lastComma > lastDot) {
                // decimal = ',' → quitar puntos
                s = s.replace(".", "").replace(',', '.');
            } else {
                // decimal = '.' → quitar comas
                s = s.replace(",", "");
            }
        } else if (lastComma != -1) {
            s = s.replace(".", ""); // puntos eran miles
            s = s.replace(',', '.');
        } else {
            s = s.replace(",", ""); // comas eran miles
        }

        try {
            return Double.parseDouble(s);
        } catch (Exception e) {
            return 0.0;
        }
    }
}
