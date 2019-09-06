package com.kafka.learn;

import com.beust.jcommander.JCommander;
import com.kafka.learn.consumer.ConsumerCreator;
import com.kafka.learn.lookup.CacheLookup;
import com.kafka.learn.mock.UserdataGenerator;
import com.kafka.learn.model.DetailData;
import com.kafka.learn.model.SummaryData;
import com.kafka.learn.producer.ProducerCreator;
import com.kafka.learn.utils.ApplicationProperties;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class KafkaProducer {


    private static final Logger logger = LoggerFactory.getLogger(KafkaProducer.class);

    public static void main(String[] args) throws IOException, CloneNotSupportedException {
        CommandParameter commandParameter=new CommandParameter();
        JCommander jCommander=new JCommander(commandParameter);
        jCommander.parse(args);

        logger.info(commandParameter.getClinetconfig());
        Properties properties= ApplicationProperties.loadProperties(commandParameter.getClinetconfig());
        detailProducer(properties);
        detailConsumer(properties);
    }

    static void  detailConsumer(Properties properties) {
        logger.info("Consumer started");
        Thread one = new Thread() {
            public void run() {
                Consumer<String, DetailData> consumer = ConsumerCreator.createConsumer(properties);

                while (true) {
                    final ConsumerRecords<String, DetailData> consumerRecords = consumer.poll(1000);
                    if (consumerRecords.count() > 0) {
                        consumerRecords.forEach(record -> {
                            logger.debug("Record Key " + record.key());
                            summaryProducer(record.value(),properties);
                            logger.debug("Record partition " + record.partition());
                            logger.debug("Record offset " + record.offset());
                        });
                        consumer.commitAsync();
                    }
                    //consumer.close();
                }
            }
        };
        one.start();
    }


    static void summaryProducer(DetailData detailData,Properties properties)  {
        logger.debug("Summary Producer");
        try {
            Producer<String, SummaryData> producer = ProducerCreator.createSummaryProducer(properties);
            SummaryData summaryData = new SummaryData();
            summaryData.setUserId(detailData.getUserId());
            summaryData.setFirstSeen(CacheLookup.getLastSeen(detailData.getUserId(), detailData.getMetadata().getTimestamp()));
            summaryData.setLastSeen(detailData.getMetadata().getTimestamp());
            final ProducerRecord<String, SummaryData> record =
                    new ProducerRecord<String, SummaryData>(properties.getProperty("summary_topic"), detailData.getUserId(), summaryData);
            try {
                RecordMetadata metadata = producer.send(record).get();
                logger.debug("Summary Record sent with key " + detailData.getUserId() + " to partition " + metadata.partition()
                        + " with offset " + metadata.offset());
            } catch (ExecutionException e) {
                logger.error("Error in sending record");
                logger.error(e.getMessage());
            } catch (InterruptedException e) {

            }
        }
            catch (ExecutionException e){
                logger.error("Error in sending record");
                logger.error(e.getMessage());
            }

    }

    /**
     *
     * @throws IOException
     */

    static void detailProducer(Properties properties)  {
        Thread one = new Thread() {
            public void run() {
                Producer<String, DetailData> producer = ProducerCreator.createDetailProducer(properties);
                while (true) {
                    DetailData detailData = null;
                    try {
                        detailData = UserdataGenerator.getRow();
                    } catch (IOException e) {
                        logger.error(e.getMessage());
                    } catch (CloneNotSupportedException e) {
                        logger.error(e.getMessage());
                    }
                    sendSummary(producer, detailData, properties);
                }
            }
        };
        one.start();

    }

    /**
     *
     * @param producer
     * @param detailData
     * @param properties
     */
    public static void sendSummary(Producer<String, DetailData> producer, DetailData detailData, Properties properties) {
        final ProducerRecord<String, DetailData> record = new ProducerRecord<String, DetailData>(properties.getProperty("detail_topic"), detailData.getUserId(), detailData);
        try {
            RecordMetadata metadata = producer.send(record).get();
            logger.debug("Detail Record sent with key " + detailData.getUserId() + " to partition " + metadata.partition()
                    + " with offset " + metadata.offset());
        } catch (ExecutionException e) {
            logger.error("Error in sending record");
            System.out.println(e);
        } catch (InterruptedException e) {
            logger.error("Error in sending record");
            logger.error(e.getMessage());
        }
    }
}
