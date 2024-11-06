package com.example.stock;

import com.example.stock.DAO.ProductDAO;

import java.util.List;

public class ProductController {
    private static ProductDAO productDAO;

    public ProductController() {
        productDAO = new ProductDAO();
    }
    public static List<Product> listProducts() {
        return productDAO.selectProducts();
    }

}
