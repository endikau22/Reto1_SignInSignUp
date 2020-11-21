
package mensaje;

import java.io.Serializable;
import user.User;

/**
 * Transmite información para la comunicación entre la aplicación cliente y la aplicación servidor.
 * @since 23/10/2020
 * @version 1.0
 * @author Eneko, Endika, Markel
 */
public class Mensaje implements Serializable{
    /**
     * Un usuario
     */
    private User user;
    /**
     * Una acción
     */
    private Accion accion;
    /**
     * Constructor vacío.
     */
    public Mensaje(){
        
    }

    /**
     * Constructor con dos parámetros
     * @param user Un usuario.
     * @param accion Una acción a realizar.
     */
    public Mensaje(User user, Accion accion) {
        this.user = user;
        this.accion = accion;
    }

    /**
     * Recoge el usuario del mensaje.
     * @return El usuario del mensaje
     */
    public User getUser() {
        return user;
    }

    /**
     * Establece el usuario del mensaje.
     * @param user Un usuario del mensaje
     */
    public void setUser(User user) {
        this.user = user;
    }
    /**
     * Recoge la acción del mensaje.
     * @return Una operación a realizar.
     */
    public Accion getAccion() {
        return accion;
    }

    /**
     * Establece la acción del mensaje.
     * @param accion Una acción del mensaje
     */
    public void setAccion(Accion accion) {
        this.accion = accion;
    }
    
    
}
