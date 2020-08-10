package com.company.controller;

import com.company.Main;
import com.company.model.Feedback;
import com.company.model.Manager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ShowFeedBacksController implements Initializable {

    @FXML TableView feedbackTbl;
    @FXML TableColumn feedbackCln;
    @FXML Button removeBtn;

    TableView FeedbackTbl2;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //show table view :
        feedbackCln.setCellValueFactory(new PropertyValueFactory<>("msg"));

        for (Feedback feedback : Feedback.feedBacks) {
            feedbackTbl.getItems().add(feedback);
        }

        removeBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                //find index to remove :
                int removeIndex = Feedback.feedBacks.indexOf(feedbackTbl.getSelectionModel().getSelectedItem());
                Feedback.feedBacks.remove(Feedback.feedBacks.get(removeIndex));

                //show in table :
                feedbackTbl.getItems().clear();
                for (int i=0 ; i<Feedback.feedBacks.size() ; i++) {
                    feedbackTbl.getItems().add(Feedback.feedBacks.get(i));
                }
                feedbackTbl.refresh();

                //write files :
                try {
                    Manager.writeAll();
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("error writing files");
                }

                //success pop up :
                FXMLLoader successPopUpLoader = new FXMLLoader(Main.class.getResource("view/SuccessPopUp.fxml"));
                try {
                    successPopUpLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("error loading pop up");
                }
                Stage successPopUpStage = new Stage();
                successPopUpStage.setScene(new Scene(successPopUpLoader.getRoot()));
                successPopUpStage.show();
            }
        });
    }
}
