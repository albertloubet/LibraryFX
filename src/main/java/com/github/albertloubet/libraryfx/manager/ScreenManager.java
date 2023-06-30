package com.github.albertloubet.libraryfx.manager;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public final class ScreenManager {

    private static Map<String, Object> screenMap = new HashMap<>();
    private static Scene scene;
    private static Stage stage;

    public static void setScene(Scene scene) {
        ScreenManager.scene = scene;
    }

    public static void setStage(Stage stage) {
        ScreenManager.stage = stage;
    }

    public static void setResizable(boolean isResizable) {
        stage.setResizable(isResizable);
    }

    public static void addScreen(String name, Object parent) {
        screenMap.put(name, parent);
    }

    public static void removeScreen(String name) {
        screenMap.remove(name);
    }

    public static void activate(String name) {
        scene.setRoot((Parent) screenMap.get(name));
    }

    public static void closeStage() {
        stage.close();
    }
}
