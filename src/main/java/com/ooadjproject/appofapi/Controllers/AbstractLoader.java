package com.ooadjproject.appofapi.Controllers;

import javafx.event.ActionEvent;
import java.io.IOException;

/*
* The Bridge Pattern
* */

public interface AbstractLoader {
    void loadNext(ActionEvent event, String fxmlFile) throws IOException;
}
