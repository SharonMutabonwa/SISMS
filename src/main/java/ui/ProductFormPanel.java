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
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Date;
import model.Product;
import service.InventoryService;

public class ProductFormPanel extends JPanel {
    private JTextField txtName, txtCategory, txtPrice, txtQty, txtExpiryDate;
    private JTable table;
    private DefaultTableModel model;
    private InventoryService inventoryService = new InventoryService();
    private int selectedProductId = -1;

    public ProductFormPanel() {
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Product Inventory", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));
        add(title, BorderLayout.NORTH);

        // Form
        JPanel form = new JPanel(new GridLayout(6, 2, 10, 10));
        form.setBorder(BorderFactory.createEmptyBorder(10, 60, 10, 60));

        form.add(new JLabel("Name:"));
        txtName = new JTextField();
        form.add(txtName);

        form.add(new JLabel("Category:"));
        txtCategory = new JTextField();
        form.add(txtCategory);

        form.add(new JLabel("Price:"));
        txtPrice = new JTextField();
        form.add(txtPrice);

        form.add(new JLabel("Quantity:"));
        txtQty = new JTextField();
        form.add(txtQty);

        form.add(new JLabel("Expiry Date (yyyy-MM-dd):"));
        txtExpiryDate = new JTextField();
        form.add(txtExpiryDate);

        JButton btnAdd = new JButton("Add");
        JButton btnUpdate = new JButton("Update");
        JButton btnDelete = new JButton("Delete");

        form.add(btnAdd);
        form.add(btnUpdate);
        form.add(btnDelete);

        add(form, BorderLayout.CENTER);

        // Table
        model = new DefaultTableModel(new String[]{"ID", "Name", "Category", "Price", "Qty", "Expiry Date"}, 0);
        table = new JTable(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(new JScrollPane(table), BorderLayout.SOUTH);

        // Load existing
        loadProducts();

        // Events
        btnAdd.addActionListener(e -> addProduct());
        btnUpdate.addActionListener(e -> updateProduct());
        btnDelete.addActionListener(e -> deleteProduct());

        table.getSelectionModel().addListSelectionListener(e -> loadSelectedProduct());
    }

    private void loadProducts() {
        model.setRowCount(0);
        for (Product p : inventoryService.getAllProducts()) {
            model.addRow(new Object[]{
                p.getId(), p.getName(), p.getCategory(), p.getPrice(), p.getQuantity(),
                p.getExpiryDate() != null ? p.getExpiryDate().toString() : ""
            });
        }
    }

    private void addProduct() {
        try {
            String name = txtName.getText().trim();
            String category = txtCategory.getText().trim();
            double price = Double.parseDouble(txtPrice.getText().trim());
            int qty = Integer.parseInt(txtQty.getText().trim());

            String expiryStr = txtExpiryDate.getText().trim();
            Date expiryDate = null;
            if (!expiryStr.isEmpty()) {
                try {
                    expiryDate = Date.valueOf(expiryStr); // format yyyy-MM-dd
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid expiry date format. Use yyyy-MM-dd.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            if (name.isEmpty() || category.isEmpty() || price <= 0 || qty < 0) {
                JOptionPane.showMessageDialog(this, "Invalid input values.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Product p = new Product(name, category, price, qty, expiryDate, null); // supplier can be null
            inventoryService.addProduct(p);
            JOptionPane.showMessageDialog(this, "Product added.");
            loadProducts();
            clearForm();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateProduct() {
        if (selectedProductId == -1) {
            JOptionPane.showMessageDialog(this, "Select a product to update.", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            String name = txtName.getText().trim();
            String category = txtCategory.getText().trim();
            double price = Double.parseDouble(txtPrice.getText().trim());
            int qty = Integer.parseInt(txtQty.getText().trim());

            String expiryStr = txtExpiryDate.getText().trim();
            Date expiryDate = null;
            if (!expiryStr.isEmpty()) {
                try {
                    expiryDate = Date.valueOf(expiryStr); // format yyyy-MM-dd
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid expiry date format. Use yyyy-MM-dd.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            Product p = new Product(selectedProductId, name, category, price, qty, expiryDate, null); // supplierId = null for now
            inventoryService.updateProduct(p);
            JOptionPane.showMessageDialog(this, "Product updated.");
            loadProducts();
            clearForm();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Update failed: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteProduct() {
        if (selectedProductId == -1) {
            JOptionPane.showMessageDialog(this, "Select a product to delete.", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this product?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            inventoryService.deleteProduct(selectedProductId);
            JOptionPane.showMessageDialog(this, "Product deleted.");
            loadProducts();
            clearForm();
        }
    }

    private void loadSelectedProduct() {
        int row = table.getSelectedRow();
        if (row != -1) {
            selectedProductId = Integer.parseInt(model.getValueAt(row, 0).toString());
            txtName.setText(model.getValueAt(row, 1).toString());
            txtCategory.setText(model.getValueAt(row, 2).toString());
            txtPrice.setText(model.getValueAt(row, 3).toString());
            txtQty.setText(model.getValueAt(row, 4).toString());
            txtExpiryDate.setText(model.getValueAt(row, 5).toString());
        }
    }

    private void clearForm() {
        txtName.setText("");
        txtCategory.setText("");
        txtPrice.setText("");
        txtQty.setText("");
        txtExpiryDate.setText("");
        selectedProductId = -1;
        table.clearSelection();
    }
}
