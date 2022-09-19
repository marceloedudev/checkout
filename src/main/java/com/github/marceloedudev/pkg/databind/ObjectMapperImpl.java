package com.github.marceloedudev.pkg.databind;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMapperImpl implements ObjectMapperAdapter {

    private ObjectMapper objectMapper;

    public ObjectMapperImpl() {
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public String writeValueAsString(Object value) throws Exception {
        try {
            return objectMapper.writeValueAsString(value);
        }
        catch (Exception e) {
            throw new Exception(e);
        }
    }
}
