package com.ooadjproject.appofapi.Controllers;

import com.ooadjproject.appofapi.Models.APIManagement;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import org.apache.commons.io.IOUtils;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static javafx.scene.control.TabPane.TabClosingPolicy.UNAVAILABLE;

public class AllAPIController implements Initializable {
    public VBox main_api_vbox;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            WidgetDirector widgetDirector = new WidgetDirector();
            main_api_vbox.getChildren().add(widgetDirector.makeAPIWidgets());
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}