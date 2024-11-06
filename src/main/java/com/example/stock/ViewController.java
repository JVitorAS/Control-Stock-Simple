package com.example.stock;

import com.example.stock.DAO.ProductDAO;
import com.example.stock.edit.EditController;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ViewController implements Initializable {

    private ProductDAO productController = new ProductDAO();

    @FXML
    private TableView<Product> tableStock;

    @FXML
    private TableColumn<Product, String> column_Code;

    @FXML
    private TableColumn<Product, String> column_Product;

    @FXML
    private TableColumn<Product, Integer> column_Quantity;

    @FXML
    private TableColumn<Product, String> column_Um;

    @FXML
    private TableColumn<Product, Void> column_Edit;

    @FXML
    private TableColumn<Product, Void> column_Delete;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configurarColunas();
        carregarProdutos();
    }

    private void configurarColunas() {
        column_Code.setCellValueFactory(new PropertyValueFactory<>("code"));
        column_Product.setCellValueFactory(new PropertyValueFactory<>("product"));
        column_Quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        column_Um.setCellValueFactory(new PropertyValueFactory<>("um"));

        column_Delete.setCellFactory(param -> criarBotaoDeletar());
        column_Edit.setCellFactory(param -> criarBotaoEditar());
    }

    private TableCell<Product, Void> criarBotaoDeletar() {
        Button btnDelete = new Button("Deletar");
        TableCell<Product, Void> cell = new TableCell<>() {
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : btnDelete);
            }
        };
        btnDelete.setOnAction(event -> {
            int index = cell.getIndex();
            deleteProduct(index);
        });
        return cell;
    }

    private TableCell<Product, Void> criarBotaoEditar() {
        Button btnEdit = new Button("Editar");
        TableCell<Product, Void> cell = new TableCell<>() {
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : btnEdit);
            }
        };
        btnEdit.setOnAction(event -> {
            int index = cell.getIndex();
            editProduct(index);
        });
        return cell;
    }

    private void carregarProdutos() {
        tableStock.setItems(FXCollections.observableArrayList(productController.selectProducts()));
    }

    private void deleteProduct(int index) {
        if (index >= 0 && index < tableStock.getItems().size()) {
            Product product = tableStock.getItems().get(index);
            productController.deleteProduct(product.getId());
            carregarProdutos();
        }
    }

    private void editProduct(int index) {
        if (index >= 0 && index < tableStock.getItems().size()) {
            Product product = tableStock.getItems().get(index);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("edit.fxml"));
                Parent root = loader.load();
                EditController controller = loader.getController();
                controller.setValues(product);
                Stage stage = new Stage();
                stage.setTitle("Editar Produto");
                stage.setScene(new Scene(root));
                stage.showAndWait();
                carregarProdutos();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
