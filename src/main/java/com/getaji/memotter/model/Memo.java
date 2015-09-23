package com.getaji.memotter.model;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by Getaji on 2015/09/22.
 */
public class Memo {

    public static final class Builder {
        private String title = "";
        private String description = "";
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private List<String> tags = new ArrayList<>();

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder tag(String  tag) {
            this.tags.add(tag);
            return this;
        }

        public Builder tags(String... tags) {
            this.tags.clear();
            Collections.addAll(this.tags, tags);
            return this;
        }

        public Memo build() {
            createdAt = LocalDateTime.now();
            updatedAt = createdAt;
            return new Memo(this);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private final Property<String> title;
    private final Property<String> description;
    private final LocalDateTime createdAt;
    private final Property<LocalDateTime> updatedAt;
    private final ObservableList<String> tags;

    public Memo(String title, String description, LocalDateTime createdAt, LocalDateTime updatedAt, Collection<String> tags) {
        this.title = new SimpleStringProperty(title);
        this.description = new SimpleStringProperty(description);
        this.createdAt = createdAt;
        this.updatedAt = new SimpleObjectProperty<>(updatedAt);
        this.tags = FXCollections.observableArrayList(tags);
    }

    private Memo(Builder builder) {
        title = new SimpleStringProperty(builder.title);
        description = new SimpleStringProperty(builder.description);
        createdAt = builder.createdAt;
        updatedAt = new SimpleObjectProperty<>(builder.updatedAt);
        tags = FXCollections.observableArrayList(builder.tags);
    }

    // Getter

    public String getTitle() {
        return title.getValue();
    }

    public Property<String> titleProperty() {
        return title;
    }

    public String getDescription() {
        return description.getValue();
    }

    public Property<String> descriptionProperty() {
        return description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt.getValue();
    }

    public Property<LocalDateTime> updatedAtProperty() {
        return updatedAt;
    }

    public ObservableList<String> getTags() {
        return tags;
    }
}
