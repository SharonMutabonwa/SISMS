/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author Odeth
 */

import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReportDAO {

    public List<String[]> getDailySalesReport() {
        List<String[]> report = new ArrayList<>();
        String sql = "SELECT DATE(sale_date) as date, SUM(total_price) as total_sales " +
                     "FROM sales GROUP BY DATE(sale_date) ORDER BY DATE(sale_date) DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String date = rs.getString("date");
                String totalSales = rs.getString("total_sales");
                report.add(new String[]{date, totalSales});
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return report;
    }

    public List<String[]> getMonthlySalesReport() {
        List<String[]> report = new ArrayList<>();
        String sql = "SELECT DATE_FORMAT(sale_date, '%Y-%m') as month, SUM(total_price) as total_sales " +
                     "FROM sales GROUP BY month ORDER BY month DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String month = rs.getString("month");
                String totalSales = rs.getString("total_sales");
                report.add(new String[]{month, totalSales});
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return report;
    }

    public List<String[]> getLowStockReport() {
        List<String[]> report = new ArrayList<>();
        String sql = "SELECT id, name, category, quantity, price FROM products WHERE quantity <= 5 ORDER BY quantity ASC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String id = String.valueOf(rs.getInt("id"));
                String name = rs.getString("name");
                String category = rs.getString("category");
                String quantity = String.valueOf(rs.getInt("quantity"));
                String price = String.valueOf(rs.getDouble("price"));
                report.add(new String[]{id, name, category, quantity, price});
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return report;
    }
}

