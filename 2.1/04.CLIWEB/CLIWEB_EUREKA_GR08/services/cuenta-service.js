// Account Service for Eureka Bank

const SoapClient = require("./soapclient");

class CuentaService {
    constructor() {
        this.wsdlUrl = "http://localhost:8080/ws_eureka_bank_soap_java/WSCuenta?wsdl";
    }

    async deposit(cuenta, monto, tipo, cd) {
        try {
            console.log(
                `Calling SOAP service: cuenta = ${cuenta}, monto = ${monto}, tipo = ${tipo}, cd = ${cd}`
            );
            const soapClient = new SoapClient(this.wsdlUrl);
            const client = await soapClient.createClient();

            return new Promise((resolve, reject) => {
                client.cuenta({ cuenta, monto, tipo, cd }, (err, result) => {
                    if (err) {
                        console.error("SOAP call failed:", err);
                        return reject(err);
                    }
                    console.log("SOAP response:", result);
                    resolve(result.return);
                });
            });
        } catch (error) {
            console.error("CuentaService deposit error:", error);
            throw error;
        }
    }

    async verDatosCuenta(cuenta) {
        try {
            console.log(`Calling SOAP service: obtenerCuentaPorNumero = ${cuenta}`);
            const soapClient = new SoapClient(this.wsdlUrl);
            const client = await soapClient.createClient();

            return new Promise((resolve, reject) => {
                client.obtenerCuentaPorNumero({ cuenta }, (err, result) => {
                    if (err) {
                        console.error("SOAP call failed:", err);
                        return reject(err);
                    }
                    console.log("SOAP response:", result);
                    resolve(result.return);
                });
            });
        } catch (error) {
            console.error("CuentaService verDatosCuenta error:", error);
            throw error;
        }
    }
}

module.exports = new CuentaService();

