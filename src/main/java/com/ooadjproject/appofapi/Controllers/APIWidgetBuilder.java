package com.ooadjproject.appofapi.Controllers;

import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.SQLException;

public interface APIWidgetBuilder {

    VBox setVBoxLayoutAndCreateWidgets() throws SQLException, IOException;
    TitledPane createTitledPanes(String api_name, String api_url) throws IOException;

}
