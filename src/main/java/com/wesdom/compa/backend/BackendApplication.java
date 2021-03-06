package com.wesdom.compa.backend;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.io.IOException;

@EnableDiscoveryClient
@SpringBootApplication
@EnableTransactionManagement
public class BackendApplication {

	@Bean
	public Module springDataPageModule() {
		return new SimpleModule().addSerializer(Page.class, new JsonSerializer<Page>() {
			@Override
			public void serialize(Page value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
				gen.writeStartObject();
				gen.writeNumberField("totalElements",value.getTotalElements());
				gen.writeNumberField("totalPages", value.getTotalPages());
				gen.writeNumberField("number", value.getNumber());
				gen.writeNumberField("size", value.getSize());
				gen.writeBooleanField("first", value.isFirst());
				gen.writeBooleanField("last", value.isLast());
				gen.writeFieldName("content");
				serializers.defaultSerializeValue(value.getContent(),gen);
				gen.writeEndObject();
			}
		});
	}

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

}
