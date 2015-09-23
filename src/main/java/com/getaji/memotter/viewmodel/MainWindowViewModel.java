package com.getaji.memotter.viewmodel;

import com.getaji.memotter.model.Pair;
import com.getaji.memotter.model.ViewModelPair;
import com.getaji.memotter.view.MainWindowView;
import javafx.stage.Stage;
import lombok.val;

/**
 * Created by Getaji on 2015/09/22.
 */
public class MainWindowViewModel {

    public static ViewModelPair<MainWindowView, MainWindowViewModel> create(Stage stage) {
        return new ViewModelPair<>(MainWindowView::new, new MainWindowViewModel(), stage);
    }

    public MainWindowViewModel() {
    }
}
