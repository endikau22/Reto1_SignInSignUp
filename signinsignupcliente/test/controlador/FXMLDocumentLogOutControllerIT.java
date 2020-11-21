/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import application.G5SigninSignUpClientapp;
import javafx.stage.Stage;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.matcher.base.NodeMatchers.isEnabled;
import static org.testfx.matcher.base.NodeMatchers.isVisible;

/**
 * @version 1.0
 * @since 02/11/2020
 * @author Endika, Markel
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FXMLDocumentLogOutControllerIT extends ApplicationTest{
    
    public FXMLDocumentLogOutControllerIT() {
    }

        /**
     * Starts application to be tested.
     * @param stage Primary Stage object
     * @throws Exception If there is any error
     */
    @Override public void start(Stage stage) throws Exception {
       new G5SigninSignUpClientapp().start(stage);
    }
    
    /**
     * Test que comprueba la ventana de logout 
     */
    @Test
    public void testA_InicioVentana(){
        clickOn("#txtFieldUsuario");
        write("usuario");
        clickOn("#pswFieldContrasena");
        write("password");
        clickOn("#btnEntrar");
        verifyThat("#btnLogOut", isEnabled());
    }
    
    
    /**
     * Test que comprueba que el usuario sale de la aplicación. Pulsa el botón salir y además en el alert de confirmación acepta salir
     */
    @Test
    public void testB_CerrarSesion(){
        clickOn("#txtFieldUsuario");
        write("usuario");
        clickOn("#pswFieldContrasena");
        write("password");
        clickOn("#btnEntrar");
        verifyThat("#panelLogOut", isVisible());
        clickOn("#btnLogOut");
        clickOn("Aceptar");
        verifyThat("#borderPaneIniciarSesion", isVisible());
    }
    
    /**
     * Test que comprueba que el usuario no sale de la aplicación. Pulsa el botón salir pero en el alert de confirmación cancela el salir.
     */
    @Test
    public void testC_CancelarCerrarSesion(){
        clickOn("#txtFieldUsuario");
        write("usuario");
        clickOn("#pswFieldContrasena");
        write("password");
        clickOn("#btnEntrar");
        verifyThat("#panelLogOut", isVisible());
        clickOn("#btnLogOut");
        clickOn("Cancelar");
        verifyThat("#panelLogOut", isVisible());
    }
}
