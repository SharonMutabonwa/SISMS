/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

/**
 *
 * @author Odeth
 */

import service.ReportService;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ReportsPanel extends JPanel {
    private ReportService reportService = new ReportService();
    private JTable table;
    private DefaultTableModel model;

    public ReportsPanel() {
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Reports", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));
        add(title, BorderLayout.NORTH);

        JPanel btnPanel = new JPanel();
        JButton btnDaily = new JButton("Daily Sales");
        JButton btnMonthly = new JButton("Monthly Sales");
        JButton btnLowStock = new JButton("Low Stock");
        btnPanel.add(btnDaily);
        btnPanel.add(btnMonthly);
        btnPanel.add(btnLowStock);
        add(btnPanel, BorderLayout.NORTH);

        model = new DefaultTableModel();
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        btnDaily.addActionListener(e -> loadDailyReport());
        btnMonthly.addActionListener(e -> loadMonthlyReport());
        btnLowStock.addActionListener(e -> loadLowStockReport());
    }

    private void loadDailyReport() {
        model.setRowCount(0);
        model.setColumnIdentifiers(new String[]{"Date", "Total Sales"});
        for (String[] row : reportService.getDailyReport()) model.addRow(row);
    }

    private void loadMonthlyReport() {
        model.setRowCount(0);
        model.setColumnIdentifiers(new String[]{"Month", "Total Sales"});
        for (String[] row : reportService.getMonthlyReport()) model.addRow(row);
    }

    private void loadLowStockReport() {
        model.setRowCount(0);
        model.setColumnIdentifiers(new String[]{"ID", "Name", "Category", "Qty", "Price"});
        for (String[] row : reportService.getLowStockReport()) model.addRow(row);
    }
}

