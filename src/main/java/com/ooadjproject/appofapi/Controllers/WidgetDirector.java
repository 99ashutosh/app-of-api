package com.ooadjproject.appofapi.Controllers;

import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.SQLException;


/*
* The Builder Pattern
* */

public class WidgetDirector {
    public VBox makeAPIWidgets() throws SQLException, IOException {
        APIWidget newBuilder = new APIWidget();
        return newBuilder.setVBoxLayoutAndCreateWidgets();
    }
}
