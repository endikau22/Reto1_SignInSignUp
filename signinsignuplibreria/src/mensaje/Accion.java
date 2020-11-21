
package mensaje;

/**
 * Las posibles acciones de la aplicación. Cuando cliente y servidor se comunican a través de mensajes estas acciones 
 * son las que indican la operación a realizar.
 * @since 23/10/2020
 * @version 1.0
 * @author Eneko,Endika,Markel
 */
public enum Accion {
    /**
     * Acción SignIn
     */
    SIGNIN, 
    /**
     * Acción SignUp
     */
    SIGNUP, 
    /**
     * Acción de LogOut
     */
    LOGOUT, 
    /**
     * Operación exitosa
     */
    OK, 
    /**
     * Contraseña incorrecta
     */
    PASSWORD_INCORRECTA,
    /**
     * Usuario no existe en una operación de signin
     */
    USUARIO_NO_EXISTE, 
    /**
     * Usuario ya existe en una operación de signup
     */
    USUARIO_YA_EXISTE, 
    /**
     * tiempo expirado en una operación.
     */
    TIEMPO_EXPIRADO,
    /**
     * Error al conectarse a la base de datos.
     */
    ERROR_BASEDEDATOS,
    /**
     * Error al conectarse al servidor.
     */
    ERROR_SERVIDOR,
    /**
     *Mensaje que informa que la contraseña existe en la base de datos.
     */
    EMAIL_YA_EXISTE
}
