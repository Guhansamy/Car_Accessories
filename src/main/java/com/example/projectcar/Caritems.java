

package com.example.projectcar;

import com.mongodb.MongoClient;
import com.mongodb.client.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.bson.Document;

import java.io.IOException;

public class Caritems {

    @FXML
    private TableColumn <TableItems, String> enddatecomplete;

    @FXML
    private TableColumn <TableItems, String> startdatecomplete;

    @FXML
    private TableView <TableItems> tableView;

    @FXML
    private TableColumn <TableItems, String> titlecomplete;

    @FXML
    private TableColumn <TableItems, String> desccomplete;

    @FXML
    private Button addbutton;

    @FXML
    private TextField inputarea;

    @FXML
    void addbtn(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("addpage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void deletebtn(ActionEvent event) {

        try (MongoClient client = new MongoClient("localhost",27017)){
            MongoDatabase db =  client.getDatabase("car");
            db.createCollection("info");
            MongoCollection<Document> coll = db.getCollection("info");

            // Specify the field name and its value for deletion
            String fieldName = "yourFieldName";

            // Delete the document where "fieldName" equals "fieldValue"
//            Document query = new Document(fieldName);
//            Document filter = new Document(fieldName);
//            coll.deleteOne(query);

            System.out.println("Document deleted successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initialize(){
        table();
    }
    public void table() {
        ObservableList<TableItems> tableItems = FXCollections.observableArrayList();

        try (MongoClient client = new MongoClient("localhost", 27017)) {

            MongoDatabase database = client.getDatabase("car");
            MongoCollection<Document> collection = database.getCollection("Add");

            FindIterable<Document> Find = collection.find();

            MongoCursor<Document> cursor = Find.iterator();
            {
                while (cursor.hasNext()) {
                    Document doc = cursor.next();
                    TableItems tableData = new TableItems();
                    tableData.setName((String) doc.get("Name"));
                    tableData.setstdate((String) doc.get("ID"));
                    tableData.setedate((String) doc.get("Category"));
                    tableData.setdesc((String) doc.get("Price"));
                    tableItems.add(tableData);
                }
            }
            tableView.setItems(tableItems);
            startdatecomplete.setCellValueFactory(f -> f.getValue().startProperty());
            enddatecomplete.setCellValueFactory(f -> f.getValue().endProperty());
            titlecomplete.setCellValueFactory(f -> f.getValue().titleProperty());
            desccomplete.setCellValueFactory(f -> f.getValue().descProperty());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
