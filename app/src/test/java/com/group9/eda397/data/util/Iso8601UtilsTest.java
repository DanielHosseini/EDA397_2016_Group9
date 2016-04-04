package com.group9.eda397.data.util;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author palmithor
 * @since 24/03/16.
 */
public class Iso8601UtilsTest {

    @Test
    public void testFormat() throws Exception {
        assertThat(Iso8601Utils.format(new Date(1458826783000L)), is("2016-03-24T13:39:43Z"));
    }

    @Test
    public void testParse() throws Exception {
        Date d = Iso8601Utils.parse("2016-03-24T13:39:43Z");
        Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT"));
        c.setTime(d);
        assertThat(c.get(Calendar.YEAR), is(2016));
        assertThat(c.get(Calendar.MONTH), is(2)); // January == 0
        assertThat(c.get(Calendar.DAY_OF_MONTH), is(24));
        assertThat(c.get(Calendar.HOUR_OF_DAY), is(13));
        assertThat(c.get(Calendar.MINUTE), is(39));
        assertThat(c.get(Calendar.SECOND), is(43));
    }
}