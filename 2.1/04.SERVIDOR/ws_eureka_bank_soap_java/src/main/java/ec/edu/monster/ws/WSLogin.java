package ec.edu.monster.ws;

import ec.edu.monster.servicio.LoginService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService; 

@WebService(serviceName = "WSLogin")
public class WSLogin {

    @WebMethod(operationName = "auth")
    public boolean auth(@WebParam(name = "username") String username, @WebParam(name = "password") String password) {
        LoginService service = new LoginService();
        boolean resultado = service.login(username, password);
        return resultado;
    }
}
