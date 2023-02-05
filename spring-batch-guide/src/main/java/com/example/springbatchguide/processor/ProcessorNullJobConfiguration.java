package com.example.springbatchguide.processor;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.PagingQueryProvider;
import org.springframework.batch.item.database.builder.JdbcPagingItemReaderBuilder;
import org.springframework.batch.item.database.support.SqlPagingQueryProviderFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

@Configuration
public class ProcessorNullJobConfiguration {

	public static final String JOB_NAME = "ProcessorNullBatch";
	public static final String BEAN_PREFIX = JOB_NAME + "_";

	private static final Logger log = LoggerFactory.getLogger(ProcessorNullJobConfiguration.class);

	private final JobBuilderFactory jobBuilderFactory;
	private final StepBuilderFactory stepBuilderFactory;
	private final DataSource dataSource;

	@Value("${chunkSize:1000}")
	private int chunkSize;

	public ProcessorNullJobConfiguration(
		JobBuilderFactory jobBuilderFactory,
		StepBuilderFactory stepBuilderFactory,
		DataSource dataSource
	) {
		this.jobBuilderFactory = jobBuilderFactory;
		this.stepBuilderFactory = stepBuilderFactory;
		this.dataSource = dataSource;
	}

	@Bean(JOB_NAME)
	public Job job() throws Exception {
		return jobBuilderFactory.get(JOB_NAME)
			.preventRestart()
			.start(step())
			.build();
	}

	@Bean(BEAN_PREFIX + "step")
	@JobScope
	public Step step() throws Exception {
		return stepBuilderFactory.get(BEAN_PREFIX + "step")
			.<Teacher, Teacher>chunk(chunkSize)
			.reader(reader())
			.processor(processor())
			.writer(writer())
			.build();
	}

	@Bean(BEAN_PREFIX + "reader")
	public JdbcPagingItemReader<Teacher> reader() throws Exception {
		return new JdbcPagingItemReaderBuilder<Teacher>()
			.pageSize(chunkSize)
			.fetchSize(chunkSize)
			.dataSource(dataSource)
			.rowMapper(new BeanPropertyRowMapper<>(Teacher.class))
			.queryProvider(createQueryProvider())
			.name("jdbcPagingItemReader")
			.build();
	}

	@Bean(BEAN_PREFIX + "queryProvider")
	public PagingQueryProvider createQueryProvider() throws Exception {
		SqlPagingQueryProviderFactoryBean queryProvider = new SqlPagingQueryProviderFactoryBean();
		queryProvider.setDataSource(dataSource); // Database에 맞는 PagingQueryProvider를 선택하기 위해
		queryProvider.setSelectClause("id, name");
		queryProvider.setFromClause("from teacher");

		Map<String, Order> sortKeys = new HashMap<>(1);
		sortKeys.put("id", Order.ASCENDING);

		queryProvider.setSortKeys(sortKeys);

		return queryProvider.getObject();
	}

	@Bean(BEAN_PREFIX + "processor")
	public ItemProcessor<Teacher, Teacher> processor() {
		return teacher -> {
			boolean isIgnoredTarget = teacher.getId() % 2 == 0L;

			if(isIgnoredTarget){
				log.info(">>>>>>>>> Teacher name={}, isIgnoreTarget={}", teacher.getName(), isIgnoredTarget);
				return null;
			}
			return teacher;
		};
	}

	@Bean(BEAN_PREFIX + "writer")
	public ItemWriter<Teacher> writer() {
		return items -> {
			for (Teacher item : items) {
				log.info("Teacher Id = {}, Name={}", item.getId(), item.getName());
			}
		};
	}
}