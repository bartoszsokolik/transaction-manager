package pl.sokolik.bartosz.transactionmanager

import io.restassured.RestAssured
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.util.TestPropertyValues
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.testcontainers.containers.MongoDBContainer
import spock.lang.Specification

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@ContextConfiguration(initializers = [MongoInitializer.class])
class IntegrationSpec extends Specification {

    private static final MongoDBContainer MONGO_DB_CONTAINER = new MongoDBContainer("mongo:4.2.8");

    @LocalServerPort
    Integer port

    def setupSpec() {
        MONGO_DB_CONTAINER.start()
    }

    def given() {
        return RestAssured.given()
                .port(port)
                .basePath("/transaction-manager")
                .auth()
                .basic("user", "user")
    }

    def cleanupSpec() {
        MONGO_DB_CONTAINER.stop()
    }

    static class MongoInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        @Override
        void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                    String.format("spring.data.mongodb.port=%s", MONGO_DB_CONTAINER.getMappedPort(27017)),
                    String.format("spring.data.mongodb.host=%s", MONGO_DB_CONTAINER.getHost()))
                    .applyTo(configurableApplicationContext.getEnvironment());
        }
    }

}
