
package usermanager;

import loginGUI.UserDialogs;
import java.util.regex.Pattern;

/*

    This class contains methods to change passwords, roles as well as create
    new users

*/

public final class UserUtil {
    
    
    /*
    
        Creates a new user in the database, will return 0 if successful, a 1 if
        the username is taken and a 2 if any fields are blank or contain
        illegal characters. if successful it will
        write the information to the database.
    
    */
    public static int createNewUser(String newName, String newPass, String newRole) {
        
        int creationSuccessful = 0;
        
        //Checks the databse to see if the username is already registered
        if (!UserDataProvider.isUser(newName)) {
            //verifies that information was typed into the fields and it is not empty
            if (newName != null && !newName.isEmpty() && newPass != null && !newPass.isEmpty()) {
               //Will verify that the new name and password do not contain illegal characters
               //Allowed characters are: Letters, Numbers, Underscores and Hyphens
               Pattern badChar = Pattern.compile("[^A-Za-z0-9_-]");
               if (badChar.matcher(newName).find() || badChar.matcher(newPass).find()) {
                   
                   creationSuccessful = 2;
                   
               }
               else {
                   //If the user does not exist and the fields contain a valid value
                   //The user information is written to the database
                   User newUser = new User();
                   newUser.setName(newName);
                   newUser.setPassword(newPass);
                   newUser.setRole(newRole);
                   UserDataProvider.writeUser(newUser.toString());
               }
            }
            else {
                creationSuccessful = 2;
            }
            
        }
        else {
            creationSuccessful = 1;
        }
        
        return creationSuccessful;
    }
   
    
    /*
    
        Changes a given users password to the new password given
        Automatically updates the database with the new values
        It does this by writing the entire database to a temporary file except the
        given user and its information
    
        It then creates a new line in the temp file with updated information and
        replaces the old file with the temporary file
    
    */
    
    public static void changePassword(String userName, String newPass) {
        
        User oldUserInfo; //Stores the userinfo to be deleted
        User newUserInfo = new User(); //Stores the new userinfo to be written
        
        oldUserInfo = UserDataProvider.getUserInfo(userName); //finds the given users info in the database
        //Verifies that the received user information is not bad
        if (oldUserInfo.getName() != null && !oldUserInfo.getName().isEmpty()) {
            
            
            //Sets the new users information to the received information
            newUserInfo.setName(userName);
            newUserInfo.setPassword(newPass);
            newUserInfo.setRole(oldUserInfo.getRole());
            
            UserDataProvider.deleteUser(oldUserInfo.toString()); //deletes the old users info
            UserDataProvider.writeUser(newUserInfo.toString()); //writes the new users info
            
            UserDialogs.createDialogMessage("Password change successful!");
            
        }
        else {
            UserDialogs.createDialogMessage("That username does not exist or could not be located");
        }

        
    }
}

    
