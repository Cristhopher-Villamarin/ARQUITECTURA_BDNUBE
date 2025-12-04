const SoapClient = require('./soapclient');

class MovementService {
    constructor() {
        this.wsdlUrl = 'http://localhost:8080/ws_eureka_bank_soap_java/WSMovimiento?wsdl';
        this.namespace = 'http://ws.monster.edu.ec/';
        this.methodName = 'consultarMovimientos';
    }

    async getMovimientos(accountNumber) {
        try {
            const soapClient = new SoapClient(this.wsdlUrl);
            const client = await soapClient.createClient();

            return new Promise((resolve, reject) => {
                const args = {
                    numcuenta: accountNumber
                };

                const possibleMethods = ['consultarMovimientos', 'obtenerMovimientos', 'getMovimientos', 'hello', 'movimientos'];
                let methodFound = false;

                for (const method of possibleMethods) {
                    if (typeof client[method] === 'function') {
                        console.log(`Using SOAP method: ${method}`);
                        methodFound = true;

                        client[method](args, (err, result) => {
                            if (err) {
                                console.error('SOAP Request Error:', err);
                                return reject(err);
                            }

                            try {
                                console.log('SOAP Response:', result);

                                if (!result || !result.return) {
                                    console.log('No movements found for account:', accountNumber);
                                    return resolve([]);
                                }

                                const movements = Array.isArray(result.return) ? result.return : [result.return];

                                const processedMovements = movements.map(movement => {
                                    const importe = parseFloat(movement.importeMovimiento || movement.importe || 0);
                                    const tipoDescripcion = (movement.tipoDescripcion || movement.tipo || '').toLowerCase();

                                    const esCredito = tipoDescripcion.includes('deposito') ||
                                        tipoDescripcion.includes('depósito') ||
                                        tipoDescripcion.includes('ingreso') ||
                                        tipoDescripcion.includes('abono') ||
                                        tipoDescripcion.includes('transferencia recibida') ||
                                        tipoDescripcion.includes('transferencia entrada') ||
                                        tipoDescripcion.includes('transferencia de entrada');

                                    const esDebito = tipoDescripcion.includes('retiro') ||
                                        tipoDescripcion.includes('extraccion') ||
                                        tipoDescripcion.includes('extracción') ||
                                        tipoDescripcion.includes('debito') ||
                                        tipoDescripcion.includes('débito') ||
                                        tipoDescripcion.includes('transferencia enviada') ||
                                        tipoDescripcion.includes('transferencia salida') ||
                                        tipoDescripcion.includes('transferencia de salida');

                                    const esTransferenciaGenerica = tipoDescripcion.includes('transferencia') &&
                                        !tipoDescripcion.includes('recibida') &&
                                        !tipoDescripcion.includes('entrada') &&
                                        !tipoDescripcion.includes('enviada') &&
                                        !tipoDescripcion.includes('salida');

                                    let esCreditoFinal;
                                    if (esTransferenciaGenerica) {
                                        const tieneReferencia = movement.cuentaReferencia && movement.cuentaReferencia.trim() !== '';
                                        const cuentaActual = movement.codigoCuenta || movement.cuenta || accountNumber;
                                        const esCuentaOrigen = cuentaActual === accountNumber;

                                        esCreditoFinal = !tieneReferencia && !esCuentaOrigen;
                                        console.log(`Transferencia genérica - Cuenta: ${cuentaActual}, Consultando: ${accountNumber}, Referencia: ${movement.cuentaReferencia}, EsCuentaOrigen: ${esCuentaOrigen}, EsCrédito: ${esCreditoFinal}`);
                                    } else {
                                        esCreditoFinal = esDebito ? false : (esCredito || importe >= 0);
                                    }
                                    const accion = esCreditoFinal ? 'Crédito' : 'Débito';

                                    // Para transferencias, agregar " - Débito" o " - Crédito" al tipo
                                    let tipoMovimiento = movement.tipoDescripcion || movement.tipo || 'Movimiento';
                                    if (tipoMovimiento.toLowerCase().includes('transferencia')) {
                                        tipoMovimiento = `Transferencia - ${accion}`;
                                    }

                                    return {
                                        cuenta: movement.codigoCuenta || movement.cuenta || accountNumber,
                                        fecha: movement.fechaMovimiento || movement.fecha || new Date().toISOString().split('T')[0],
                                        numero: movement.numeroMovimiento || movement.numero || 0,
                                        tipo: tipoMovimiento,
                                        accion: accion,
                                        importe: esCreditoFinal ? importe : -importe,
                                        saldo: parseFloat(movement.saldo || 0)
                                    };
                                });

                                for (let i = 0; i < processedMovements.length; i++) {
                                    const current = processedMovements[i];
                                    const next = processedMovements[i + 1];

                                    if (next && isFinite(current.saldo) && isFinite(next.saldo)) {
                                        const esCreditoSegunSaldo = current.saldo > next.saldo;
                                        const signoDebeSerPositivo = esCreditoSegunSaldo;

                                        current.accion = signoDebeSerPositivo ? 'Crédito' : 'Débito';
                                        current.importe = Math.abs(current.importe) * (signoDebeSerPositivo ? 1 : -1);
                                    }
                                }

                                resolve(processedMovements);
                            } catch (parseError) {
                                console.error('Result Parsing Error:', parseError);
                                reject(parseError);
                            }
                        });
                        break;
                    }
                }

                if (!methodFound) {
                    console.error('No valid SOAP method found. Available methods:', Object.keys(client));
                    reject(new Error('No valid SOAP method found for movements'));
                }
            });
        } catch (error) {
            console.error('Account Movements Error:', error);
            throw error;
        }
    }
}

module.exports = new MovementService();
