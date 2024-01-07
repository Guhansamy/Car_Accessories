package com.example.projectcar;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import com.mongodb.MongoClient;
import java.util.Iterator;
import org.bson.Document;


import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
//import org.w3c.dom.Document;

import java.io.IOException;
import java.sql.*;
import java.sql.Connection;
public class LoginController {
    public int data = 0;

    public static int userID ;


    @FXML
    private Label incorrectlabel;

    @FXML
    private Button loginbtn;

    @FXML
    public TextField password;

    @FXML
    public TextField user;


    @FXML
    void loginbtnclick(ActionEvent event) throws Exception {
        try(MongoClient client = new MongoClient("localhost",27017)){
            MongoDatabase db =  client.getDatabase("test007");
            db.createCollection("123");

            MongoCollection<Document> coll = db.getCollection("123");
        String user_name = "Spider78";
        String password = "spider";

        Document query = new Document("user_name", user_name);
        FindIterable<Document> itr = coll.find(query);

        if (itr.iterator().hasNext()) {
            Document userDocument = itr.iterator().next();

            String storedPassword = userDocument.getString("password");

            if (password.equals(storedPassword)) {
                System.out.println("Login successful!");
            } else {
                System.out.println("Incorrect password!");
            }
        } else {
            System.out.println("User not found!");
     }



        Parent root = FXMLLoader.load(getClass().getResource("cartable.fxml"));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();


        }


    }





    @FXML
    void registerationclick(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("register.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
