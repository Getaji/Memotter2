package com.getaji.memotter.view;

import com.getaji.memotter.model.Memo;
import com.getaji.memotter.util.FXUtil;
import com.getaji.memotter.viewmodel.MemosViewModel;
import com.getaji.memotter.viewmodel.TagViewModel;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.text.TextFlow;
import javafx.util.Callback;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Getaji on 2015/09/22.
 */
public class MemosView implements View {

    // top pane
    private final BorderPane topPane = new BorderPane();
    // Input Box (top)
    private final BorderPane inputPane = new BorderPane();
    private final TextField titleField = new PersistentPromptTextField("", "タイトル");
    private final TextArea descArea = new PersistentPromptTextArea("", "説明");
    private final HBox bottomHBox = new HBox();
    private final MenuButton postButton = new MenuButton("投稿");
    private final MenuItem postToSelectedButton = new MenuItem("選択中のメモと同じ場所に追加");
    private final MenuItem postToChildOfSelectedButton = new MenuItem("選択中のメモの下位に追加");
    private final MenuItem postToRootButton = new MenuItem("最上位に追加");
    private final TextField tagsField = new PersistentPromptTextField("", "タグをカンマ(,)で区切る");
    // split pane (center)
    private final SplitPane splitPane = new SplitPane();
    // memos tree (split/left)
    private final TreeItem<Memo> memoTreeViewRoot = new TreeItem<>();
    private final TreeView<Memo> memoTreeView = new TreeView<>(memoTreeViewRoot);
    // details (split/right)
    private final VBox memoDetailBox = new VBox(4);
    private final Label titleLabel = new Label("メモの詳細");
    private final Label descLabel = new Label("メモが選択されていません");
    private final FlowPane tagsFlow = new FlowPane();

    public MemosView(MemosViewModel viewModel) {
        // top
        topPane.setTop(inputPane);
        topPane.setCenter(splitPane);
        // input
        inputPane.setTop(titleField);
        inputPane.setCenter(descArea);
        inputPane.setBottom(bottomHBox);
        descArea.setPrefHeight(100);
        bottomHBox.getChildren().addAll(tagsField, postButton);
        bottomHBox.setSpacing(4);
        bottomHBox.setAlignment(Pos.CENTER_LEFT);
        // タイトルと説明の両方が空の場合、投稿ボタンを無効にする
        EventHandler<KeyEvent> keyEventHandler =  e -> {
            boolean emptyAnd = titleField.getText().isEmpty() && descArea.getText().isEmpty();
            postButton.setDisable(emptyAnd);
        };
        titleField.addEventHandler(KeyEvent.KEY_RELEASED, keyEventHandler);
        descArea.addEventHandler(KeyEvent.KEY_RELEASED, keyEventHandler);
        // タグフィールドを横に広げる
        HBox.setHgrow(tagsField, Priority.ALWAYS);
        postButton.getItems().addAll(postToSelectedButton, postToChildOfSelectedButton, postToRootButton);
        FXUtil.setMargin(4, inputPane);
        // split
        splitPane.setOrientation(Orientation.HORIZONTAL);
        splitPane.getItems().addAll(memoTreeView, memoDetailBox);
        // tree
        memoTreeView.setShowRoot(false);
        memoTreeView.setCellFactory(param -> new MemoTreeCell());
        memoTreeView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            viewModel.selectedMemoProperty().setValue(newValue.getValue());
        });
        // details
        memoDetailBox.getChildren().addAll(titleLabel, descLabel, tagsFlow);
        memoDetailBox.setPadding(new Insets(4));
        titleLabel.setStyle("-fx-font-size: 26; -fx-font-weight: bold;");
        tagsFlow.setVgap(4);
        tagsFlow.setHgap(4);
        memoTreeView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            Memo memo = newValue.getValue();
            titleLabel.setText(memo.getTitle());
            descLabel.setText(memo.getDescription());
            tagsFlow.getChildren().clear();
            tagsFlow.getChildren().addAll(createTagNodes(memo.getTags()));
        });
        initBind(viewModel);
    }

    private void initBind(MemosViewModel viewModel) {
        viewModel.titleProperty().bindBidirectional(titleField.textProperty());
        viewModel.descriptionProperty().bindBidirectional(descArea.textProperty());
        viewModel.tagsProperty().bindBidirectional(tagsField.textProperty());
    }

    private Collection<Node> createTagNodes(Collection<String> tags) {
        List<Node> nodes = new ArrayList<>(tags.size());
        nodes.addAll(tags.stream()
                .map(tag -> TagViewModel.create(tag).getT1().getNodeView())
                .collect(Collectors.toList()));
        return nodes;
    }

    public ObservableList<TreeItem<Memo>> getTreeItems() {
        return memoTreeViewRoot.getChildren();
    }

    @Override
    public Parent getTopPane() {
        return topPane;
    }
}
