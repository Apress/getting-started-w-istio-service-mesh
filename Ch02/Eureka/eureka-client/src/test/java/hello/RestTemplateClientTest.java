package hello;

import static org.assertj.core.api.BDDAssertions.then;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import java.net.URI;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RestTemplateClientTest.RestTemplateTestConfig.class)
@ActiveProfiles("test")
public class RestTemplateClientTest {
  @Autowired
  RestTemplate testRestTemplate;
  @Autowired
  GreetingService greetingService;

  @Test
  public void testServiceDiscovery() throws InterruptedException {
    ResponseEntity<String> response = this.testRestTemplate.getForEntity("http://A-BOOTIFUL-CLIENT/service-instances/a-bootiful-client", String.class);

    then(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    then(response.getBody()).contains("a-bootiful-client");
  }

  @Test
  public void testGreetingAPI() throws InterruptedException {
    ResponseEntity<String> response = this.testRestTemplate.getForEntity("http://A-BOOTIFUL-CLIENT/greeting/user", String.class);

    then(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    then(response.getBody()).contains("Hello! user");
  }

  @Test
  public void testGreetingAPI2() {
    String response = greetingService.greet("user");
    then(response).contains("Hi! there");
  }

  @Configuration
  @EnableAutoConfiguration
  @EnableCircuitBreaker
  static class RestTemplateTestConfig {
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
      return new RestTemplate();
    }

    @Bean
    GreetingService gs(){
      return new GreetingService(restTemplate());
    }
  }


}

@Service
class GreetingService {

  private final RestTemplate restTemplate;

  public GreetingService(RestTemplate rest) {
    this.restTemplate = rest;
  }

  @HystrixCommand(fallbackMethod = "fallbackGreeting", commandProperties = {
          @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")
  })
  public String greet(String username) {
    URI uri = URI.create("http://A-BOOTIFUL-CLIENT/greeting/"+username);
    return this.restTemplate.getForObject(uri, String.class);
  }

  public String fallbackGreeting(String username) {
    return "Hi! there";
  }

}