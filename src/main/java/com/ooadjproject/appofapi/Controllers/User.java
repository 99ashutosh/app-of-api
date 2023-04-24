package com.ooadjproject.appofapi.Controllers;

import javafx.beans.property.SimpleStringProperty;

public class User {
        private final SimpleStringProperty firstName = new SimpleStringProperty("");
        private final SimpleStringProperty lastName = new SimpleStringProperty("");
        private final SimpleStringProperty email = new SimpleStringProperty("");
        private final SimpleStringProperty username = new SimpleStringProperty("");

        private final SimpleStringProperty userType = new SimpleStringProperty("");

        public User() {
            this("", "", "", "", "");
        }

        public User(String firstName, String lastName, String email, String username, String userType) {
            setFirstName(firstName);
            setLastName(lastName);
            setEmail(email);
            setUserName(username);
            setUserType(userType);
        }

        public String getFirstName() {
            return firstName.get();
        }

        public void setFirstName(String fName) {
            firstName.set(fName);
        }

        public String getLastName() {
            return lastName.get();
        }

        public void setLastName(String fName) {
            lastName.set(fName);
        }

        public String getEmail() {
            return email.get();
        }

        public void setEmail(String fName) {
            email.set(fName);
        }

    public String getUserName() {
        return username.get();
    }

    public void setUserName(String uName) {
        username.set(uName);
    }

    public String getUserType() {
        return userType.get();
    }

    public void setUserType(String type) {
        userType.set(type);
    }


}
