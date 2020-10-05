package com.felder.serial_port.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;

public class Json {
    private final ObjectMapper JSON_MAPPER = new ObjectMapper();


    public void createJson(Object obj, String strDir) throws Exception {
        JSON_MAPPER.writeValue(new File(strDir), obj);
    }


    public Object readJson(String strDir, String nameClass) throws Exception {
        return JSON_MAPPER.readValue(new File(strDir), Class.forName(nameClass));

    }
}
