
package user;

import java.io.Serializable;

/**
 * Contiene los distintos privilegios de un usuario.
 * @since 23/10/2020
 * @version 1.0
 * @author Eneko, Endika, Markel
 */
public enum UserPrivilege implements Serializable{
    /**
     * Privilegios de usuario.
     */
    USER,
    /**
     * Privilegios de administrador.
     */
    ADMIN
}
