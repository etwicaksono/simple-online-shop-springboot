package com.etwicaksono.simpleonlineshop.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class SwaggerConfig {

   @Autowired
   private Environment env;

   @Bean
   public OpenAPI defineOpenAPI() {
      Server server = new Server();
      server.setUrl(String.format("http://%s:%s", env.getProperty("server.address", "localhost"),
            env.getProperty("server.port", "8080")));
      server.setDescription("Simple Online Shop API");

      Contact myContact = new Contact();
      myContact.setName("Eko Teguh Wicaksono");
      myContact.setUrl("https://github.com/etwicaksono");
      myContact.setEmail("ekoteguhwicaksono@gmail.com");

      Info information = new Info()
            .title("Simple Online Shop API")
            .version("1.0.0")
            .description("Simple Online Shop API Documentation")
            .contact(myContact);
      return new OpenAPI().info(information).servers(List.of(server));
   }

}
