package com.group9.eda397.utils;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author palmithor
 * @since 17/04/16.
 */
public class StringUtilsTest {

    @Test
    public void testIsNotBlank() throws Exception {
        assertThat(StringUtils.isNotBlank(null), is(false));
        assertThat(StringUtils.isNotBlank(StringUtils.EMPTY_STRING), is(false));
        assertThat(StringUtils.isNotBlank("    "), is(false));
        assertThat(StringUtils.isNotBlank("Hello World"), is(true));
    }

    @Test
    public void testIsBlank() throws Exception {
        assertThat(StringUtils.isNotBlank(null), is(true));
        assertThat(StringUtils.isNotBlank(StringUtils.EMPTY_STRING), is(true));
        assertThat(StringUtils.isNotBlank("    "), is(true));
        assertThat(StringUtils.isNotBlank("Hello World"), is(false));
    }
}