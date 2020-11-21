
package contenedorMetodos;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.control.TextField;

/**
 * @version 1.0
 * @author Endika, Markel
 */
public class MetodosUtiles {
              
    /**
     * Comprueba que el texto de un campo no tenga espacios intermedios.
     * @param field Un campo de texto.
     * @return Un boolean true si hay espacios en blanco en el texto, false si por el contrario no los hay.
     */
    public static Boolean comprobarEspaciosBlancos(TextField field) {
        //Guardamos valos textField en string sin espacios delante ni detras.
        String textoSinEspacios = field.getText().trim();
        //VAriable de retorno. Inicializar a false
        Boolean textoCorrecto = true;
        //ForEach de character. Recorremos letra a letra
        for(Character t:textoSinEspacios.toCharArray()){
            //Condición de igualdad. Propiedad equals de Character. Si el caracter actual igual a espacio.
            if(t.equals(' '))
                textoCorrecto = false;
        }
        return textoCorrecto;
    }
           
      /**
     * Comprueba que el texto de un campo no tenga más caracteres que el Integer pasado como parámetro.
     * @param field Un campo de texto.
     * @param max Un entero que indica el número máximo de caracteres permitidos en el textfield
     * @return Un boolean true si contiene los caracteres deseados, false si los caracteres superan el preestablecido.
     */
    public static Boolean maximoCaracteres(TextField field,Integer max) {
        //booleano iniciado a true. Por defecto cumple la condición
        Boolean numeroCaracteresCorrectos = true;
        //si el texto del textfield quitados los espacios delante y detrás su longitud mayor a lo preestablecido error
        if(field.getText().trim().length()>max){
            //booleana a false
                numeroCaracteresCorrectos = false;
        }
        return numeroCaracteresCorrectos;
    }
    
    /**
     * Comprueba que el texto de un campo no tenga menos caracteres que el Integer pasado como parámetro.
     * @param field Un campo de texto.
     * @param min Un entero que indica el número mínimo de caracteres permitidos en el textfield
     * @return Un boolean true si contiene los caracteres deseados, false si los caracteres son menos que el preestablecido.
     */
    public static Boolean minimoCaracteres(TextField field, Integer min) {
        //booleano iniciado a true.
        Boolean numeroCaracteresCorrectos = true;
        if(field.getText().trim().length()< min){
                numeroCaracteresCorrectos = false;
        }
        return numeroCaracteresCorrectos;
    }
 /**
     * Comprueba que el String recibido como parámetro cumple las condiciones de un email.
     * @param email El email del campo contraseña.
     * @return Un booleano.  True si la contraseña es correcta.
     */
    public static boolean emailCorrecto(String email) {
        //un patrón de email válido.
        String regex =  "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regex);
        //Comprueba que el pattern del patron coincide con el email introducido.
        Matcher matcher = pattern.matcher(email);
        //Devuelve un booleano el metodo matches de la clase matcher
        return  matcher.matches();
    }
}
