package com.getaji.memotter.model;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by Getaji on 2015/09/22.
 */
public class StylesTest {
    @Test
    public void build_スタイルが空なら空文字() {
        CSS.Styles styles = new CSS.Styles();
        styles.setQuoting(false);
        assertThat(styles.build(), is(""));
    }

    @Test
    public void build_シンプル() {
        CSS.Styles styles = new CSS.Styles("body");
        styles.add("color", "black");
        assertThat(styles.build(), is("body{color:black;}"));
    }
}