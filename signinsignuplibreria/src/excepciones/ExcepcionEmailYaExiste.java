
package excepciones;

/**
 * Excepción que señala que el email está registrado en la BBDD.
 * @version 1.0
 * @since 23/10/2020
 * @author Eneko, Endika, Markel
 */
public class ExcepcionEmailYaExiste extends Exception{
    
    public ExcepcionEmailYaExiste (){
        super("Email ya existe.");
    }
    
}
