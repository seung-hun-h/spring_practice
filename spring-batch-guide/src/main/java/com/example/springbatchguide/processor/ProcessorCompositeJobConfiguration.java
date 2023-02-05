package com.example.springbatchguide.processor;

import java.util.HashMap;
import java.util.List;
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
import org.springframework.batch.item.support.CompositeItemProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

@Configuration
public class ProcessorCompositeJobConfiguration {

	public static final String JOB_NAME = "ProcessorCompositeBatch";
	public static final String BEAN_PREFIX = JOB_NAME + "_";

	private static final Logger log = LoggerFactory.getLogger(ProcessorCompositeJobConfiguration.class);

	private final JobBuilderFactory jobBuilderFactory;
	private final StepBuilderFactory stepBuilderFactory;
	private final DataSource dataSource;

	@Value("${chunkSize:1000}")
	private int chunkSize;

	public ProcessorCompositeJobConfiguration(
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
			.processor(compositeProcessor())
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

	@Bean(BEAN_PREFIX + "compositeProcessor")
	public CompositeItemProcessor compositeProcessor() {
		List<ItemProcessor> delegates = List.of(processor1(), processor2());

		CompositeItemProcessor compositeItemProcessor = new CompositeItemProcessor();
		compositeItemProcessor.setDelegates(delegates);
		return compositeItemProcessor;
	}

	private ItemProcessor<Teacher, String> processor1() {
		return Teacher::getName;
	}

	private ItemProcessor<String, String> processor2() {
		return item -> "안녕하세요. " + item + " 선생입니다.";
	}

	@Bean(BEAN_PREFIX + "writer")
	public ItemWriter<String> writer() {
		return items -> {
			for (String item : items) {
				log.info("Teacher Name={}", item);
			}
		};
	}
}