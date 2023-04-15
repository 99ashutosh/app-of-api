module com.ooadjproject.appofapi {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires org.apache.commons.io;

    opens com.ooadjproject.appofapi to javafx.fxml;
    exports com.ooadjproject.appofapi;
    exports com.ooadjproject.appofapi.Controllers;
    opens com.ooadjproject.appofapi.Controllers to javafx.fxml;
    exports com.ooadjproject.appofapi.Models;
    opens com.ooadjproject.appofapi.Models to javafx.fxml;
}