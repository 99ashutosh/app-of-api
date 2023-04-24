package com.ooadjproject.appofapi.Controllers;

import com.ooadjproject.appofapi.MainController;
import com.ooadjproject.appofapi.Models.AccountManagementBackend;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CreateAccountController implements Initializable, AbstractLoader {

    public TextField unameField;
    public TextField fnameField;
    public TextField lnameField;
    public PasswordField passField;
    public ChoiceBox<String> typeDrop;
    public TextField emailField;
    public int userType = -1;
    public Label resultLabel;

    @FXML
    public void onCreateAccount(ActionEvent event) throws IOException {
        String uname = unameField.getText();
        String pass = passField.getText();
        String fname = fnameField.getText();
        String lname = lnameField.getText();
        String email = emailField.getText();
        int type = userType;

        AccountManagementBackend manager = AccountManagementBackend.getInstance();

        if (!manager.addUser(uname, fname, lname, pass, email, type)) {
            resultLabel.setText("Made an Account");
        } else {
            resultLabel.setText("Error");
        }

        if (userType == 1) {
            loadNext(event,"ProgrammerDashboard.fxml");
        } else if (userType == 2) {
            loadNext(event,"dbaDashboard.fxml");
        } else {
            loadNext(event, "dashboard.fxml");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String[] roles = { "User","Programmer","Database Administrator" };

        typeDrop.setItems(FXCollections.observableArrayList(roles));
        typeDrop.getSelectionModel().select("Choose a Role");

        typeDrop.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue ov, Number value, Number new_value) {
                userType = new_value.intValue();
            }
        });
    }

    @Override
    public void loadNext(ActionEvent event, String fxmlFile) throws IOException {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MainController.class.getResource(fxmlFile));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("API DB");
        stage.setScene(scene);
        stage.show();
    }
}
