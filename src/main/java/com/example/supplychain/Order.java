package com.example.supplychain;

import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Order {
    void placeOrder(String productID) throws SQLException {
        ResultSet res = HelloApplication.connection.executeQuery("Select max(orderID) from orders");
        int orderid=0;
        if(res.next())
            orderid = res.getInt("max(orderID)")+1;
        String query = String.format("Insert into orders values(%s,%s,'%s')",orderid,productID,HelloApplication.emailId);
        int response = HelloApplication.connection.executeUpdate(query);
        if(response>0){
            Dialog<String> dialog = new Dialog<>();
            dialog.setTitle("Order");
            ButtonType type  = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            dialog.setContentText("Your order is placed");
            dialog.getDialogPane().getButtonTypes().add(type);
            dialog.showAndWait();
        }
    }
}
