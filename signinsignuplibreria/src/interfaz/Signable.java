
package interfaz;

import excepciones.ExcepcionPasswdIncorrecta;
import excepciones.ExcepcionUserNoExiste;
import excepciones.ExcepcionUserYaExiste;
import user.User;

/**
 * Interface que contiene los métodos para autenticar a un usuario en una aplicación.
 * @author Eneko, Endika, Markel
 */
public interface Signable {
    /**
     * Representa una operación de autenticación
     * @param user Un usuario de la aplicación
     * @return Un usuario.
     * @throws java.lang.Exception
     * @throws excepciones.ExcepcionPasswdIncorrecta
     * @throws excepciones.ExcepcionUserNoExiste
     *
     */
    public User signIn(User user) throws Exception,ExcepcionPasswdIncorrecta,ExcepcionUserNoExiste;
    /**
     * Representa una operación de registro.
     * @param user Un usuario de la aplicación
     * @throws excepciones.ExcepcionUserYaExiste
     * @throws java.lang.Exception
     */
    public void signUp(User user) throws Exception,ExcepcionUserYaExiste;
    /**
     * Representa una operación de logout.
     * @param user Un usuario de la aplicación
     * @throws java.lang.Exception
     */
    public void logOut(User user)throws Exception;
}
