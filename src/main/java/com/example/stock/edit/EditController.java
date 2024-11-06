package com.example.stock.edit;

import com.example.stock.Product;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class EditController{
    private Integer id;
    private String code;
    private String product;
    private Integer quantity;
    private String um;
    private Double price;

    public EditController(){}

    public void setValues(String code, String product, int quantity, String um, double price) {
        this.code = code;
        this.quantity = quantity;
        this.um = um;
        this.price = price;
    }

    public void setValues(Product product) {
    }
}
