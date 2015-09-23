package com.getaji.memotter.viewmodel;

import com.getaji.memotter.model.Pair;
import com.getaji.memotter.view.TagView;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Getaji on 2015/09/22.
 */
public class TagViewModel {

    public static Pair<TagView, TagViewModel> create(String  name) {
        TagViewModel viewModel = new TagViewModel(name);
        return new Pair<>(new TagView(viewModel), viewModel);
    }

    private final Property<String> name;

    public TagViewModel(String  name) {
        this.name = new SimpleStringProperty(name);
    }

    public String getName() {
        return name.getValue();
    }

    public void onClose() {}

    public Property<String> nameProperty() {
        return name;
    }
}
