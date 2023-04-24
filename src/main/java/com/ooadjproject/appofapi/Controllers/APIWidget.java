package com.ooadjproject.appofapi.Controllers;

import com.ooadjproject.appofapi.Models.APIManagement;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.sql.SQLException;

import static javafx.scene.control.TabPane.TabClosingPolicy.UNAVAILABLE;

public class APIWidget implements APIWidgetBuilder{
    @Override
    public VBox setVBoxLayoutAndCreateWidgets() throws SQLException, IOException {
        final VBox mainVBox = new VBox();

        APIManagement manager = APIManagement.getInstance();
        ResultSet apis = manager.dispAllAPI();

        while (apis.next()) {
            mainVBox.getChildren().add(
                    createTitledPanes(apis.getString(2), apis.getString(3))
            );
        }
        apis.close();

        return mainVBox;
    }

    @Override
    public TitledPane createTitledPanes(String api_name, String api_url) throws IOException {
        VBox content = new VBox();

        String getCommand = String.format("curl -X GET %s", api_url);
        String postCommand = String.format("curl -X POST %s", api_url);
        Process getProcess = Runtime.getRuntime().exec(getCommand);
        Process postProcess = Runtime.getRuntime().exec(postCommand);
        Label jsonRespLabel = new Label("JSON Response");
        content.getChildren().add(jsonRespLabel);

        TabPane responsePane = new TabPane();
        responsePane.setTabClosingPolicy(UNAVAILABLE);
        Tab getResponseTab = new Tab("GET");
        TextArea getResponse = new TextArea(IOUtils.toString(getProcess.getInputStream(), StandardCharsets.UTF_8));
        getResponseTab.setContent(getResponse);
        Tab postResponseTab = new Tab("POST");
        TextArea postResponse = new TextArea(IOUtils.toString(postProcess.getInputStream(), StandardCharsets.UTF_8));
        postResponseTab.setContent(postResponse);
        responsePane.getTabs().add(getResponseTab);
        responsePane.getTabs().add(postResponseTab);
        content.getChildren().add(responsePane);
        VBox.setMargin(responsePane, new Insets(10));

        content.setAlignment(Pos.TOP_LEFT);
        VBox titleBox = new VBox();
        Label titleL = new Label(api_name);
        Label url = new Label(api_url);
        titleBox.getChildren().setAll(
                titleL,
                url
        );

        TitledPane pane = new TitledPane("", content);
        pane.setExpanded(false);
        pane.setGraphic(titleBox);

        return pane;
    }
}
