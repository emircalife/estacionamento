package br.com.calife.estacionamento.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.IntegerSchema;
import io.swagger.v3.oas.models.media.ObjectSchema;
import io.swagger.v3.oas.models.media.StringSchema;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
	
	@Value("${spring.application.version}")
	private String version;

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.info(new Info().title("Estacionamento API").version(version)
						.description("Documentação da API de Estacionamento"))
				.components(new Components().addSchemas("ResponseErrorDTO",
						new ObjectSchema().addProperty("errorCode", new IntegerSchema()).addProperty("errorMessage",
								new StringSchema())));
	}

}
