package com.ooadjproject.appofapi.Controllers;

import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import com.ooadjproject.appofapi.MainController;
import org.apache.commons.io.IOUtils;



import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;

import static javafx.scene.control.TabPane.TabClosingPolicy.UNAVAILABLE;


public class allApiController implements Initializable {
    public VBox main_api_vbox;

    private MainController main;

    private static final Image BLUE_FISH = new Image("http://icons.iconarchive.com/icons/fasticon/fish-toys/128/Blue-Fish-icon.png");
    private static final Image RED_FISH = new Image("http://icons.iconarchive.com/icons/fasticon/fish-toys/128/Red-Fish-icon.png");
    private static final Image YELLOW_FISH = new Image("http://icons.iconarchive.com/icons/fasticon/fish-toys/128/Yellow-Fish-icon.png");
    private static final Image GREEN_FISH = new Image("http://icons.iconarchive.com/icons/fasticon/fish-toys/128/Green-Fish-icon.png");


    private VBox createStackedTitledPanes() throws IOException {
        final VBox stackedTitledPanes = new VBox();
        stackedTitledPanes.getChildren().setAll(
                createTitledPane("One Fish", GREEN_FISH),
                createTitledPane("Two Fish", YELLOW_FISH),
                createTitledPane("Red Fish", RED_FISH),
                createTitledPane("Blue Fish", BLUE_FISH)
        );

        return stackedTitledPanes;
    }

    public TitledPane createTitledPane(String title, Image... images) throws IOException {
        VBox content = new VBox();
        for (Image image : images) {
            String getCommand = "curl -X GET https://kontests.net/api/v1/all";
            String postCommand = "curl -X POST https://kontests.net/api/v1/all";
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

            //ImageView imageView = new ImageView(image);
            //content.getChildren().add(imageView);

            VBox.setMargin(responsePane, new Insets(10));
        }
        content.setAlignment(Pos.TOP_LEFT);

        VBox titleBox = new VBox();
        Label titleL = new Label(title);
        Label url = new Label("trdt");
        titleBox.getChildren().setAll(
                titleL,
                url
        );

        TitledPane pane = new TitledPane("", content);
        pane.setExpanded(false);
        pane.setGraphic(titleBox);

        return pane;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            main_api_vbox.getChildren().add(createStackedTitledPanes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
