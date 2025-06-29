/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

/**
 *
 * @author Odeth
 */

public class Session {
    public static String currentUser;
    public static String currentRole;

    public static String getCurrentUser() { return currentUser; }
    public static void setCurrentUser(String user) { currentUser = user; }

    public static String getCurrentRole() { return currentRole; }
    public static void setCurrentRole(String role) { currentRole = role; }

    public static void clear() {
        currentUser = null;
        currentRole = null;
    }
}

