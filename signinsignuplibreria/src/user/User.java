
package user;

import java.io.Serializable;
import java.util.Date;

/**
 * Representa un usuario de la aplicación.
 * @since 23/10/2020
 * @version 1.0
 * @author Eneko, Endika, Markel
 */
public class User implements Serializable{
    /**
     * Identificativo del usuario en la aplicación
     */
    private Integer id;
    /**
     * Nombre en la aplicación del usuario
     */
    private String login;
    /**
     * Email del usuario
     */
    private String email;
    /**
     * Nombre del usuario
     */
    private String fullName;
    /**
     * Estado del usuario en la aplicación
     */
    private UserStatus status = UserStatus.ENABLED;
    /**
     * Privilegios del usuario en la aplicación
     */
    private UserPrivilege privilege = UserPrivilege.USER;
    /**
     * Contraseña del usuario
     */
    private String password;
    /**
     * Fecha de último acceso
     */
    private Date lastAccess;
    /**
     * Fecha de último cambio de contraseña
     */
    private Date lastPasswordChange;
    /**
     * Constructor vacío.
     */
    public User(){
        
    }
    /**
     * Constructor que recibe 2 parametros de la Ventana Sign In
     * @param login String que identifica un usuario en la base de datos.
     * @param password String que contiene la contraseña del usuario.
     */
    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    /**
     * Constructor que recibe 4 parametros de la Ventana Sign Up
     * @param login String que identifica un usuario en la base de datos.
     * @param email String que contiene el email del usuario.
     * @param fullName String que contiene el nombre del usuario
     * @param password String que contiene la contraseña del usuario
     */
    public User(String login, String email, String fullName, String password) {
        this.login = login;
        this.email = email;
        this.fullName = fullName;
        this.password = password;
        status = UserStatus.ENABLED;
        privilege = UserPrivilege.USER;
    }

    /**
     * Recoge el nombre del usuario en la aplicación.
     * @return el login
     */
    public String getLogin() {
        return login;
    }

    /**
     * Asigna el nombre de usuario
     * @param login El nombre de usuario
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Recoge el mail del usuario
     * @return El email del usuario
     */
    public String getEmail() {
        return email;
    }

    /**
     * Asigna el mail del usuario.
     * @param email El email del usuario
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Recoge el nombre del usuario
     * @return El nombre del usuario
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Registra el nombre del usuario
     * @param fullName El nombre del usuario
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * Recoge el status del usuario
     * @return El status del usuario
     */
    public UserStatus getStatus() {
        return status;
    }

    /**
     * Asigna el status del usuario
     * @param status El status del usuario
     */
    public void setStatus(UserStatus status) {
        this.status = status;
    }

    /**
     * Recoge los privilegios de usuario
     * @return Los privilegios de usuario
     */
    public UserPrivilege getPrivilege() {
        return privilege;
    }

    /**
     * Asigna los privilegios de usuario
     * @param privilege Los privilegios de usuario
     */
    public void setPrivilege(UserPrivilege privilege) {
        this.privilege = privilege;
    }

    /**
     * Recoge la contraseña del usuario
     * @return La contraseña del usuario
     */
    public String getPassword() {
        return password;
    }

    /**
     * Asigna la contraseña del usuario
     * @param password La contraseña del usuario
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Recoge la fecha del último acceso del usuario
     * @return La fecha del último acceso del usuario
     */
    public Date getLastAccess() {
        return lastAccess;
    }

    /**
     * Asigna la fecha del último acceso del usuario
     * @param lastAccess La fecha del último acceso del usuario
     */
    public void setLastAccess(Date lastAccess) {
        this.lastAccess = lastAccess;
    }

    /**
     * Recoge la fecha del último cambio de contraseña
     * @return Fecha del último cambio de conraseña.
     */
    public Date getLastPasswordChange() {
        return lastPasswordChange;
    }

    /**
     * Asigna la fecha del último cambio de contraseña
     * @param lastPasswordChange Fecha del último cambio de conraseña.
     */
    public void setLastPasswordChange(Date lastPasswordChange) {
        this.lastPasswordChange = lastPasswordChange;
    }  
}


