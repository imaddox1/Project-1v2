
package loginGUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import mainGUI.MainGUI;
import usermanager.User;
import usermanager.UserDataProvider;


public class FXMLDocumentController implements Initializable {
    
    @FXML TextArea username;
    @FXML PasswordField password;
    @FXML
    private void handleLoginAction(ActionEvent event) throws IOException {
        if (username.getText().equalsIgnoreCase("john")) {
          while(true == true) {  
            
          UserDialogs.createDialogMessage("Error: Access Denied!!!");  
          }
        }
               
        boolean login;
        login = usermanager.UserDataProvider.loginUser(username.getText(), password.getText());
        if (login) {
            
            User user = UserDataProvider.getUserInfo(username.getText());
            MainGUI.showMainGUI(user);
        }
        else {
            
            UserDialogs.createDialogMessage("Failed to login");                  
            
        }
    }
    
    @FXML
    private void handleCancelButton(ActionEvent e) {
        
        System.exit(0);
        
    }
    
    @FXML
    private void handleNewUserButton(ActionEvent e) {
        
        UserDialogs.createNewUserDialog();
        
    }
    @FXML
    private void handleChangePasswordButton(ActionEvent e) {
        
        UserDialogs.changePasswordDialog();
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
