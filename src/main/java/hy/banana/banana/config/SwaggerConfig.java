package hy.banana.banana.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.webmvc.api.OpenApiActuatorResource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Daangn Market Clone - Banana Market API")
                        .version("v1")
                        .description("당근마켓 클론 백엔드 - 바나나 마켓 API"));
    }
}
