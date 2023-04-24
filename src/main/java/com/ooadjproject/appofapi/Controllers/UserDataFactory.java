package com.ooadjproject.appofapi.Controllers;

import com.ooadjproject.appofapi.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.util.Scanner;

/*
* The Factory Pattern
* */

public interface UserDataFactory {
    default String getUserNameFromFile() {
        String data = null;

        File myObj = new File("user.txt");
        Scanner myReader = null;
        try {
            myReader = new Scanner(myObj);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (myReader.hasNextLine()) {
            data = myReader.nextLine();
        }
        myReader.close();
        return data;
    }

    default void deleteFileAndLogout(ActionEvent event) throws IOException {
        File file = new File("user.txt");
        if(file.delete()) {
            System.out.println("Logged out");
        }

        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MainController.class.getResource("loginWindow.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("API DB");
        stage.setScene(scene);
        stage.show();

    }

    default void createUserFile(String username) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("user.txt"));
        writer.write(username);
        writer.close();
    }

}
