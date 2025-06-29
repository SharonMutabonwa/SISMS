/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

/**
 *
 * @author Odeth
 */
import model.Product;
import model.Sale;
import service.InventoryService;
import service.SaleService;
import util.Session;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SalesPanel extends JPanel {
    private JComboBox<Product> productCombo;
    private JTextField txtQuantity;
    private JLabel lblTotal;
    private JTable table;
    private DefaultTableModel model;

    private InventoryService inventoryService = new InventoryService();
    private SaleService saleService = new SaleService();
    private List<Sale> sessionSales = new ArrayList<>();

    public SalesPanel() {
        setLayout(new BorderLayout());

        // Title
        JLabel title = new JLabel("Sales Panel", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));
        add(title, BorderLayout.NORTH);

        // Form Panel
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 60, 10, 60));

        formPanel.add(new JLabel("Select Product:"));
        productCombo = new JComboBox<>();
        formPanel.add(productCombo);

        formPanel.add(new JLabel("Quantity:"));
        txtQuantity = new JTextField();
        formPanel.add(txtQuantity);

        formPanel.add(new JLabel("Total Price:"));
        lblTotal = new JLabel("0 RWF");
        formPanel.add(lblTotal);

        JButton btnCalculate = new JButton("Calculate Total");
        JButton btnSell = new JButton("Sell");
        formPanel.add(btnCalculate);
        formPanel.add(btnSell);

        add(formPanel, BorderLayout.CENTER);

        // Table for sales
        model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"Product", "Qty", "Total"});
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.SOUTH);

        // Load data
        loadProducts();
        loadSales();

        // Events
        btnCalculate.addActionListener(e -> calculateTotal());
        btnSell.addActionListener(e -> processSale());
    }

    private void loadProducts() {
        productCombo.removeAllItems();
        List<Product> products = inventoryService.getAllProducts();
        for (Product p : products) {
            productCombo.addItem(p);
        }
    }

    private void loadSales() {
        model.setRowCount(0);
        for (Sale s : sessionSales) {
            model.addRow(new Object[]{
                    s.getProductId(), // You can show product name here if needed
                    s.getQuantitySold(),
                    s.getTotalPrice()
            });
        }
    }

    private void calculateTotal() {
        Product product = (Product) productCombo.getSelectedItem();
        try {
            int qty = Integer.parseInt(txtQuantity.getText().trim());
            if (product != null) {
                double total = qty * product.getPrice();
                lblTotal.setText(total + " RWF");
            } else {
                lblTotal.setText("No product selected");
            }
        } catch (Exception e) {
            lblTotal.setText("Invalid input");
        }
    }

    private void processSale() {
        Product product = (Product) productCombo.getSelectedItem();
        String qtyText = txtQuantity.getText().trim();

        if (product == null || qtyText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a product and enter quantity.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int qty = Integer.parseInt(qtyText);
            if (qty <= 0) throw new NumberFormatException();

            if (qty > product.getQuantity()) {
                JOptionPane.showMessageDialog(this, "Not enough stock. Only " + product.getQuantity() + " left.", "Stock Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Sale sale = new Sale(product.getId(), qty, qty * product.getPrice(), Session.currentUser);
            if (saleService.recordSale(sale)) {
                sessionSales.add(sale);
                showReceipt(product, qty);
                loadProducts();
                loadSales();
                txtQuantity.setText("");
                lblTotal.setText("0 RWF");
            } else {
                JOptionPane.showMessageDialog(this, "Transaction failed.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Enter a valid positive number.", "Invalid Quantity", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showReceipt(Product product, int qty) {
        double total = qty * product.getPrice();
        String invoiceNo = generateInvoiceNumber();

        String receipt = "----- RECEIPT -----\n" +
                "Invoice No: " + invoiceNo + "\n" +
                "Date: " + LocalDateTime.now() + "\n" +
                "Cashier: " + Session.currentUser + "\n" +
                "Product: " + product.getName() + "\n" +
                "Qty: " + qty + "\n" +
                "Price: " + product.getPrice() + " RWF\n" +
                "-------------------\n" +
                "TOTAL: " + total + " RWF\n" +
                "-------------------\n" +
                "Thank you for your purchase!";

        JTextArea textArea = new JTextArea(receipt);
        textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(300, 200));

        JButton printButton = new JButton("🖨 Print Receipt");
        printButton.addActionListener(e -> {
            try {
                textArea.print();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Failed to print: " + ex.getMessage(), "Print Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        saveReceiptToFile(receipt, invoiceNo);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(printButton, BorderLayout.SOUTH);

        JOptionPane.showMessageDialog(this, panel, "Sale Successful", JOptionPane.PLAIN_MESSAGE);
    }

    private void saveReceiptToFile(String receiptContent, String invoiceNo) {
        try {
            java.io.File dir = new java.io.File("receipts");
            if (!dir.exists()) dir.mkdirs();

            String filename = "receipts/" + invoiceNo + ".txt";
            java.io.FileWriter writer = new java.io.FileWriter(filename);
            writer.write(receiptContent);
            writer.close();
            System.out.println("Receipt saved: " + filename);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Failed to save receipt: " + e.getMessage(), "File Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String generateInvoiceNumber() {
        String datePart = LocalDate.now().toString().replace("-", "");
        int serial = saleService.getNextInvoiceSerialForToday();
        return "INV-" + datePart + "-" + String.format("%03d", serial);
    }
}
