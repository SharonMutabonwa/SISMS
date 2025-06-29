/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

/**
 *
 * @author Odeth
 */

import dao.ReportDAO;
import java.util.List;

public class ReportService {
    private ReportDAO reportDAO = new ReportDAO();

    public List<String[]> getDailyReport() {
        return reportDAO.getDailySalesReport();
    }

    public List<String[]> getMonthlyReport() {
        return reportDAO.getMonthlySalesReport();
    }

    public List<String[]> getLowStockReport() {
        return reportDAO.getLowStockReport();
    }
}

