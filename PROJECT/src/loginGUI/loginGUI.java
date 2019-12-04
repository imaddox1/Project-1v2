/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loginGUI;

import java.io.File;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import usermanager.UserUtil;


public class loginGUI extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setWidth(250);
        stage.setHeight(200);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //This block validates the database's existence and if it doesnt exist prompts the user to create
        //a new one and sets the information to Admin/Admin/Admin
        try {
        
          File userInfo = new File("UserInfo.txt");
           
            if(!userInfo.exists()) {
               if (JOptionPane.showConfirmDialog(null, "No Database file found; Create new file?", "Database not found", JOptionPane.YES_NO_OPTION)
                   == JOptionPane.YES_OPTION) 
               {
                   
                   userInfo.createNewFile();
                   JOptionPane.showMessageDialog(null, "New File Created: user info is Admin/Admin");
                   UserUtil.createNewUser("Admin","Admin","Admin");
                   
               }
            }

        }

        catch (IOException ex) {

        }
        launch(args);
    }
    
    
}
