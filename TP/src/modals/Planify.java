package modals;

import java.io.*;
import java.util.ArrayList;

public class Planify {
    private static final String FILE_NAME = "users.dat";
    private static Planify instance;
    private ArrayList<Utilisateur> users;

    private Planify() {
        users = new ArrayList<>();
        loadUsersFromFile(); 
    }

    public static synchronized Planify getInstance() {
        if (instance == null) {
            instance = new Planify();
        }
        return instance;
    }

    public ArrayList<Utilisateur> getUsers() {
        return users;
    }

    public void addUser(Utilisateur user) {
        users.add(user);    
        saveUsersToFile(); 
    }

    public int signedUp(String pseudo) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getPseudo().equals(pseudo)) {
                return i;
            }
        }
        return -1; // Return -1 if user not found
    }

    public Utilisateur SignedUp2(String pseudo) {
        for (Utilisateur user : users) {
            if (user.getPseudo().equals(pseudo)) {
                return user;
            }
        } 
        return null;
    }

    public void updateUser(Utilisateur updatedUser) {
        int index = signedUp(updatedUser.getPseudo());
        if (index != -1) {
            users.set(index, updatedUser);
            saveUsersToFile(); 
        } else {
            System.err.println("Invalid index for user update.");
        }
    }

    public void saveUsersToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(users);
            System.out.println("saved");
        } catch (IOException e) {
            System.err.println("Error saving users to file: " + e.getMessage());
        }   
    }

    public void loadUsersFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            users = (ArrayList<Utilisateur>) ois.readObject();  
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading users from file: " + e.getMessage());
        }   
    }
}
