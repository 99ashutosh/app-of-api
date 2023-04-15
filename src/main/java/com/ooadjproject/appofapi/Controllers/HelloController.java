package com.ooadjproject.appofapi.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        String url = "jdbc:postgresql://localhost:5432/api_db";
        String user = "pikachu";
        String password = "";

        try (Connection con = DriverManager.getConnection(url, user, password);
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT VERSION()")) {

            if (rs.next()) {
                System.out.println(rs.getString(1));
                    welcomeText.setText(rs.getString(1));
            }

        } catch (SQLException ex) {

            Logger lgr = Logger.getLogger(HelloController.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
}