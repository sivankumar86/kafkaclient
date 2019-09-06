package com.kafka.learn.schema;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.learn.model.DetailData;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class KafkaJsonDeserializer implements Deserializer {



    public KafkaJsonDeserializer()
    {

    }


    @Override
    public void configure(Map map, boolean b) {

    }

    @Override
    public Object deserialize(String s, byte[] bytes) {
        ObjectMapper mapper = new ObjectMapper();
        DetailData obj = null;
        try {
            obj = mapper.readValue(bytes, DetailData.class);
        } catch (Exception e) {

            System.out.println("Exception in json"+e.getMessage());
        }
        return obj;
    }

    @Override
    public void close() {

    }
}