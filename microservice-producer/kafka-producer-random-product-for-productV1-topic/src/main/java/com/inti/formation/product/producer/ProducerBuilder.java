package com.inti.formation.product.producer;


import com.inti.formation.product.data.Product;
import com.inti.formation.product.util.ReadFileInfoProduct;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;

@Slf4j
@Component
public class ProducerBuilder {

    @Value("${kafka.topic-name}")
    private String topicName;

    @Autowired
    private KafkaTemplate<String, Product> kafkaTemplate;

    @Value("${kafka.compression-type}")
    private String compressionType;

    private final List<String> origins = Collections.emptyList();
    private final ReadFileInfoProduct infoProduct = new ReadFileInfoProduct();

    public ProducerBuilder() throws IOException {
    }

    @Scheduled(fixedDelayString = "${schedule-time}")
    public void scheduleFixedDelayTask() throws IOException {
        Product product = infoProduct.getRandomProduct();
        ProducerRecord<String, Product> producerRecord = new ProducerRecord<>(topicName,
                product.getIdproduit(), product);
        kafkaTemplate.send(producerRecord);
    }
}
