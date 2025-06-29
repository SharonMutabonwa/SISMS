/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

/**
 *
 * @author Odeth
 */

import dao.SaleDAO;
import model.Sale;

import java.util.List;

public class SaleService {
    private SaleDAO saleDAO = new SaleDAO();

    public boolean recordSale(Sale sale) {
    return saleDAO.recordSale(sale);
    }


    public List<Sale> getAllSales() {
        return saleDAO.getAllSales();
    }
    
    public int getNextInvoiceSerialForToday() {
        return saleDAO.getNextInvoiceSerialForToday();
    }

}
