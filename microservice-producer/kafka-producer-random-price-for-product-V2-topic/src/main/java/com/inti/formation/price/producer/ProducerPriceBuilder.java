package com.inti.formation.price.producer;

import com.inti.formation.price.data.Price;
import com.inti.formation.price.util.RandPrice;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ProducerPriceBuilder {
    @Value("${kafka.topic-name}")
    private String topicName;

    @Autowired
    private KafkaTemplate<String, Price> kafkaTemplate;

    @Value("${kafka.compression-type}")
    private String compressionType;

    @Scheduled(fixedDelayString = "${schedule-time}")
    public void scheduleFixedDelayTask() {
        Price price = new Price();
        price.setPrice(RandPrice.getRandPrice());
        ProducerRecord<String, Price> producerRecord = new ProducerRecord<>(topicName,1, price.getPrice(), price);
        kafkaTemplate.send(producerRecord);
    }
}
