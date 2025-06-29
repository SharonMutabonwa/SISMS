/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

/**
 *
 * @author Odeth
 */

import model.Sale;
import service.SaleService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class SalesHistoryPanel extends JPanel {
    private JTable table;
    private DefaultTableModel model;
    private SaleService saleService = new SaleService();

    public SalesHistoryPanel() {
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Sales History", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));
        add(title, BorderLayout.NORTH);

        model = new DefaultTableModel(new String[]{"ID", "Product", "Qty", "Total", "Date", "Sold By"}, 0);
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        loadSales();
    }

    private void loadSales() {
        model.setRowCount(0);
        List<Sale> sales = saleService.getAllSales();
        for (Sale s : sales) {
            model.addRow(new Object[]{
                s.getId(),
                s.getProductId(), // Ideally replace with product name
                s.getQuantitySold(),
                s.getTotalPrice(),
                s.getSaleDate(),
                s.getSoldBy()
            });
        }
    }
}

