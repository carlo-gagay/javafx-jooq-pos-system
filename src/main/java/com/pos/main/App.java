package com.pos.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static final String MAIN_STYLESHEET = "assets/css/main";

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("fxml/pages/login"), 640, 480);
        App.setCSS(MAIN_STYLESHEET);
        App.setCSS("assets/css/login");
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    public static void setCSS(String css) {
        Predicate<String> isNotEmpty = str -> str != null && !str.isEmpty();
        Function<String, String> getValidResource = source -> App.class.getResource(source).toExternalForm();
        
        if (isNotEmpty.test(css)) {
            String url = getValidResource.apply(css.concat(".css"));

            if (isNotEmpty.test(url)) {
                scene.getStylesheets().add(url);
            }
        }
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}