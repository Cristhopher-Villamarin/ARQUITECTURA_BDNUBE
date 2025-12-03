using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CLIESC_EUREKA_REST_DOT_GR08.ec.edu.monster.model
{
    public class CuentaModel
    {
        public string ChrCuenCodigo { get; set; }
        public string ChrMoneCodigo { get; set; }
        public string ChrSucucodigo { get; set; }
        public string ChrEmplCreaCuenta { get; set; }
        public string ChrClieCodigo { get; set; }
        public double DecCuenSaldo { get; set; }
        public System.DateTime? DttCuenFechaCreacion { get; set; }
        public string VchCuenEstado { get; set; }
        public int IntCuenContMov { get; set; }
        public string ChrCuenClave { get; set; }
    }
}

