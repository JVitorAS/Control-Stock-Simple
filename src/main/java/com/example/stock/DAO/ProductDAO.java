package com.example.stock.DAO;

import com.example.stock.Product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private String host;
    private String db;
    private String user;
    private String pass;
    private Connection conn;

    public ProductDAO() {
        host = "127.0.0.1";
        db = "sistema_treino";
        user = "root";
        pass = "";
        //Connection with Hostinger
        String url = "jdbc:mysql://" + host + "/" + db;
        try {
            conn = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Product> selectProducts() {
        String sql = "SELECT ID, CODIGO, PRODUTO, QUANTIDADE, UM, PRICE FROM PRODUTO_TESTE";
        List<Product> products = new ArrayList<>();
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Product product = new Product(
                        rs.getInt("ID"),
                        rs.getString("CODIGO"),
                        rs.getString("PRODUTO"),
                        rs.getInt("QUANTIDADE"),
                        rs.getString("UM"),
                        rs.getDouble("PRICE")
                );
                products.add(product);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return products;
    }
    public void insertProduct(Product product) {
        String sql = "INSERT INTO PRODUTO_TESTE(CODIGO, PRODUTO, QUANTIDADE, UM, PRICE) VALUES (?,?,?,?,?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, product.getCode());
            pstmt.setString(2, product.getProduct());
            pstmt.setInt(3, product.getQuantity());
            pstmt.setString(4, product.getUm());
            pstmt.setDouble(5,product.getPrice());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public void updateProduct(Product product) {
        String sql = "UPDATE PRODUTO_TESTE SET CODIGO = ?, PRODUTO = ?, QUANTIDADE = ?, UM = ?, PRICE = ? WHERE ID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, product.getCode());
            pstmt.setString(2, product.getProduct());
            pstmt.setInt(3, product.getQuantity());
            pstmt.setString(4, product.getUm());
            pstmt.setDouble(5,product.getPrice());
            pstmt.setInt(6,product.getId());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void deleteProduct(Integer id) {
        String sql = "DELETE FORM PRODUTO_TESTE WHERE ID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public Product selectProduct(int id) {
        String sql = "SELECT * FROM PRODUTO_TESTE ID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Product (
                    rs.getInt("ID"),
                    rs.getString("CODIGO"),
                    rs.getString("PRODUTO"),
                    rs.getInt("QUANTIDADE"),
                    rs.getString("UM"),
                    rs.getDouble("PRICE")
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
