package com.example.springbatchguide.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ScopeJobConfiguration {
	private static final Logger logger = LoggerFactory.getLogger(ScopeJobConfiguration.class);
	private final JobBuilderFactory jobBuilderFactory;
	private final StepBuilderFactory stepBuilderFactory;

	public ScopeJobConfiguration(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
		this.jobBuilderFactory = jobBuilderFactory;
		this.stepBuilderFactory = stepBuilderFactory;
	}

	@Bean
	public Job scopeJob() {
		return jobBuilderFactory.get("scopeJob")
			.start(scopeStep1(null)) // 1
			.next(scopeStep2())
			.build();
	}

	@Bean
	public Step scopeStep2() {
		return stepBuilderFactory.get("scopeStep2")
			.tasklet(scopeStep2Tasklet(null))
			.build();
	}

	@Bean
	@StepScope
	public static Tasklet scopeStep2Tasklet(@Value("#{jobParameters[requestDate]}") String requestDate) {
		return (contribution, chunkContext) -> {
			logger.info(">>>> This is scopeStep2");
			logger.info(">>>> requestDate is {}", requestDate);
			return RepeatStatus.FINISHED;
		};
	}

	@Bean
	@JobScope
	public Step scopeStep1(@Value("#{jobParameters[requestDate]}") String requestDate) {
		return stepBuilderFactory.get("scopeStep1")
			.tasklet(((contribution, chunkContext) -> {
				logger.info(">>>> This is scopeStep1");
				logger.info(">>>> requestDate is {}", requestDate);
				return RepeatStatus.FINISHED;
			}))
			.build();
	}


}
