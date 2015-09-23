package com.getaji.memotter.util;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Created by Getaji on 2015/09/23.
 */
public class FXUtil {
    public static void setMargin(int margin, Node... nodes) {
        Insets insets = new Insets(margin);
        for (Node node : nodes) {
            BorderPane.setMargin(node, insets);
        }
    }
    public static void setMargin(int margin, BorderPane borderPane) {
        Insets insets = new Insets(margin);
        for (Node node : borderPane.getChildren()) {
            BorderPane.setMargin(node, insets);
        }
    }

    public static HBox createHBox(int spacing, Pos alignment, Node... nodes) {
        HBox hBox = new HBox(spacing, nodes);
        hBox.setAlignment(alignment);
        return hBox;
    }

    public static VBox createVBox(int spacing, Pos alignment, Node... nodes) {
        VBox vBox = new VBox(spacing, nodes);
        vBox.setAlignment(alignment);
        return vBox;
    }
}
