package ec.edu.monster.servicio;

public class LoginService {

    public boolean login(String username, String password) {
        if ((username.equals("MONSTER") && password.equals("MONSTER9"))
                || (username.equals("admin") && password.equals("admin"))) {
            return true;
        }
        return false;
    }
}
