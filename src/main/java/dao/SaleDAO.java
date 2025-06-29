/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author Odeth
 */
import model.Sale;
import util.DBConnection;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SaleDAO {

    // Record a new sale in the database
    public boolean recordSale(Sale sale) {
        String sql = "INSERT INTO sales (product_id, quantity_sold, total_price, sale_date) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, sale.getProductId());
            stmt.setInt(2, sale.getQuantitySold());
            stmt.setDouble(3, sale.getTotalPrice());
            stmt.setTimestamp(4, Timestamp.valueOf(sale.getSaleDate()));

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Fetch all sales from the database
    public List<Sale> getAllSales() {
        List<Sale> sales = new ArrayList<>();
        String sql = "SELECT * FROM sales ORDER BY sale_date DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Sale sale = new Sale(
                    rs.getInt("id"),
                    rs.getInt("product_id"),
                    rs.getInt("quantity_sold"),
                    rs.getDouble("total_price"),
                    rs.getTimestamp("sale_date").toLocalDateTime(),
                    rs.getString("sold_by")
                );
                sales.add(sale);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sales;
    }

    // Get the next invoice serial number for today
    public int getNextInvoiceSerialForToday() {
        String sql = "SELECT COUNT(*) FROM sales WHERE DATE(sale_date) = CURDATE()";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1) + 1;  // If 2 sales today, return 3
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 1; // Default to 1 if query fails
    }
}

