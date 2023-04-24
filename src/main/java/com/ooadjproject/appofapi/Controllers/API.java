package com.ooadjproject.appofapi.Controllers;

import javafx.beans.property.SimpleStringProperty;

public class API {
    private final SimpleStringProperty apiName = new SimpleStringProperty("");
    private final SimpleStringProperty apiURL = new SimpleStringProperty("");

    public API() {
        this("", "");
    }

    public API(String firstName, String lastName) {
        setFirstName(firstName);
        setLastName(lastName);
    }

    public String getFirstName() {
        return apiName.get();
    }

    public void setFirstName(String fName) {
        apiName.set(fName);
    }

    public String getLastName() {
        return apiURL.get();
    }

    public void setLastName(String fName) {
        apiURL.set(fName);
    }
}
