/*
This class is used to create User objects to store data about a given user, it should be used alongside its provider
named UserDataProvider to get/set various values and have that reflect in the database, as well as checking values and validation.
The UserBase class will make an arraylist of User objects to store the entire userbase in memory when necessary.
all of the user related classes are in the package "usermanager"
To change values via user input, such as a password change use the UserUtil class
Role priviledges are handled in the UserRole class
*/

package usermanager;

public class User {

   private String name; //The username   
   private String password; //Password for this user
   private String role; //The role of the user; Will define what features can be used by the user

   /* Name methods */
   
   /* Returns the username for this user object */
   public String getName() {
   
      return this.name;
   
   }
   
   /* Changes the name of the user, if changing via dialog at runtime use UserUtil.changeName(<String>) to ensure validation */
   void setName(String newName) {
   
      this.name = newName;
   
   }
   
   /* Password methods */
   
   /* Returns this user objects password */
   public String getPassword() {
   
      return this.password;
   
   }
   /* Changes the password of this user, if changing via dialog at runtime use UserUtil.changePassword(<String>) to ensure validation */
   void setPassword(String newPassword) {
   
      this.password = newPassword;
   
   }
   
   /* Role Methods */
   
   /* Returns this user objects role */
   
   public String getRole() {
   
      return this.role;
   
   }
   
   /* Changes the role of this user, if changing via dialog at runtime use UserUtil.changeRole(<String>) to ensure validation */
   /* User roles should be one of the following: read, edit, accounting, update, admin */
   
   void setRole(String newRole) {
   
      this.role = newRole;
   
   }
   
   //Creates a string representing user information to be sent to the database
   @Override
   public String toString() {
    
       String userInfo;
       userInfo = ("[" + name + " " + password + " " + role + "]");
       
       return userInfo;
       
   }
   
}