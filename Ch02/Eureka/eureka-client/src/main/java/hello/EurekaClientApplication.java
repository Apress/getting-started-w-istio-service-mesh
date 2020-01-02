package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@EnableDiscoveryClient
@SpringBootApplication
public class EurekaClientApplication {

  public static void main(String[] args) {
    SpringApplication.run(EurekaClientApplication.class, args);
  }
}

@RestController
class ApplicationRestController {

  //@Autowired
  //private DiscoveryClient discoveryClient;

  @RequestMapping("/greeting/{user}")
  public String greeting(@PathVariable String user) {
    try {
      Thread.sleep(10000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    return "Hello! " + user;
  }
/*
  @RequestMapping("/service-instances/{applicationName}")
  public List<ServiceInstance> serviceInstancesByApplicationName(
      @PathVariable String applicationName) {
    return this.discoveryClient.getInstances(applicationName);
  }*/
}
