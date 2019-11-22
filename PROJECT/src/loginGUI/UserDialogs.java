package loginGUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import usermanager.UserDataProvider;
import usermanager.UserUtil;

public class UserDialogs {
    
    
    //This Dialog will prompt a user for information to create a new user in the database
    public static void createNewUserDialog() {
        
        final Stage dialog = new Stage();
        dialog.setTitle("New User");
        GridPane grid = new GridPane();
        Label userLabel = new Label("Desired Username:");
        Label passLabel = new Label("Desired Password:");
        
        TextArea userArea = new TextArea();
        userArea.setPrefHeight(38.0);
        userArea.setPrefWidth(100);
        PasswordField passArea = new PasswordField();
        passArea.setPrefHeight(38.0);
        passArea.setPrefWidth(100);
        Button confirmButton = new Button("Confirm");
        
        confirmButton.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                    int userCreation;
        
                    userCreation = UserUtil.createNewUser(userArea.getText(), passArea.getText(), "User");
                    
                    if (userCreation == 0) {
                        createDialogMessage("Creation Successful");
                        dialog.close();
                    }
                    else if (userCreation == 1) {
                        createDialogMessage("That Username Already Exists");
                    }
                    else if (userCreation == 2) {
                       createDialogMessage("Empty fields or inappropriate characters"); 
                    }
    }
            
            
        }
        
        );
        
        Button cancelButton = new Button("Cancel");
        
        cancelButton.setOnAction(
            new EventHandler<ActionEvent>() {
            
                @Override
                public void handle(ActionEvent e) {
                    
                    dialog.close();
                    
                }
            
            }
        );
        
        dialog.initModality(Modality.APPLICATION_MODAL);
        
        grid.add(userLabel, 0, 0);
        grid.add(passLabel, 0, 1);
        grid.add(confirmButton, 0, 2);
        grid.add(userArea, 1, 0);
        grid.add(passArea, 1, 1);
        grid.add(cancelButton, 1, 2);
        
        
        Scene dialogScene = new Scene(grid, 200, 100);
        dialog.setScene(dialogScene);
        dialog.show();
       

    }
    
    //This is a simple dialog message window to display information
    //The given string is the message to be displayed
    public static void createDialogMessage(String message) {
    
    final Stage messageDialog = new Stage();
    Label dialog = new Label(message);
    dialog.setPrefHeight(100);
    dialog.setPrefWidth(140);
    dialog.setWrapText(true);
    
    Button okButton = new Button("OK");
    okButton.setPrefHeight(25);
    okButton.setPrefWidth(75);
    okButton.setLayoutX(37.5);
    GridPane grid = new GridPane();
  
    okButton.setOnAction( 
            new EventHandler<ActionEvent>() {
            
                @Override
                public void handle(ActionEvent e) {
                    
                    messageDialog.close();
                    
                }
        
                }
            );
    
    grid.add(dialog, 0, 0);
    grid.add(okButton, 0, 1);

    Scene dialogScene = new Scene(grid,150,150);
    messageDialog.setScene(dialogScene);
    messageDialog.show();
    
}
    
    //This dialog will prompt a user for an existing users name and password
    //and a new password for that user
    //Will display error messages if the information is incorrect
    //If successful a dialog will appear and the window will close once ok is pressed
    public static void changePasswordDialog() {
        
        final Stage passwordDialog = new Stage();
        Label userLabel = new Label("Username:");
        Label oldPassLabel = new Label("Old Password:");
        Label newPassLabel = new Label("New Password:");
        
        TextArea userArea = new TextArea();
        userArea.setPrefHeight(30);
        userArea.setPrefWidth(100);
        PasswordField oldPassArea = new PasswordField();
        oldPassArea.setPrefHeight(30);
        oldPassArea.setPrefWidth(100);
        PasswordField newPassArea = new PasswordField();
        newPassArea.setPrefHeight(30);
        newPassArea.setPrefWidth(100);

        Button confirm = new Button("Confirm");
        confirm.setOnAction( 
            new EventHandler<ActionEvent>() {
            
                    @Override
                    public void handle(ActionEvent e) {
                    
                        if(UserDataProvider.loginUser(userArea.getText(), oldPassArea.getText())) {
                            
                            UserUtil.changePassword(userArea.getText(), newPassArea.getText());
                            passwordDialog.close();
                            
                        }
                        else {
                            
                           createDialogMessage("Failed to authenticate user"); 
                            
                        }
                      
                    }
        
                }
            );
        
        Button cancel = new Button("Cancel");
        
        cancel.setOnAction( 
            new EventHandler<ActionEvent>() {
            
                @Override
                public void handle(ActionEvent e) {
                    
                    passwordDialog.close();
                    
                }
        
                }
            );
        
        GridPane grid = new GridPane();
        passwordDialog.initModality(Modality.APPLICATION_MODAL);
        
        grid.add(userLabel, 0, 0);
        grid.add(oldPassLabel, 0, 1);
        grid.add(newPassLabel, 0, 2);
        grid.add(confirm, 0, 3);
        grid.add(userArea, 1, 0);
        grid.add(oldPassArea, 1, 1);
        grid.add(newPassArea, 1, 2);
        grid.add(cancel, 1, 3);
        
        Scene dialogScene = new Scene(grid,200,200);
        passwordDialog.setScene(dialogScene);
        passwordDialog.show();
        
    }
    

    
}
