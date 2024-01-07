package com.example.projectcar;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javafx.event.ActionEvent;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.bson.Document;

import java.io.IOException;

public class Carregister {
    @FXML
    void reg(ActionEvent event) throws IOException {
        try( MongoClient client = new MongoClient("localhost", 27017)) {
            MongoDatabase database =client.getDatabase("car");
            MongoCollection<Document> collection = database.getCollection("info");

            String first_name = "Spider";
            String last_name = "man";
            String user_name = "Spider78";
            String email = "Spider@789@gmail.com";
            String password = "spider";

            Document document = new Document("name", first_name)
                    .append("last_name", last_name)
                    .append("user_name", user_name)
                    .append("email", email)
                    .append("password", password);

            collection.insertOne(document);
            System.out.println("Document inserted successfully");
        }
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("cartable.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
//    public static void Register() throws Exception {
//
//    }
}
