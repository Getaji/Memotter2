package com.getaji.memotter.model;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by Getaji on 2015/09/22.
 */
public class CSSTest {
    @Test
    public void build_スタイルが空なら空文字() {
        assertThat(new CSS().build(), is(""));
    }

    @Test
    public void build_シンプル() {
        final CSS css = new CSS().addStyle(".tag", "color", "#CCC");
        assertThat(css.build(), is(".tag{color:#CCC;}"));
    }

    @Test
    public void build_rootへの追加() {
        CSS css = new CSS().addStyleToRoot("background-color", "black");
        assertThat(css.build(), is("background-color:black;"));
    }
}