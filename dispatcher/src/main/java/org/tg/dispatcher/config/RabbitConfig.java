package org.tg.dispatcher.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitConfig {

    public final static String EXCHANGER = "exchanger";
    private final static String ROUTING_KEY_TEXT_MESSAGE = "textMessage.routing.key";

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(final ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }

    @Bean
    public Queue textMessageQueue() {
        return new Queue("queue.dispatcher");
    }


    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGER);
    }

    @Bean
    public Binding textMessageBinding() {
        return BindingBuilder.bind(textMessageQueue()).to(exchange()).with(ROUTING_KEY_TEXT_MESSAGE);
    }


}
