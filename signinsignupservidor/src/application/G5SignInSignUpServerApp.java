package application;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import worker.Worker;
import worker.WorkerLimiteSuperado;

/**
 * Aplicación del servidor SignInSignUp. Que pone en funcionamiento el servidor y este se quedara escuchando. 
 *
 * @version 1.0
 * @since 26/10/2020
 * @author Eneko, Endika, Markel
 */
public class G5SignInSignUpServerApp {

    /**
     * Atributo Logger para rastrear los pasos de ejecución del programa.
     */
    private static final Logger LOGGER
            = Logger.getLogger("grupog5.signinsignupapplication.servidor.application");
    /**
     * Leer los datos de un fichero properties.
     */
    private static final ResourceBundle FICHERO = ResourceBundle.getBundle("poolConexion.datosconexionbasededatos");
    /**
     * Indica el número máximo de conexiones posibles al servidor. El dato está
     * guardado en un fichero de propiedades en el paquete poolConexion.
     */
    private static final Integer NUMERO_CONEXIONES_MAXIMAS = Integer.parseInt(FICHERO.getString("NumeroConexionesMaximas"));
    /**
     * Número de conexiones activas, controlar que no superen las máximas
     * preestablecidas.
     */
    private static Integer conexionesActuales = 0;

    /**
     * Numero de puerto libre para la comunicación con el servidor por medio de
     * un socket.
     */
    private static final Integer NUMERO_PUERTO
            = Integer.parseInt(ResourceBundle.getBundle("socket.infoSocket").getString("portNumber"));

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        //Mensaje Logger al acceder al método
        LOGGER.log(Level.INFO, "Método main de la aplicación sevidor.");
        // TODO code application logic here
        //Declaración de un Server socket para atender las peticiones del cliente
        ServerSocket socketServidor;
        //Inicialización del serverSocket indicando puerto por el que escucha
        socketServidor = new ServerSocket(NUMERO_PUERTO);
        //Bucle infinito, el servidor atiende todas las conexiones del lado cliente
        while (true) {
            LOGGER.log(Level.INFO, "Bucle infinito del servidor atendiendo consultas de clientes.");
            //Si hay conexiones disponibles.
            //Acepta el server socket una conexión y traslada la atención de esa conexión a un socket.
            Socket unSocket = socketServidor.accept();
            //Si no se han cumplido el número de consexiones al servidor preestablecidas en la constante.
            if (NUMERO_CONEXIONES_MAXIMAS > conexionesActuales) {
                //Sumar la conexión actual al atributo que controla las conexiones activas.
                conexionesActuales++;
                //Crear el hilo.
                Worker worker = new Worker();
                //Pasar el socket a la clase hilo para que que se comunique con la aplicación cliente
                worker.setSocketWorker(unSocket);
                //Iniciar ejecución del Hilo
                worker.start();
            } else {//El número de conexiones al servidor se han sobrepasado.
                //Inicializar una instancia de la clase workerLimiteSuperado.
                WorkerLimiteSuperado workerSuperado = new WorkerLimiteSuperado();
                //Enviar el socket al hilo para que este lo guarde en su atributo socket.
                workerSuperado.setSocket(unSocket);
                //Arranca el funcionamiento del hilo.
                workerSuperado.start();
                try {
                    //Esperar a que el hilo termine para seguir.
                    workerSuperado.join();
                } catch (InterruptedException ex) {
                    LOGGER.log(Level.SEVERE, "Error de interrupción del hilo worker limite superado.");
                }
            }

        }
    }
    
    /**
     * Actualiza el valor de la variable estática conexionesActuales cuando un hilo acaba.
     */
    public static synchronized void sumarConexiones() {
        //Hacer asi para que se pueda hacer esto desde el mismo hilo. Es estático para que se acceda através de la clase, sin instancias.
        conexionesActuales++;
    }

    /**
     * Actualiza el valor de la variable estática conexionesActuales cuando un hilo acaba.
     */
    public static synchronized void restarConexiones() {
        conexionesActuales--;
    }

}
