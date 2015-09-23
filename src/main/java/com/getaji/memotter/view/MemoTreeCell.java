package com.getaji.memotter.view;

import com.getaji.memotter.model.Memo;
import javafx.scene.control.TreeCell;

/**
 * Created by Getaji on 2015/09/23.
 */
public class MemoTreeCell extends TreeCell<Memo> {
    @Override
    protected void updateItem(Memo item, boolean empty) {
        super.updateItem(item, empty);

        if (empty || item == null) {
            setText("");
            return;
        }

        setText(item.getTitle());
    }
}
