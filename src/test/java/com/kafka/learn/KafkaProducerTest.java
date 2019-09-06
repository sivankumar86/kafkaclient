package com.kafka.learn;

import com.kafka.learn.mock.UserdataGenerator;
import com.kafka.learn.model.DetailData;
import com.kafka.learn.producer.ProducerCreator;
import com.kafka.learn.utils.ApplicationProperties;
import org.apache.kafka.clients.producer.Producer;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Properties;

public class KafkaProducerTest {
    Properties properties=null;
    @Before
    public void setup() {
        ApplicationProperties applicationProperties=new ApplicationProperties();
        properties= applicationProperties.getProperties();

    }

    @Test
    public void lastSeenTest(){
       /* Producer<String, DetailData> producer = ProducerCreator.createDetailProducer(properties);
        while (true) {
            DetailData detailData = null;
            try {
                detailData = UserdataGenerator.getRow();
                KafkaProducer.sendSummary(producer,detailData,properties);
                detailData.getMetadata().setTimestamp(System.currentTimeMillis());
                KafkaProducer.sendSummary(producer,detailData,properties);

            } catch (IOException e) {

            } catch (CloneNotSupportedException e) {

            }

        }*/
    }


}
