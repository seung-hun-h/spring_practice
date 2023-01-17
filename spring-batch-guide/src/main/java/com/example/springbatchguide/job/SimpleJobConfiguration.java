package com.example.springbatchguide.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Configuration
public class SimpleJobConfiguration {
	private static final Logger logger = LoggerFactory.getLogger(SimpleJobConfiguration.class);
	private final JobBuilderFactory jobBuilderFactory;
	private final StepBuilderFactory stepBuilderFactory;

	public SimpleJobConfiguration(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
		this.jobBuilderFactory = jobBuilderFactory;
		this.stepBuilderFactory = stepBuilderFactory;
	}

	@Bean
	public Job simpleJob() {
		return jobBuilderFactory.get("simpleJob")
			.start(simpleStep1(null)) // 1
			.build();
	}

	@Bean
	@JobScope // 2
	public Step simpleStep1(
		@Value("#{jobParameters[requestDate]}") String requestDate // 3
	) {
		return stepBuilderFactory.get("simpleStep1")
			.tasklet(((contribution, chunkContext) -> {
				logger.info(">>> This is step1");
				logger.info(">> Request Date: {}", requestDate); // 4
				return RepeatStatus.FINISHED;
			}))
			.build();
	}

}
