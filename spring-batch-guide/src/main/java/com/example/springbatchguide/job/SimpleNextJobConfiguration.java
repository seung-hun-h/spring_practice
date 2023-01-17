package com.example.springbatchguide.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SimpleNextJobConfiguration {
	private static final Logger logger = LoggerFactory.getLogger(SimpleNextJobConfiguration.class);
	private final JobBuilderFactory jobBuilderFactory;
	private final StepBuilderFactory stepBuilderFactory;

	public SimpleNextJobConfiguration(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
		this.jobBuilderFactory = jobBuilderFactory;
		this.stepBuilderFactory = stepBuilderFactory;
	}

	@Bean
	public Job simpleNextJob() {
		return jobBuilderFactory.get("simpleNextJob")
			.start(step1())
			.next(step2())
			.next(step3())
			.build();
	}

	@Bean
	public Step step1() {
		return stepBuilderFactory.get("step1")
			.tasklet(((contribution, chunkContext) -> {
				logger.info(">>> This is step1");
				return RepeatStatus.FINISHED;
			}))
			.build();
	}

	@Bean
	public Step step2() {
		return stepBuilderFactory.get("step2")
			.tasklet(((contribution, chunkContext) -> {
				logger.info(">>> This is step2");
				return RepeatStatus.FINISHED;
			}))
			.build();
	}

	@Bean
	public Step step3() {
		return stepBuilderFactory.get("step3")
			.tasklet(((contribution, chunkContext) -> {
				logger.info(">>> This is step3");
				return RepeatStatus.FINISHED;
			}))
			.build();
	}
}
