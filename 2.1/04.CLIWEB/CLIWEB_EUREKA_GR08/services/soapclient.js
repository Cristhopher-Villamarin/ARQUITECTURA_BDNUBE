// SOAP Client for Eureka Bank services

const soap = require('soap');
const crypto = require('crypto');

class SoapClient {
    constructor(wsdlUrl) {
        this.wsdlUrl = wsdlUrl;
    }

    async createClient() {
        return new Promise((resolve, reject) => {
            const timeout = setTimeout(() => {
                reject(new Error(`SOAP client creation timeout for ${this.wsdlUrl}. Check if the service is running.`));
            }, 5000); 

            soap.createClient(this.wsdlUrl, (err, client) => {
                clearTimeout(timeout);
                if (err) {
                    console.error(`SOAP client creation failed for ${this.wsdlUrl}:`, err.message);
                    reject(new Error(`Cannot connect to SOAP service at ${this.wsdlUrl}. Please ensure the Java service is running on localhost:8080`));
                } else {
                    console.log(`SOAP client created successfully for ${this.wsdlUrl}`);
                    resolve(client);
                }
            });
        });
    }

    static hashPassword(password) {
        return crypto.createHash('sha256').update(password).digest('hex');
    }
}

module.exports = SoapClient;
