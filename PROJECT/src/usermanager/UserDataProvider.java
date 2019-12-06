package usermanager;

//For the file input/output
import loginGUI.UserDialogs;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.NoSuchElementException;
import javax.swing.JOptionPane;


public final class UserDataProvider {

   private UserDataProvider() {}

   static File userInfo = null; 
   
   //Gets the database file and if it doesnt exist makes one
   public static File getDatabase() {
      
   
        try {
        
          userInfo = new File("UserInfo.txt");
           
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

            ex.printStackTrace();
            
            UserDialogs.createDialogMessage("IOException error!");
        }
        return userInfo;
    } 
   
   //Provides the database file as a BufferedReader object
   static BufferedReader fileProvider() {
        
       BufferedReader br = null;
       try {
       
           br = new BufferedReader(new FileReader(getDatabase()));
           
       }
       catch(IOException e) {
           
           e.printStackTrace();
            
           UserDialogs.createDialogMessage("IOException error!");
           
       }
        return br;
    }
    
    //Tests to see if the given username exists in the data base
    //Returns a boolean that indicates whether that username exists or not
    public static boolean isUser(String username) {
       boolean isUser = false;
       
       String userIn;
       String emptyIn; //to store unused information
       
       try(BufferedReader br = new BufferedReader(fileProvider())) {
           
           Scanner fileScanner = new Scanner(br);
           fileScanner.useDelimiter("(\\[|\\s|\\])+"); //Will ignore whitespace and "[" "]" characters when reading
           
           while (fileScanner.hasNext() && isUser == false) {
               
               userIn = fileScanner.next();
               emptyIn = fileScanner.next();
               emptyIn = fileScanner.next();
               
               if (userIn.equals(username)) {
                   isUser = true;
               }
           }
           
       }
       catch(IOException e) {
           
           e.printStackTrace();
            
           UserDialogs.createDialogMessage("IOException error!");
           
           
       }
       
       return isUser;
    }
    
    //Tests both a username and password against the database
    //Returns a boolean value that indicates whether or not username and password match the database
    public static boolean loginUser(String username, String password) {
        
        boolean loginSuccessful = false;
        
        String userIn;
        String passIn;
        String emptyIn;
        
        try(BufferedReader br = new BufferedReader(fileProvider())) {
            
            Scanner fileScanner = new Scanner(br);
            fileScanner.useDelimiter("(\\[|\\s|\\])+"); //Will ignore whitespace and "[" "]" characters when reading
            
            while (fileScanner.hasNext() && !loginSuccessful) {
                userIn = fileScanner.next();
                passIn = fileScanner.next();
                emptyIn = fileScanner.next();
                
                if (userIn.equals(username)) {
                    if (passIn.equals(password)) {
                        loginSuccessful = true;
                    }
                }
            }
        }
        catch(IOException e) {
            
            
           e.printStackTrace();
            
           UserDialogs.createDialogMessage("IOException error!");
           
       
        }
        catch(NoSuchElementException ex) {
            
            UserDialogs.createDialogMessage("Text file contains bad characters.");
            
            
        }
        
        return loginSuccessful;
    }
    
    /*
    
        writeUser writes a block of information to the userinfo file
    
        takes a string of text representing user info. To send a user objects 
        information to the method use the method <objectname>.toString() as the
        argument
    
    */
    static void writeUser(String userInfo) {
       
       try (FileWriter fw = new FileWriter(getDatabase(),true);) {
           
           fw.write("\r\n");
           fw.write(userInfo);
           
       }
       catch (IOException e) {
           
           e.printStackTrace();
            
           UserDialogs.createDialogMessage("IOException error!");
           
           
       }
        
    }
    
    
    /*
    
    Searches the database for the given username and when found returns a user object
    if no user is found then the User object will have all empty fields and
    checks must be performed to ensure that bad data is not processed
    by any methods calling this method
    
    */
    public static User getUserInfo(String username) {
        
        User searchUser = new User(); //the user information being searched for
        Boolean found = false; //indicates whether or not a given username has been located in the database
        String userIn;
        String passIn;
        String roleIn;
        
        try(BufferedReader br = new BufferedReader(fileProvider())) {
            
            Scanner fileScanner = new Scanner(br);
            fileScanner.useDelimiter("(\\[|\\s|\\])+"); //Will ignore whitespace and "[" "]" characters when reading
            
            //Will scan the file until the end or until the given username is found
            while (fileScanner.hasNext() && !found) {
                
                userIn = fileScanner.next();
                passIn = fileScanner.next();
                roleIn = fileScanner.next();
                
                //if the given username matches the one found on this line, the data is
                //written into the user object and will be passed
                if (userIn.equals(username)) {
                    
                    found = true;
                    searchUser.setName(userIn);
                    searchUser.setPassword(passIn);
                    searchUser.setRole(roleIn);
                    
                }
                
            }
            
            
        }
        catch (IOException e) {
            
        e.printStackTrace();
            
        UserDialogs.createDialogMessage("IOException error!");
                                 
        }
        
        return searchUser;
        
    }
    /*
    
    deletes a given string of userdata from the data base
    the string of data must be a string generated by the getUserInfo() method
    or one that comes from a user objects toString() method
    
    This method deletes a user by writing the database to a new temporary file
    and omitting any line that matches the given string of userdata
    it then deletes the old file and renames the temp file to the original
    effectively replacing it
    
    */
    public static void deleteUser(String userData) {
        
        try {
            
            File userFile = getDatabase();
            File tempUserFile = new File(userFile.getAbsolutePath()+ ".tmp");
            
            BufferedReader br = new BufferedReader(new FileReader(userFile));
            BufferedWriter bw = new BufferedWriter(new FileWriter(tempUserFile));
            
            String line = null;
            //Will continue until there are no more lines
            while ((line = br.readLine()) != null) {
                
                //If the current line is not the userData string then it is written
                //to the temp file
                if(!line.trim().equals(userData)) {
                    
                    bw.write(line);
                    bw.flush();
                    
                }
                
            }
            br.close();
            bw.close();
            
            //If errors in deletion or renaming occur a dialog will appear
            if (!userFile.delete()) {
                UserDialogs.createDialogMessage("Could not delete file");
            }
            if (!tempUserFile.renameTo(userFile)) {
                UserDialogs.createDialogMessage("Could not rename file");
            }
            
        }
        catch(IOException e) {
        
        e.printStackTrace();
            
        UserDialogs.createDialogMessage("IOException error!");
                              
        }
        finally {
            
            
        }
        
    }
    
}
