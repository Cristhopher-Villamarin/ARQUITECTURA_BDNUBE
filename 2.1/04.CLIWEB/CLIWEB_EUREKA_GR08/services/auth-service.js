// Authentication Service for Eureka Bank

const SoapClient = require('./soapclient');

class AuthService {
    constructor() {
        this.wsdlUrl = 'http://localhost:8080/ws_eureka_bank_soap_java/WSLogin?wsdl';
    }

    async authenticate(username, password) {
        try {
            const soapClient = new SoapClient(this.wsdlUrl);
            const client = await soapClient.createClient();

            const hashedPassword = SoapClient.hashPassword(password);

            return new Promise((resolve, reject) => {
                client.auth({ username, password }, (err, result) => {
                    if (err) reject(err);
                    resolve(result.return);
                });
            });
        } catch (error) {
            console.error('Authentication Error:', error);
            throw error;
        }
    }
}

module.exports = new AuthService();

