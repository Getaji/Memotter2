package com.getaji.memotter.view;

import javafx.scene.control.TextArea;

/**
 * Created by Getaji on 2015/09/23.
 */
public class PersistentPromptTextArea extends TextArea {
    PersistentPromptTextArea(String text, String prompt) {
        super(text);
        setPromptText(prompt);
        getStyleClass().add("persistent-prompt");
        refreshPromptVisibility();

        textProperty().addListener(observable -> refreshPromptVisibility());
    }

    private void refreshPromptVisibility() {
        final String text = getText();
        if (isEmptyString(text)) {
            getStyleClass().remove("no-prompt");
        } else {
            if (!getStyleClass().contains("no-prompt")) {
                getStyleClass().add("no-prompt");
            }
        }
    }

    private boolean isEmptyString(String text) {
        return text == null || text.isEmpty();
    }
}
