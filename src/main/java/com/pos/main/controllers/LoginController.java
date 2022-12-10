package com.pos.main.controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import com.pos.main.App;

public class LoginController {

    @FXML
    private void switchToDashboard() throws IOException {
        App.setRoot("fxml/pages/dashboard");
        App.setCSS("assets/css/dashboard");
    }
}
