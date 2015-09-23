package com.getaji.memotter.view;

import com.getaji.memotter.model.CSS;
import com.getaji.memotter.viewmodel.TagViewModel;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

/**
 * Created by Getaji on 2015/09/22.
 */
public class TagView implements NodeView {

    private final Label nameLabel;
    private final Label closeButton = new Label("x");
    private final HBox hBox;
    private final CSS css = new CSS();

    public TagView(TagViewModel viewModel) {
        nameLabel = new Label(viewModel.getName());
        nameLabel.setId(".nameLabel");
        nameLabel.textProperty().bindBidirectional(viewModel.nameProperty());
        hBox = new HBox(nameLabel, closeButton);
        hBox.setAlignment(Pos.CENTER_LEFT);
        closeButton.setId(".closeButton");
        closeButton.setTextFill(Color.DARKGRAY);
        closeButton.prefWidthProperty().bind(closeButton.heightProperty());
        closeButton.setAlignment(Pos.CENTER);
        closeButton.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
            closeButton.setStyle("-fx-background-color: white;");
        });
        closeButton.addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
            closeButton.setStyle("-fx-background-color: none;");
        });
        closeButton.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
            closeButton.setStyle("-fx-background-color: gray;");
        });
        closeButton.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> {
            closeButton.setStyle("-fx-background-color: white;");
        });
        closeButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            viewModel.onClose();
        });
        css.addStyleToRoot("-fx-background-color", "#91D0CD;");
        hBox.setStyle(css.build());
    }

    @Override
    public Node getNodeView() {
        return hBox;
    }
}
