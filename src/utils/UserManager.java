package utils;

import components.User;

import java.io.*;

public  class UserManager {
    private UserManager(){}
    public static User currentUser; //used to store current logged-in user.
    private static final String path = "assets/Database/Users/";

    public static boolean saveUser() {
        try {
            FileOutputStream ii = new FileOutputStream(path + currentUser.getEmail() + ".txt");
            ObjectOutputStream OO = new ObjectOutputStream(ii);
            OO.writeObject(currentUser);//to write the object in file
            OO.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static boolean readUser(String email) {
        try {
            //opening file stream.
            ObjectInputStream OI = new ObjectInputStream(new FileInputStream(path + email + ".txt"));
            //reading from file stream
            currentUser = (User) OI.readObject();//type cast object class to user class.
            return true;
        } catch (IOException | ClassNotFoundException e) {
            return false;
        }
    }

    public static boolean updateUser() {
        try {
            new PrintWriter(path + currentUser.getEmail() + ".txt").print("");
            ObjectOutputStream OO = new ObjectOutputStream(new FileOutputStream(path + currentUser.getEmail() + ".txt"));
            OO.writeObject(currentUser);
            OO.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static void logOut(){
        currentUser=null;
    }

}
