package com.getaji.memotter;

import com.getaji.memotter.model.Pair;
import com.getaji.memotter.view.MainWindowView;
import com.getaji.memotter.viewmodel.MainWindowViewModel;
import javafx.application.Application;
import javafx.stage.Stage;
import lombok.val;

/**
 * Entry point class
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        MainWindowViewModel.create(primaryStage);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
