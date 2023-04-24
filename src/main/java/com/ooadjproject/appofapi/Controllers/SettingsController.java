package com.ooadjproject.appofapi.Controllers;

import com.ooadjproject.appofapi.Models.AccountManagementBackend;
import javafx.event.ActionEvent;
import java.io.IOException;

public class SettingsController implements UserDataFactory {
    public void onDeleteAccount(ActionEvent event) throws IOException {
        AccountManagementBackend manager = AccountManagementBackend.getInstance();
        manager.delAccount(getUserNameFromFile());
        deleteFileAndLogout(event);
    }
}