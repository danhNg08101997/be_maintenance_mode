package com.example.demo.config;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.media.StringSchema;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerHeaderConfig {

    @Bean
    public OpenApiCustomizer authHeaderCustomizer() {
        return openApi -> openApi.getPaths().values().forEach(pathItem ->
                pathItem.readOperations().forEach(op -> {
                    op.addParametersItem(new Parameter()
                            .in("header")
                            .name("Authorization")
                            .description("Bearer <JWT>")
                            .required(false) // để false cho endpoint public không bị khó chịu
                            .schema(new StringSchema())
                    );
                })
        );
    }
}
