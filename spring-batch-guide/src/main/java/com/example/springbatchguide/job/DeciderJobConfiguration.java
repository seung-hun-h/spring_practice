package com.example.springbatchguide.job;

import java.util.concurrent.ThreadLocalRandom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DeciderJobConfiguration {
	private static final Logger logger = LoggerFactory.getLogger(DeciderJobConfiguration.class);
	private final JobBuilderFactory jobBuilderFactory;
	private final StepBuilderFactory stepBuilderFactory;

	public DeciderJobConfiguration(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
		this.jobBuilderFactory = jobBuilderFactory;
		this.stepBuilderFactory = stepBuilderFactory;
	}

	@Bean
	public Job deciderJob() {
		return jobBuilderFactory.get("deciderJob")
			.start(startStep())
			.next(decider())
			.from(decider())
				.on("ODD")
				.to(oddStep())
			.from(decider())
				.on("EVEN")
				.to(evenStep())
			.end()
			.build();
	}

	@Bean
	public Step startStep() {
		return stepBuilderFactory.get("startStep")
			.tasklet(((contribution, chunkContext) -> {
				logger.info(">>> star!!");
				return RepeatStatus.FINISHED;
			}))
			.build();
	}

	@Bean
	public Step evenStep() {
		return stepBuilderFactory.get("evenStep")
			.tasklet(((contribution, chunkContext) -> {
				logger.info(">>> 짝수입니다!!");
				return RepeatStatus.FINISHED;
			}))
			.build();
	}

	@Bean
	public Step oddStep() {
		return stepBuilderFactory.get("oddStep")
			.tasklet(((contribution, chunkContext) -> {
				logger.info(">>> 홀수입니다!!");
				return RepeatStatus.FINISHED;
			}))
			.build();
	}

	@Bean
	public JobExecutionDecider decider() {
		return new OddDecider();
	}

	public static class OddDecider implements JobExecutionDecider {

		@Override
		public FlowExecutionStatus decide(JobExecution jobExecution, StepExecution stepExecution) {
			ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();

			int randNumber = threadLocalRandom.nextInt(1, 51);
			logger.info("랜덤 숫자: {}", randNumber);

			if (randNumber % 2 == 0) {
				return new FlowExecutionStatus("EVEN");
			}
			return new FlowExecutionStatus("ODD");
		}
	}
}
