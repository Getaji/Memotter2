package com.getaji.memotter.viewmodel;

import com.getaji.memotter.model.Memo;
import com.getaji.memotter.model.Pair;
import com.getaji.memotter.model.ViewModelPair;
import com.getaji.memotter.view.MemosView;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Getter;
import lombok.val;

/**
 * Created by Getaji on 2015/09/22.
 */
public class MemosViewModel {

    public static ViewModelPair<MemosView, MemosViewModel> create() {
        return new ViewModelPair<>(MemosView::new, new MemosViewModel());
    }

    private final Property<Memo> selectedMemo = new SimpleObjectProperty<>();
    private final StringProperty title = new SimpleStringProperty();
    private final StringProperty description = new SimpleStringProperty();
    private final StringProperty tags = new SimpleStringProperty();

    // Getter
    public Memo getSelectedMemo() {
        return selectedMemo.getValue();
    }

    public String getTitle() {
        return title.get();
    }

    public StringProperty titleProperty() {
        return title;
    }

    public String getDescription() {
        return description.get();
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public String getTags() {
        return tags.get();
    }

    public StringProperty tagsProperty() {
        return tags;
    }

    public Property<Memo> selectedMemoProperty() {
        return selectedMemo;
    }
}
