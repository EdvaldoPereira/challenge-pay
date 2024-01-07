package br.com.challenge.test.integration;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.lifecycle.Startables;

import java.util.Map;
import java.util.stream.Stream;

@ContextConfiguration(initializers = AbstractIntegrationTest.Initializer.class)
public class AbstractIntegrationTest {
	static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
		
		static PostgreSQLContainer<?> postGresSql = new PostgreSQLContainer<>("postgres:11.1");
		
		public static void startContainers() {
			Startables.deepStart(Stream.of(postGresSql)).join();
		}
		
		private static Map<String, String> createConnectionConfiguration() {
			return Map.of(
					"spring.datasource.url", postGresSql.getJdbcUrl(),
					"spring.datasource.username", postGresSql.getUsername(),
					"spring.datasource.password", postGresSql.getPassword());
		}
		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		@Override
		public void initialize(final ConfigurableApplicationContext applicationContext) {
			
			startContainers();
			ConfigurableEnvironment environment = applicationContext.getEnvironment();
			MapPropertySource testContainers = new MapPropertySource("testcontainers",
			                                                         (Map) createConnectionConfiguration());
			
			environment.getPropertySources().addFirst(testContainers);
		}
	}
}
