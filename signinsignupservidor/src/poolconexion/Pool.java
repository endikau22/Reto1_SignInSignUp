
package poolConexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Pool de conexión con la base de datos.
 * @version 1.0
 * @since 30/10/2020
 * @author Eneko, Endika, Markel
 */
public class Pool {
    /**
     * Atributo Logger para rastrear los pasos de ejecución del programa.
     */
    private static final Logger LOGGER = 
            Logger.getLogger("grupog5.signinsignupapplication.servidor.pooldeconexiones");
    /**
     * Un objeto pool statico de la clase, solo existirá un pool. Este atributo me asegura que solo se crea uno.
     */
    private static  Pool unPool;
    /**
     * Una pila que contendrá las conexiones al pool.
     */
    private Stack pilaContenedoraConexiones;
    /**
     * Una conexión a la BBDD.
     */
    private Connection con = null;

    /**
     * Constructor privado, solo accesible dentro de la clase. Inicializa la pila.
     */
    private Pool(){
        //Se supone que solo entra una vez aqui. Veremos.
        LOGGER.log(Level.INFO, "Método constructor del pool");
        //Inicializar la pila
        pilaContenedoraConexiones = new Stack();
    }
    /**
     * Accede al constructor. Es estático para que no se acceda con objetos se accede a traves de la clase
     * @return Una instacia del pool. Menos la primera vez nunca entra en el constructor.
     */
    public static Pool getPool(){
        LOGGER.log(Level.INFO, "Método getPool del pool. Solo una instancia");
        if(Pool.unPool==null)
            unPool = new Pool();
        return unPool;
    }
    
    /**
     * Recoge una conexión de la pila. Utilizando el método pop de la clase Stack (pila).
     * Está sincronizado para que no accedan varios hilos a la vez al método.
     * @return Una conexion.
     * @throws java.lang.Exception
     */
    public synchronized Connection getConnection ()throws Exception{
        //Método del pool que el dao le pide una conexión
        LOGGER.log(Level.INFO, "Método getConnection del pool");
        //Pedir una conexión a la pila si está vacá añadir una.
        if(pilaContenedoraConexiones.isEmpty()){
            //Abrir una conexión.
            this.openConnection();
            //Introducir una conexión en la pila.
            pilaContenedoraConexiones.push(con);
        }
       //Retorna una conexión de la pila. La sacade la pila con pop.
       return (Connection) this.pilaContenedoraConexiones.pop();
    }
    
    /**
     * Devuelve una conexión a la pila con el método push de la clase Stack (Pila).
     * Está sincronizado para que no accedan varios hilos a la vez al método.
     */   
    public synchronized void freeConnection (){
        LOGGER.log(Level.INFO, "Método freeConnection del pool. Liberar conexión, devolver a la pila");
       //Introduce una conexión en la pila.
       this.pilaContenedoraConexiones.push(con);
    }
    /**
     * Abre una conexión DriverManager con la base de datos.
     * @throws Exception 
     */
    private void openConnection() throws Exception {
        LOGGER.log(Level.INFO, "Método openConnection del pool.");
        //Asocia el fichero de propiedades con el objeto de la clase Resource Bundle, clase que lee String del gichero de propiedades.
        ResourceBundle fichero = ResourceBundle.getBundle("poolConexion.datosconexionbasededatos");
        //Guardar la información del fichero de propiedades en os atributos de la clase.
        String url = fichero.getString("Conn");
        String user = fichero.getString("DBUser");
        String passwd = fichero.getString("DBPass");
        String driver = fichero.getString("Driver");
        
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url,user,passwd);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Pool.class.getName()).log(Level.SEVERE, null, ex);
            LOGGER.log(Level.INFO, "Catch del OpenConnection().");
            throw  new Exception();
        }
        
    }
}
