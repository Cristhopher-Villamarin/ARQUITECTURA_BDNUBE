
package ec.edu.monster.service;

import ec.edu.monster.ws.WSLogin;
import ec.edu.monster.ws.WSLogin_Service;

public class LoginService {
     public boolean auth(String username, String password){
        WSLogin_Service service = new WSLogin_Service();
        WSLogin port = service.getWSLoginPort();
        return port.auth(username, password);
    }
}
