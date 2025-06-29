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

public class DashboardPanel extends JPanel {
    public DashboardPanel() {
        setLayout(new GridLayout(2, 2, 20, 20));
        setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        add(createCard("Total Products", "120"));
        add(createCard("Total Sales Today", "205,000 RWF"));
        add(createCard("Monthly Sales", "4,300,000 RWF"));
        add(createCard("Low Stock Items", "3"));
    }

    private JPanel createCard(String title, String value) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        panel.setBackground(Color.WHITE);
        JLabel lblTitle = new JLabel(title, JLabel.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 14));
        JLabel lblValue = new JLabel(value, JLabel.CENTER);
        lblValue.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        panel.add(lblTitle, BorderLayout.NORTH);
        panel.add(lblValue, BorderLayout.CENTER);
        return panel;
    }
}

//public class DashboardPanel extends JPanel {
//
//    private JLabel lblWelcome;
//    private JLabel lblTotalProducts;
//    private JLabel lblTotalSales;
//    private JLabel lblLowStock;
//
//    public DashboardPanel(String role, String username, int totalProducts, double totalSales, int lowStockItems) {
//        setLayout(new BorderLayout());
//
//        // Welcome Banner
//        lblWelcome = new JLabel("Welcome, " + username + " (" + role + ")", JLabel.CENTER);
//        lblWelcome.setFont(new Font("Segoe UI", Font.BOLD, 20));
//        lblWelcome.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
//        add(lblWelcome, BorderLayout.NORTH);
//
//        // Stats Panel
//        JPanel statsPanel = new JPanel(new GridLayout(1, 3, 20, 20));
//        statsPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
//
//        lblTotalProducts = createStatLabel("Total Products", String.valueOf(totalProducts));
//        lblTotalSales = createStatLabel("Total Sales (RWF)", String.format("%.2f", totalSales));
//        lblLowStock = createStatLabel("Low Stock Items", String.valueOf(lowStockItems));
//
//        statsPanel.add(lblTotalProducts);
//        statsPanel.add(lblTotalSales);
//        statsPanel.add(lblLowStock);
//
//        add(statsPanel, BorderLayout.CENTER);
//    }
//
//    private JPanel createStatLabel(String title, String value) {
//        JPanel panel = new JPanel(new BorderLayout());
//        panel.setBackground(Color.WHITE);
//        panel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
//
//        JLabel lblTitle = new JLabel(title, JLabel.CENTER);
//        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 14));
//
//        JLabel lblValue = new JLabel(value, JLabel.CENTER);
//        lblValue.setFont(new Font("Segoe UI", Font.PLAIN, 24));
//        lblValue.setForeground(new Color(0, 102, 204));
//
//        panel.add(lblTitle, BorderLayout.NORTH);
//        panel.add(lblValue, BorderLayout.CENTER);
//        return panel;
//    }
//}
//
//
