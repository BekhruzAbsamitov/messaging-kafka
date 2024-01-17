package com.epam;

import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.awaitility.Awaitility.await;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class KafkaProducerIntegrationTest {

//    @Container
//    static KafkaContainer kafkaContainer = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:latest"));
//
//    Logger log = LoggerFactory.getLogger(KafkaProducerIntegrationTest.class.getSimpleName());
//
//    @DynamicPropertySource
//    static void overridePropertiesInternal(DynamicPropertyRegistry registry) {
//        registry.add("spring.kafka.bootstrap-servers", kafkaContainer::getBootstrapServers);
//    }
//
//    @Autowired
//    KafkaMessagePublisher publisher;
//
//    @Test
//    void testConsumeEvents() {
//        log.info("testConsumeEvents method execution started...");
//        Customer customer = new Customer(263, "test user", "test@gmail.com", "564782542752");
//        publisher.sendEventsToTopic(customer);
//        log.info("testConsumeEvents method execution ended...");
//        await().pollInterval(Duration.ofSeconds(3)).atMost(10, SECONDS).untilAsserted(() -> {
//        });
//    }
}
