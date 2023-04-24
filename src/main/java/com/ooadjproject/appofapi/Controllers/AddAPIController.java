package com.ooadjproject.appofapi.Controllers;

import com.ooadjproject.appofapi.Models.APIManagement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AddAPIController implements UserDataFactory {
    public TextArea APIURLField;
    public TextField APINameField;
    public Label resLab;

    @FXML
    public void addAPIButton(ActionEvent event){
        String name = APINameField.getText();
        String url = APIURLField.getText();

        APIManagement manager = APIManagement.getInstance();
        if (manager.insAPI(name, url, getUserNameFromFile())) {
            resLab.setText("Made API");
        } else {
            resLab.setText("Error");
        }
    }
}
