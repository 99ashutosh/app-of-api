package com.ooadjproject.appofapi.Controllers;

import com.ooadjproject.appofapi.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public interface WidgetReloaderFactory {
    default void reloadFxml(ActionEvent event, String fxmlFile) throws IOException {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MainController.class.getResource(fxmlFile));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("API DB");
        stage.setScene(scene);
        stage.show();
    }
}
