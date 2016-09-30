package net.chrisrichardson.bankingexample.eureka;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableEurekaClient
@Profile("eurekaServiceDiscovery")
public class EurekaClientConfiguration {

  @Bean
  @Profile("eurekaServiceDiscovery")
  @LoadBalanced
  public RestTemplate restTemplate(ObjectMapper objectMapper) {
    RestTemplate rt = new RestTemplate();
    rt.getMessageConverters()
            .stream()
            .filter(MappingJackson2HttpMessageConverter.class::isInstance)
            .forEach(mc -> ((MappingJackson2HttpMessageConverter) mc).setObjectMapper(objectMapper));
    return rt;
  }

}