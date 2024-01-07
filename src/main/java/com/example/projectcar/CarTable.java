package com.example.projectcar;
import javafx.stage.*;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.*;
import javafx.scene.control.TextField;
import org.bson.Document;

import java.io.IOException;

public class CarTable {

    @FXML
    private TextField category;

    @FXML
    private TextField id;

    @FXML
    private TextField name;

    @FXML
    private TextField price;



    @FXML
    void add(ActionEvent event) throws IOException {
        try (MongoClient client = new MongoClient("localhost", 27017)) {
            MongoDatabase database = client.getDatabase("car");
            MongoCollection<Document> collection = database.getCollection("Add");
            String name_ = name.getText();
            String Id_ = id.getText();
            String Category_=category.getText();
            String price_ = price.getText();

            Document addElement = new Document("Name",name_)
                    .append("ID",Id_)
                    .append("Category",Category_)
                    .append("Price",price_);
         collection.insertOne(addElement);


            FindIterable<Document> Find = collection.find();

            MongoCursor<Document> cursor = Find.iterator();

            while(cursor.hasNext()) {
                Document doc = cursor.next();
//				System.out.println(doc);
                System.out.println(doc.get("Name"));
            }

            Caritems t = new Caritems();

//            t.table();
//            t.showItemDetails();


        }

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("cartable.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}

