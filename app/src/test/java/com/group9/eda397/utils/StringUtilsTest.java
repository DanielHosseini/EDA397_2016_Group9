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
        assertThat(StringUtils.isBlank(null), is(true));
        assertThat(StringUtils.isBlank(StringUtils.EMPTY_STRING), is(true));
        assertThat(StringUtils.isBlank("    "), is(true));
        assertThat(StringUtils.isBlank("Hello World"), is(false));
    }
}