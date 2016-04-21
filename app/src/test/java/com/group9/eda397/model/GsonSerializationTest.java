package com.group9.eda397.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.group9.eda397.data.util.Rfc339DateJsonAdapter;

import org.junit.Before;

import java.util.Date;

/**
 * @author palmithor
 * @since 19/04/16.
 */
public class GsonSerializationTest {

    protected Gson gson;

    @Before
    public void setUp() throws Exception {
        gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, new Rfc339DateJsonAdapter())
                .create();
    }
}
