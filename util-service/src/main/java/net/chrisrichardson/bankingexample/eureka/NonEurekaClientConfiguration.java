package net.chrisrichardson.bankingexample.eureka;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@Configuration
class NonEurekaClientConfiguration {

  // If you don't do this you get a Ribbon configured RestTemplate that doesn't work without Eureka

  @Bean
  @Profile("!eurekaServiceDiscovery")
  public RestTemplate restTemplate(ObjectMapper objectMapper) {
    RestTemplate rt = new RestTemplate();
    rt.getMessageConverters()
            .stream()
            .filter(MappingJackson2HttpMessageConverter.class::isInstance)
            .forEach(mc -> ((MappingJackson2HttpMessageConverter) mc).setObjectMapper(objectMapper));
    return rt;
  }
}