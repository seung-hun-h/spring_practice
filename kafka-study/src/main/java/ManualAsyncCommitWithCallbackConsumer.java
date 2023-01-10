import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

public class ManualAsyncCommitConsumer {

	public static final String BOOTSTRAP_SERVER = "localhost:9092";
	public static final String GROUP_ID = "test-group";
	public static final String TOPIC_NAME = "hello.kafka";

	public static void main(String[] args) {
		Properties properties = new Properties();
		properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVER);
		properties.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID);
		properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);

		try(Consumer<String, String> kafkaConsumer = new KafkaConsumer<>(properties)) {
			kafkaConsumer.subscribe(Collections.singleton(TOPIC_NAME));

			while (true) {
				ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofSeconds(1));

				for (ConsumerRecord<String, String> record : records) {
					System.out.println("record = " + record);
				}
				kafkaConsumer.commitAsync();
			}
		}
	}
}
