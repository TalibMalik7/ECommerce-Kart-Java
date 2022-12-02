package com.example.supplychain;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import java.sql.ResultSet;
import java.sql.SQLException;


public class productPage {
ListView<HBox> products;

    ListView<HBox> showProductsbyName(String search) throws SQLException {
        ObservableList<HBox> productList = FXCollections.observableArrayList();
        ResultSet res =HelloApplication.connection.executeQuery("Select * from product");
        products = new ListView<>();
        Label Name = new Label();
        Label Price= new Label();
        Label ID= new Label();
        HBox Details= new HBox();
        Name.setMinWidth(50);
        ID.setMinWidth(50);
        Price.setMinWidth(50);
        Name.setText("productName");
        Price.setText("price");
        ID.setText("productId");
        Details.getChildren().addAll(ID,Name,Price);
        productList.add(Details);



        while (res.next()) {
            if(res.getString("productName").toLowerCase().contains(search.toLowerCase())) {


                Label productName = new Label();
                Label productPrice = new Label();
                Label productID = new Label();
                Button buy = new Button();
                HBox productDetails = new HBox();
                productName.setMinWidth(50);
                productID.setMinWidth(50);
                productPrice.setMinWidth(50);
                buy.setText("Buy");
                buy.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {

                        if (HelloApplication.emailId.equals("")) {
                            Dialog<String> dialog = new Dialog<>();
                            dialog.setTitle("Login");
                            ButtonType type = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                            dialog.setContentText("You are not legged in");
                            dialog.getDialogPane().getButtonTypes().add(type);
                            dialog.showAndWait();
                        } else {
                            Order place = new Order();
                            try {
                                place.placeOrder(productID.getText());
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                            System.out.println("ordered");

                        }
                    }
                });


                productName.setText(res.getString("productName"));
                productPrice.setText(res.getString("price"));
                productID.setText("" + res.getInt("productId"));
                productDetails.getChildren().addAll(productID, productName, productPrice, buy);
                productList.add(productDetails);
            }
        }
        if(productList.size()==1){
            Dialog<String> dialog = new Dialog<>();
            dialog.setTitle("Search Result");
            ButtonType type  = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            dialog.setContentText("Product not available");
            dialog.getDialogPane().getButtonTypes().add(type);
            dialog.showAndWait();
        }

        products.setItems(productList);
        return products;
    }

ListView<HBox> showProducts() throws SQLException {
    ObservableList<HBox> productList = FXCollections.observableArrayList();
    ResultSet res =HelloApplication.connection.executeQuery("Select * from product");
    products = new ListView<>();
    Label Name = new Label();
    Label Price= new Label();
    Label ID= new Label();
    HBox Details= new HBox();
    Name.setMinWidth(50);
    ID.setMinWidth(50);
    Price.setMinWidth(50);
    Name.setText("productName");
    Price.setText("price");
    ID.setText("productId");
    Details.getChildren().addAll(ID,Name,Price);
    productList.add(Details);



    while (res.next()) {
        Label productName = new Label();
        Label productPrice= new Label();
        Label productID= new Label();
        Button buy = new Button();
        HBox productDetails= new HBox();
        productName.setMinWidth(50);
        productID.setMinWidth(50);
        productPrice.setMinWidth(50);
        buy.setText("Buy");
        buy.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent)  {

                if(HelloApplication.emailId.equals("")){
                    Dialog<String> dialog = new Dialog<>();
                    dialog.setTitle("Login");
                    ButtonType type  = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                    dialog.setContentText("You are not legged in");
                    dialog.getDialogPane().getButtonTypes().add(type);
                    dialog.showAndWait();
                }
                else {
                    Order place = new Order();
                    try {
                        place.placeOrder(productID.getText());
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("ordered");

                }
            }
        });


        productName.setText(res.getString("productName"));
        productPrice.setText(res.getString("price"));
        productID.setText(""+ res.getInt("productId"));
        productDetails.getChildren().addAll(productID,productName,productPrice,buy);
        productList.add(productDetails);
    }

    products.setItems(productList);
    return products;
}
}
