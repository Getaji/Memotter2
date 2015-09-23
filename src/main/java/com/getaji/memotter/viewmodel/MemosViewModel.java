package com.getaji.memotter.viewmodel;

import com.getaji.memotter.model.Memo;
import com.getaji.memotter.model.Pair;
import com.getaji.memotter.model.ViewModelPair;
import com.getaji.memotter.view.MemosView;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import lombok.val;

/**
 * Created by Getaji on 2015/09/22.
 */
public class MemosViewModel {

    public static ViewModelPair<MemosView, MemosViewModel> create() {
        return new ViewModelPair<>(MemosView::new, new MemosViewModel());
    }

    private final Property<Memo> selectedMemo = new SimpleObjectProperty<>();

    // Getter

    public Memo getSelectedMemo() {
        return selectedMemo.getValue();
    }

    public Property<Memo> selectedMemoProperty() {
        return selectedMemo;
    }
}
