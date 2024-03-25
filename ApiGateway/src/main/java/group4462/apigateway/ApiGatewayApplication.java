package group4462.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class ApiGatewayApplication {

	public static void main(String[] args) {

		SpringApplication.run(ApiGatewayApplication.class, args);
	}
	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("Microservice1",r->r.path("/books/**")
						.uri("http://localhost:8080/"))
				.route("Microservice1",r->r.path("/accounts/**")
						.uri("http://localhost:8080/"))
				.route("Microservice2",r->r.path("/admin/**")
						.uri("http://localhost:8081/")).build();
	}

}
