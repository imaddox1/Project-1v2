/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainGUI;

import static com.sun.javafx.scene.control.skin.Utils.getResource;
import java.io.File;
import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import usermanager.User;



public final class MainGUI {
    
    
    public static void showMainGUI(User currentUser) throws IOException {
        
    FXMLLoader fxmlLoader = new FXMLLoader(MainGUI.class.getResource("MainFXML.fxml"));
    Parent root1 = fxmlLoader.load();
    Stage stage = new Stage();
    stage.setScene(new Scene(root1));
    stage.show();
       
    }
    
}
