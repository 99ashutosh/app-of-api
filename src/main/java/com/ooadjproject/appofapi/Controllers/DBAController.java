package com.ooadjproject.appofapi.Controllers;

import com.ooadjproject.appofapi.Models.APIManagement;
import com.ooadjproject.appofapi.Models.AccountManagementBackend;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DBAController implements Initializable, UserDataFactory, WidgetReloaderFactory {
    public TableView<User> userView;
    public TableView<API> apiView;
    public TableColumn<User, String> userCol;
    public TableColumn<User, String> lnameCol;
    public TableColumn<User, String> fnameCol;
    public TableColumn<User, String> emailCol;
    public TableColumn<User, String> typeCol;
    public TableColumn<API, String> apiNameCol;
    public TableColumn<API, String> apiURLCol;
    public Label userLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        userLabel.setText(getUserNameFromFile());
        userView.setPlaceholder(new Label("No rows to display"));

        AccountManagementBackend manager = AccountManagementBackend.getInstance();
        ResultSet allUsers = manager.dispAllAccounts();

        fnameCol.setCellValueFactory(new PropertyValueFactory<User, String>("firstName"));
        lnameCol.setCellValueFactory(new PropertyValueFactory<User, String>("lastName"));
        userCol.setCellValueFactory(new PropertyValueFactory<User, String>("userName"));
        emailCol.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
        typeCol.setCellValueFactory(new PropertyValueFactory<User, String>("userType"));

        ObservableList<User> data = userView.getItems();
        while (true) {
            try {
                if (!allUsers.next()) break;
                else {
                    data.add(
                            new User(
                                    allUsers.getString(2),
                                    allUsers.getString(3),
                                    allUsers.getString(5),
                                    allUsers.getString(4),
                                    allUsers.getString(6)
                            )
                    );
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        userView.setItems(data);
        apiView.setPlaceholder(
                new Label("No rows to display"));
        APIManagement APImanager = APIManagement.getInstance();
        ResultSet allAPIs = APImanager.dispAllAPI();

        apiNameCol.setCellValueFactory(new PropertyValueFactory<API, String>("firstName"));
        apiURLCol.setCellValueFactory(new PropertyValueFactory<API, String>("lastName"));
        ObservableList<API> api_data = apiView.getItems();
        while (true) {
            try {
                if (!allAPIs.next()) break;
                else {
                    api_data.add(
                            new API(
                                    allAPIs.getString(2),
                                    allAPIs.getString(3)
                            )
                    );
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        apiView.setItems(api_data);
    }

    public void onDeleteUser(ActionEvent actionEvent) throws IOException {
        User user = userView.getSelectionModel().getSelectedItem();
        System.out.println(user.getUserName());

        AccountManagementBackend manager = AccountManagementBackend.getInstance();
        manager.delAccount(user.getUserName());
        reloadFxml(actionEvent,"dbaDashboard.fxml");
    }

    public void onDeleteAPI(ActionEvent actionEvent) throws IOException {
        API api = apiView.getSelectionModel().getSelectedItem();
        System.out.println(api.getFirstName());

        APIManagement manager = APIManagement.getInstance();
        manager.delAPI(api.getFirstName());
        reloadFxml(actionEvent,"dbaDashboard.fxml");
    }

    public void onLogout(ActionEvent actionEvent) throws IOException {
        deleteFileAndLogout(actionEvent);
    }

    public void onDeleteAccount(ActionEvent actionEvent) throws IOException {
        AccountManagementBackend manager = AccountManagementBackend.getInstance();
        manager.delAccount(getUserNameFromFile());
        deleteFileAndLogout(actionEvent);

    }
}
