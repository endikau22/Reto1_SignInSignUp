
package user;

import java.io.Serializable;

/**
 * Representa los distintos estados de un cliente. 
 * @since 23/10/2020
 * @version 1.0
 * @author Eneko, Endika, Markel
 */
public enum UserStatus implements Serializable{
    /**
     * Estado usuario habilitado
     */
    ENABLED,
    /**
     * Estado usuario deshabilitado
     */
    DISABLED;
}
