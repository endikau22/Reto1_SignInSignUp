/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import application.G5SigninSignUpClientapp;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;
import static org.testfx.matcher.base.NodeMatchers.isDisabled;
import static org.testfx.matcher.base.NodeMatchers.isEnabled;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import  org.testfx.matcher.control.TextInputControlMatchers;
import  org.testfx.matcher.control.LabeledMatchers;

/**
 * Contiene los métodos de prueba de la escena SignUp controlada por la clase FXMLDocumentSignUpController.
 * @author Endika Ubierna Lopez.
 * @version 2.0
 * @since 11/11/2020
 */
 @FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FXMLDocumentSignUpControllerIT extends ApplicationTest{
  
     /**
     * Starts application to be tested. Se ejecuta antes de cada método de test.
     * @param stage Primary Stage object
     * @throws Exception If there is any error
     */
    @Override public void start(Stage stage) throws Exception {
       new G5SigninSignUpClientapp().start(stage);
    }
    
    /**
     * Constructor de la clase.
     */
    public FXMLDocumentSignUpControllerIT() {
    }
    
    /**
     * Test para comprobar que todos los campos estan habilitados menos el boton
     */
    
    @Test
    public void testA_InicioVentana() {
        clickOn("#hplRegistrate");
        verifyThat("#txtFieldNombre", TextInputControlMatchers.hasText(""));
        verifyThat("#txtFieldUsuario", TextInputControlMatchers.hasText(""));
        verifyThat("#txtFieldEmail", TextInputControlMatchers.hasText(""));
        verifyThat("#pswFieldContrasena",TextInputControlMatchers.hasText(""));
        verifyThat("#pswFieldRepetirContrasena",TextInputControlMatchers.hasText(""));
        verifyThat("#btnCrearCuenta", isDisabled());

    }
    
    /**
     * Test para comprobar que el hyperlynk de vuelta esta habilitado y funcionando 
     */
    
    @Test
    public void testB_HyperlinkInicioSesion() {
        clickOn("#hplRegistrate");
        clickOn("#hplIniciaSesion");
        verifyThat("#borderPaneIniciarSesion", isVisible());
    }
    
    /**
     * Test para comprobar que el boton esta desabilitado hasta que esten metidos todos los datos
     */
    
    @Test
    public void testC_BotonCrearCuentaDesabilitado() {
        clickOn("#hplRegistrate");
        clickOn("#txtFieldNombre");
        write("username");
        verifyThat("#btnCrearCuenta", isDisabled());
        eraseText(8);
        clickOn("#txtFieldUsuario");
        write("user");
        verifyThat("#btnCrearCuenta", isDisabled());
        eraseText(8);
        clickOn("#txtFieldEmail");
        write("username@gmail.com");
        verifyThat("#btnCrearCuenta", isDisabled());
        eraseText(18);
        clickOn("#pswFieldContrasena");
        write("username");
        verifyThat("#btnCrearCuenta", isDisabled());
        eraseText(8);
        clickOn("#pswFieldRepetirContrasena");
        write("username");
        verifyThat("#btnCrearCuenta", isDisabled());
        eraseText(8);
        verifyThat("#btnCrearCuenta", isDisabled());
    }
    
    /**
     * Test para comprobar que el boton se habilitara cuando se metan todos los datos
     */
    
    @Test
    public void testD_BotonCrearCuentaHabilitado() {
        clickOn("#hplRegistrate");
        clickOn("#txtFieldNombre");
        write("username");
        clickOn("#txtFieldUsuario");
        write("user");
        clickOn("#txtFieldEmail");
        write("username@gmail.com");
        clickOn("#pswFieldContrasena");
        write("username");
        clickOn("#pswFieldRepetirContrasena");
        write("username");
        verifyThat("#btnCrearCuenta", isEnabled());
        
    }
    
    /**
     * Test para comprobar que todos los datos son correctos y que pasa a la venta de logout 
     */
    
    @Test
    public void testE_TodoCorrecto() {
        clickOn("#hplRegistrate");
        clickOn("#txtFieldNombre");
        write("username");
        clickOn("#txtFieldUsuario");
        write("username99");
        clickOn("#txtFieldEmail");
        write("username99@gmail.com");
        clickOn("#pswFieldContrasena");
        write("abcd");
        clickOn("#pswFieldRepetirContrasena");
        write("abcd");
        clickOn("#btnCrearCuenta");
        verifyThat("Aceptar", NodeMatchers.isVisible());
        clickOn("Aceptar");
        verifyThat("#borderPaneIniciarSesion", isVisible());
    }
    
    /**
     * Test para comprobar que String del campo Nombre es muy corto.
     */
    
    @Test
    public void testF_NombreMuyCorto() {
        clickOn("#hplRegistrate");
        clickOn("#txtFieldNombre");
        write("u");
        clickOn("#txtFieldUsuario");
        write("user");
        clickOn("#txtFieldEmail");
        write("username@gmail.com");
        clickOn("#pswFieldContrasena");
        write("username");
        clickOn("#pswFieldRepetirContrasena");
        write("username");
        clickOn("#btnCrearCuenta");
        verifyThat("#lblMensajeError",LabeledMatchers.hasText("El campo nombre no es válido."));
    }
    
    /**
     * Test para comprobar que String del campo Nombre es muy larga.
     */
    
    @Test
    public void testG_NombreMuyLargo() {
        clickOn("#hplRegistrate");
        clickOn("#txtFieldNombre");
        write("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        clickOn("#txtFieldUsuario");
        write("user");
        clickOn("#txtFieldEmail");
        write("username@gmail.com");
        clickOn("#pswFieldContrasena");
        write("username");
        clickOn("#pswFieldRepetirContrasena");
        write("username");
        clickOn("#btnCrearCuenta");
        verifyThat("#lblMensajeError",LabeledMatchers.hasText("El campo nombre no es válido."));
    }
       
    /**
     * Test para comprobar que existe un usuario con el mismo nombre
     */
    
    @Test
    public void testK_UsuarioYaExiste() {
        clickOn("#hplRegistrate");
        clickOn("#txtFieldNombre");
        write("username");
        clickOn("#txtFieldUsuario");
        write("endika22");
        clickOn("#txtFieldEmail");
        write("username@gmail.com");
        clickOn("#pswFieldContrasena");
        write("username");
        clickOn("#pswFieldRepetirContrasena");
        write("username");
        clickOn("#btnCrearCuenta");
        verifyThat("#lblMensajeError",LabeledMatchers.hasText("El usuario ya existe."));
    }
    
    /**
     * Test para comprobar que el email no es valido
     */
    
    @Test
    public void testL_EmailNoValido() {
        clickOn("#hplRegistrate");
        clickOn("#txtFieldNombre");
        write("username");
        clickOn("#txtFieldUsuario");
        write("user");
        clickOn("#txtFieldEmail");
        write("useremail");
        clickOn("#pswFieldContrasena");
        write("username");
        clickOn("#pswFieldRepetirContrasena");
        write("username");
        clickOn("#btnCrearCuenta");
        verifyThat("#lblMensajeError",LabeledMatchers.hasText("El campo email no es válido."));
    }
    
    
    /**
     * Test para comprobar que String del campo contraseña es muy corto.
     */
    
    @Test
    public void testN_ContraseñaMuyCorto() {
        clickOn("#hplRegistrate");
        clickOn("#txtFieldNombre");
        write("username");
        clickOn("#txtFieldUsuario");
        write("user");
        clickOn("#txtFieldEmail");
        write("username@gmail.com");
        clickOn("#pswFieldContrasena");
        write("u");
        clickOn("#pswFieldRepetirContrasena");
        write("u");
        clickOn("#btnCrearCuenta");
        verifyThat("#lblMensajeError",LabeledMatchers.hasText("El campo contraseña no es válido."));
    }
    
    /**
     * Test para comprobar que String del campo contraseña tiene espacio
     */
    
    @Test
    public void testM_ContraseñaEspacio() {
        clickOn("#hplRegistrate");
        clickOn("#txtFieldNombre");
        write("user");
        clickOn("#txtFieldUsuario");
        write("user");
        clickOn("#txtFieldEmail");
        write("username@gmail.com");
        clickOn("#pswFieldContrasena");
        write("user name");
        clickOn("#pswFieldRepetirContrasena");
        write("user name");
        clickOn("#btnCrearCuenta");
        verifyThat("#lblMensajeError",LabeledMatchers.hasText("El campo contraseña no es válido."));
    }
    
    /**
     * Test para comprobar que String del campo contraseña es muy larga.
     */
    
    @Test
    public void testÑ_ContraseñaMuyLargo() {
        clickOn("#hplRegistrate");
        clickOn("#txtFieldNombre");
        write("username");
        clickOn("#txtFieldUsuario");
        write("user");
        clickOn("#txtFieldEmail");
        write("username@gmail.com");
        clickOn("#pswFieldContrasena");
        write("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        clickOn("#pswFieldRepetirContrasena");
        write("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        clickOn("#btnCrearCuenta");
        verifyThat("#lblMensajeError",LabeledMatchers.hasText("El campo contraseña no es válido."));
    }
    
    /**
     * Test para comprobar que String del campo repetir contraseña es iagual a la contraseña 
     */
    
    @Test
    public void testO_RepetirContraseñaIncorrecto() {
        clickOn("#hplRegistrate");
        clickOn("#txtFieldNombre");
        write("user");
        clickOn("#txtFieldUsuario");
        write("user");
        clickOn("#txtFieldEmail");
        write("username@gmail.com");
        clickOn("#pswFieldContrasena");
        write("username");
        clickOn("#pswFieldRepetirContrasena");
        write("user");
        clickOn("#btnCrearCuenta");
        verifyThat("#lblMensajeError",LabeledMatchers.hasText("Los campos contraseña no coinciden."));
    }
    //Hacer desde aquí
    /**
     * Test para comprobar que el email introducido es válido. Email con una única letra en el nombre y dos extensiones.
     */
    @Test
    public void testP_ComprobarEmail() {
        clickOn("#hplRegistrate");
        clickOn("#txtFieldNombre");
        write("MannyMal");
        clickOn("#txtFieldUsuario");
        write("taiwapng");
        clickOn("#txtFieldEmail");
        write("zas@gmail.com.es");
        clickOn("#pswFieldContrasena");
        write("abcd");
        clickOn("#pswFieldRepetirContrasena");
        write("abcd");
        clickOn("#btnCrearCuenta");
        clickOn("Aceptar");
        verifyThat("#borderPaneIniciarSesion", isVisible());
    }
    /**
     * Comprobar el label mostrado cuando el campo usuario es muy corto
     */
    @Test
    public void testQ_ComprobarLabelUsuarioMuyCorto() {
        clickOn("#hplRegistrate");
        clickOn("#txtFieldNombre");
        write("Hannes");
        clickOn("#txtFieldUsuario");
        write("u");
        clickOn("#txtFieldEmail");
        write("username@gmail.com");
        clickOn("#pswFieldContrasena");
        write("username");
        clickOn("#pswFieldRepetirContrasena");
        write("username");
        clickOn("#btnCrearCuenta");
        verifyThat("#lblMensajeError",LabeledMatchers.hasText("El campo usuario tiene menos de 4 caracteres."));
    }
    /**
     * Comprobar el label mostrado cuando el campo usuario es muy largo
     */
    @Test
    public void testR_ComprobarLabelUsuarioMuyLargo() {
        clickOn("#hplRegistrate");
        clickOn("#txtFieldNombre");
        write("Hannes");
        clickOn("#txtFieldUsuario");
        write("abcdefghijklmnopqrstuvwxyz");
        clickOn("#txtFieldEmail");
        write("username@gmail.com");
        clickOn("#pswFieldContrasena");
        write("username");
        clickOn("#pswFieldRepetirContrasena");
        write("user");
        clickOn("#btnCrearCuenta");
        verifyThat("#lblMensajeError",LabeledMatchers.hasText("El campo usuario tiene mas de 20 caracteres."));
    }
    /**
     * Comprobar el label mostrado cuando el campo usuario contiene espacios intermedios
     */
    @Test
    public void testS_ComprobarLabelUsuarioEspacios() {
        clickOn("#hplRegistrate");
        clickOn("#txtFieldNombre");
        write("user");
        clickOn("#txtFieldUsuario");
        write("user user");
        clickOn("#txtFieldEmail");
        write("username@gmail.com");
        clickOn("#pswFieldContrasena");
        write("username");
        clickOn("#pswFieldRepetirContrasena");
        write("username");
        clickOn("#btnCrearCuenta");
        verifyThat("#lblMensajeError",LabeledMatchers.hasText("El campo usuario no puede contener espacios."));
    }
    /**
     * Comprobar que se muestra un mensaje alert notificando que el usuario ha sido registrado de manera correcta.
     */
    @Test
    public void testT_ComprobarMensajeAlertaUsuarioRegistrado() {
        clickOn("#hplRegistrate");
        clickOn("#txtFieldNombre");
        write("James");
        clickOn("#txtFieldUsuario");
        write("season44");
        clickOn("#txtFieldEmail");
        write("swish77@gmail.com");
        clickOn("#pswFieldContrasena");
        write("abcd");
        clickOn("#pswFieldRepetirContrasena");
        write("abcd");
        clickOn("#btnCrearCuenta");
        clickOn("Aceptar");
        verifyThat("#borderPaneIniciarSesion", isVisible());
    }
    /**
     * Comprobar que al pulsar la x de cierre de ventana, sale un Alert de confirmación de cierre de ventana y se pulsa en aceptar.
     */
    @Test
    public void testU_ComprobarMensajeAlertConfirmacionSalidaVentanaAceptarAlert() {
        clickOn("#hplRegistrate");
        this.push(KeyCode.ALT, KeyCode.F4);
        verifyThat("Aceptar", NodeMatchers.isVisible());
        verifyThat("Cancelar", NodeMatchers.isVisible());
        clickOn("Aceptar");
        verifyThat("#borderPaneIniciarSesion", isVisible());
    }
    /**
     * Comprobar que al pulsar la x de cierre de ventana, sale un Alert de confirmación de cierre de ventana y se pulsa en cancelar.
     */
    @Test
    public void testV_ComprobarMensajeAlertConfirmacionSalidaVentanaCancelarAlert() {
        clickOn("#hplRegistrate");
        this.push(KeyCode.ALT, KeyCode.F4);
        verifyThat("Aceptar", NodeMatchers.isVisible());
        verifyThat("Cancelar", NodeMatchers.isVisible());
        clickOn("Cancelar");
        verifyThat("#borderPaneRegistro", isVisible());
    }
 }