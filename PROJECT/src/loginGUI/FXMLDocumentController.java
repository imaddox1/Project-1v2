
package loginGUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;


public class FXMLDocumentController implements Initializable {
    
    @FXML TextArea username;
    @FXML PasswordField password;
    @FXML
    private void handleLoginAction(ActionEvent event) {
        boolean login;
        login = usermanager.UserDataProvider.loginUser(username.getText(), password.getText());
        if (login) {
            
            System.out.println("success");
            
        }
        else {
            
            System.out.println("failure");                  
            
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
