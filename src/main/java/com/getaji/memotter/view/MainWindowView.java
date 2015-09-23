package com.getaji.memotter.view;

import com.getaji.memotter.model.Memo;
import com.getaji.memotter.model.Pair;
import com.getaji.memotter.model.ViewModelPair;
import com.getaji.memotter.viewmodel.MainWindowViewModel;
import com.getaji.memotter.viewmodel.MemosViewModel;
import com.getaji.memotter.viewmodel.TagViewModel;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Created by Getaji on 2015/09/22.
 */
public class MainWindowView implements View {

    private final MainWindowViewModel viewModel;

    private final BorderPane topPane = new BorderPane();

    public MainWindowView(MainWindowViewModel viewModel, Stage stage) {
        this.viewModel = viewModel;
        stage.setScene(new Scene(topPane));
        ViewModelPair<MemosView, MemosViewModel> pair = MemosViewModel.create();
        Memo memo = Memo.builder()
                .title("もぢんぐについて")
                .description("ガッツポーズしながらウェイ")
                .tags("開発", "プログラミング", "Minecraft")
                .build();
        pair.getView().getTreeItems().add(new TreeItem<>(memo));
        this.topPane.setCenter(pair.getView().getTopPane());
    }

    @Override
    public Parent getTopPane() {
        return topPane;
    }
}
