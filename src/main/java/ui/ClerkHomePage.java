/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

/**
 *
 * @author Odeth
 */

import javax.swing.*;
import java.awt.*;

public class ClerkHomePage extends JFrame {
    public ClerkHomePage() {
        setTitle("Clerk Dashboard - SISMS");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabs = new JTabbedPane();
        tabs.add("Sales", new SalesPanel());
        tabs.add("Sales History", new SalesHistoryPanel());

        add(tabs, BorderLayout.CENTER);
    }
}
