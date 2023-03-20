package com.boilerplate.xotomicro_back_order_service.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.boilerplate.xotomicro_back_order_service.consumer.event.Product;
import com.boilerplate.xotomicro_back_order_service.model.Order;
import com.boilerplate.xotomicro_back_order_service.service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class KafkaConsumer {
    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    private final OrderService orderService;
    private final ObjectMapper objectMapper;

    public KafkaConsumer(OrderService orderService) {
        this.orderService = orderService;
        this.objectMapper = new ObjectMapper();
    }

    @KafkaListener(topics = "${kafka.topics.product}")
    public void listenProductEvents(ConsumerRecord<String, String> record) throws JsonProcessingException {
        logger.info("Received event, topic={}, partition={}, offset={}", record.topic(), record.partition(), record.offset());
        logger.info("Value: {}", record.value());

        var product = objectMapper.readValue(record.value(), Product.class);
        var existingOrder = orderService.getByProductName(product.getName());

        if (existingOrder != null) {
            logger.info("Found existing order for product [{}] -> Ignore this event", product.getName());
        } else {
            var order = new Order();
            order.setOrderType("DEFAULT");
            order.setProductName(product.getName());
            order.setProductDescription(product.getDescription());
            orderService.save(order);
            logger.info("Saved new order: {}", order.getId());
        }
    }
}
