package com.getaji.memotter.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.*;

/**
 * Created by Getaji on 2015/09/22.
 */
public class CSS {

    public static class Styles {

        @Getter @Setter @Accessors(chain = true)
        private String element;
        @Getter @Setter @Accessors(chain = true)
        private boolean isQuoting = true;

        private final Map<String, String> styles = new HashMap<>();

        public Styles() {
        }

        public Styles(String element) {
            this.element = element;
        }

        public Styles add(String key, String  value) {
            styles.put(key, value);
            return this;
        }

        public String build() {
            StringBuilder builder = new StringBuilder();
            if (isQuoting) {
                builder.append(element);
                builder.append("{");
            }
            for (Map.Entry<String, String> style : styles.entrySet()) {
                builder.append(style.getKey());
                builder.append(":");
                builder.append(style.getValue());
                builder.append(";");
            }
            if (isQuoting) {
                builder.append("}");
            }
            return builder.toString();
        }
    }

    private final Map<String, Styles> styleBlocks = new HashMap<>();

    public void addStyles(Styles styles) {
        this.styleBlocks.put(styles.element, styles);
    }

    public CSS addStyle(String element, String key, String value) {
        getOrNew(element).add(key, value);
        return this;
    }

    // TODO どうにかしろ
    public CSS addStyleToRoot(String key, String value) {
        getOrNew("root").setQuoting(false).add(key, value);
        return this;
    }

    public Styles getOrNew(String element) {
        Styles styles = this.styleBlocks.getOrDefault(element, null);
        if (styles == null) {
            styles = new Styles(element);
            styleBlocks.put(element, styles);
        }
        return styles;
    }

    public String build() {
        StringBuilder builder = new StringBuilder();
        for (Styles styles : styleBlocks.values()) {
            builder.append(styles.build());
            builder.append(" ");
        }
        if (builder.length() == 0) {
            return "";
        }
        return builder.substring(0, builder.length() - 1);
    }
}
