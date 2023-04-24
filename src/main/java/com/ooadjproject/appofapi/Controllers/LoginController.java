package com.ooadjproject.appofapi.Controllers;

import com.ooadjproject.appofapi.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import com.ooadjproject.appofapi.Models.AccountManagementBackend;
import java.io.IOException;


public class LoginController implements AbstractLoader, UserDataFactory{
    public Label welcomeText;
    public TextField userField;
    public PasswordField passField;


    @FXML
    void onLogin(ActionEvent event) throws IOException {
        String username = userField.getText();
        String password = passField.getText();

        AccountManagementBackend manager = AccountManagementBackend.getInstance();
        int userType = manager.checkUserPass(username,password);

        if (userType == 1) {
            createUserFile(username);
            loadNext(event,"ProgrammerDashboard.fxml");
        } else if (userType == 2) {
            createUserFile(username);
            loadNext(event,"dbaDashboard.fxml");
        } else if (userType == 0){
            createUserFile(username);
            loadNext(event,"dashboard.fxml");
        } else {
            welcomeText.setText("Error");
        }
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
