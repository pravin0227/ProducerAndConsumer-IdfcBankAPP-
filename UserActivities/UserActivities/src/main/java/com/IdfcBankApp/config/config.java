package com.IdfcBankApp.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.IdfcBankApp.UserActivities;

@Configuration
@EnableKafka
public class config {
	
	@Bean
	public ConsumerFactory<String, UserActivities> consumerfactory(){
		Map<String,Object> configprop = new HashMap<String, Object>();
		configprop.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		configprop.put(ConsumerConfig.GROUP_ID_CONFIG, "IdfcBankgroup");
		configprop.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		configprop.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		return new DefaultKafkaConsumerFactory<>(configprop);
	}
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, UserActivities> kafkalistener(){
		ConcurrentKafkaListenerContainerFactory<String, UserActivities> listen = new ConcurrentKafkaListenerContainerFactory<>();
		listen.setConsumerFactory(consumerfactory());
		return listen;
	}

	
	
}
