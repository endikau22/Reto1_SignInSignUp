
package worker;

import application.G5SignInSignUpServerApp;
import dao.DaoFactory;
import excepciones.ExcepcionEmailYaExiste;
import excepciones.ExcepcionPasswdIncorrecta;
import excepciones.ExcepcionUserNoExiste;
import excepciones.ExcepcionUserYaExiste;
import interfaz.Signable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import mensaje.Accion;
import mensaje.Mensaje;
import user.User;

/**
 * Clase que extiende de hilo, se comunica a traves del socket con la aplicación Cliente y le da respuesta a las peticiones del 
 * cliente usando el DaoImplentation para realizar consultas en la base de datos.
 * @version 1.0
 * @since 26/10/2020
 * @author Eneko, Endika, Markel
 */
public class Worker extends Thread implements Serializable{
    /**
     * Atributo Logger para rastrear los pasos de ejecución del programa.
     */
    private static final Logger LOGGER = 
            Logger.getLogger("grupog5.signinsignupapplication.servidor.worker.thread");
    /**
     * Un socket. Extremo servidor del socket para comunicar con el lado cliente.
     */
    private Socket socketWorker;

    /**
     * Recoge el atributo Socket.
     * @return El atributo socket de la clase
     */
    public Socket getSocketWorker() {
        return socketWorker;
    }
    /**
     * Asigna un Socket recibido como parámetro al atributo de la clase Socket.
     * @param socketWorker Un socket.
     */
    public void setSocketWorker(Socket socketWorker) {
        this.socketWorker = socketWorker;
    }
   
    /**
     * Método que ejecuta el hilo. Arranca cuando un objeto de la clase ejecuta el método start(). 
     */
    public void run(){
        //Mensaje Logger al acceder al método
        LOGGER.log(Level.INFO, "Método run del hilo de la clase Worker");
        //Añade uno al contador de hilos activos. 
        G5SignInSignUpServerApp.sumarConexiones();
        //Instanciar un objeto Signable. Método estático de la factoría.
        Signable dao = DaoFactory.getSignable();
        //Un usuario para guardar el usuario que leemos en el mensaje.
        User user = new User();
        //Clase que deserializa objetos que se han escrito con ObjectOutputStream enviados a través de un socket.
        ObjectInputStream ois=null;
        //Clase para escribir objetos y enviarlos a traves del socket.
        ObjectOutputStream oos=null;
        //Guartdar en un objeto de tipo Mensaje el objeto recibido.
        Mensaje mensajeRecibido; 
        //Vamos a usar una instancia de mensaje diferente para el reenvio.
        Mensaje mensajeAEnviar = new Mensaje();
        //Try catch capturar los posibles errores cuando hay una consulta en la base de datos.
        try {
            //Inicializar el objeto ObjectInputStream
            ois = new ObjectInputStream(socketWorker.getInputStream());          
            //Leer un mensaje recibido. Castear el objeto a Mensaje. ClassNot FoundException da esto.
            mensajeRecibido = (Mensaje) ois.readObject();
            //Guardar en la variable user el usuario leido en el mensaje.
            user = mensajeRecibido.getUser();
            //Estudiar las distintas opciones del mensaje recibido, hay 3 opciones Signin signup logout
            switch(mensajeRecibido.getAccion()){
                //Caso de que la acción a realizar sea un SignIn
                case SIGNIN:
                    LOGGER.log(Level.INFO, "Recibida petición SignIn");
                    //Llamada al método signIn del Dao. Devuelve un user.
                    user = dao.signIn(user);
                    //Indicar en el mensaje que todo ha salido bien
                    mensajeAEnviar.setAccion(Accion.OK);
                    mensajeAEnviar.setUser(user);
                    break;   
                //Caso de que la acción a realizar sea un SignUp
                case SIGNUP:
                    LOGGER.log(Level.INFO, "Recibida petición SignUp");
                    //Llamada al método signIn del Dao.
                    dao.signUp(user);
                    //Indicar en el mensaje que todo ha salido bien
                    mensajeAEnviar.setAccion(Accion.OK);
                    mensajeAEnviar.setUser(user);
                    break;
                case LOGOUT:
                    LOGGER.log(Level.INFO, "Recibida petición LogOut");
                    //Llamada al método logOut del Dao.
                    dao.logOut(user);
                    //Indicar en el mensaje que todo ha salido bien
                    mensajeAEnviar.setAccion(Accion.OK);                  
                    break;
                default:
                    //Si entra aquí es que la acción del mensaje no era ninguna de las anteriores por lo tanto no hacer nada y es un error enviar de vuelta el mensaje.
                    //Indicar en el mensaje que todo ha salido bien
                    mensajeAEnviar.setAccion(Accion.TIEMPO_EXPIRADO);              
                    break;
            }
        //Tratar las excepciones la base de datos puede lanzar las tres que hemos creado mas las existentes las englobamos con exception que es la padre de todas.
        }catch(ExcepcionPasswdIncorrecta e){
            LOGGER.log(Level.INFO, "Entra al catch de ExceptionPasswordIncorrecta en el worker.");
            //Se ha producido un error indicar en el mensaje
            mensajeAEnviar.setAccion(Accion.PASSWORD_INCORRECTA);
        }catch(ExcepcionUserYaExiste e){
            LOGGER.log(Level.INFO, "Entra al catch de ExceptionUserYaExiste en el worker.");
            //Se ha producido un error indicar en el mensaje
            mensajeAEnviar.setAccion(Accion.USUARIO_YA_EXISTE);
        }catch(ExcepcionUserNoExiste e){
            LOGGER.log(Level.INFO, "Entra al catch de ExceptionUserNoExiste en el worker.");
            //Se ha producido un error indicar en el mensaje
            mensajeAEnviar.setAccion(Accion.USUARIO_NO_EXISTE);
        }catch(ExcepcionEmailYaExiste e){
            LOGGER.log(Level.INFO, "Entra al catch de ExceptionEmailYaExiste en el worker.");
            //Se ha producido un error indicar en el mensaje
            mensajeAEnviar.setAccion(Accion.EMAIL_YA_EXISTE);
        }catch(Exception e){
            //Englobamos SQL, IO, Class ...
            LOGGER.log(Level.INFO, "Entra al catch de Exception en el worker.");
            //Se ha producido un error indicar en el mensaje
            mensajeAEnviar.setAccion(Accion.TIEMPO_EXPIRADO);
        }finally{
            //Ahora enviar el mensaje a la aplicación cliente. Método.
            enviarMensajeVuelta(mensajeAEnviar,oos);  
            //Acaba el hilo 
            G5SignInSignUpServerApp.restarConexiones();
            //Cerrar los Streams
            //Dentro de try catch dan error de IOException
            try{
                if(oos != null) 
                    oos.close();
                if(ois != null)
                    ois.close();               
            }catch(IOException e){
                LOGGER.log(Level.INFO, "Catch cerrando los Output/Input stream.");
            }
        } 
        
    }

    /**
     * Enviar un mensaje a través del socket a laa aplicación cliente.
     * @param mensajeAEnviar Una instancia de mensaje
     * @param oos Una instancia de ObjectOutputStream
     */
    private void enviarMensajeVuelta(Mensaje mensajeAEnviar, ObjectOutputStream oos) {
        try{
            //Clase para escribir objetos. Y enviarlos a traves del socket.
            oos = new ObjectOutputStream(socketWorker.getOutputStream());
           //Escribir en el socket el mensaje que va a ir al socket del cliente.
            oos.writeObject(mensajeAEnviar);
        }catch(IOException e){
             LOGGER.log(Level.INFO, "Catch de entrada salida reenvio de mensaje de servidor a cliente.");
        }
    }
}