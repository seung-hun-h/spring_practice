package com.example.springbatchguide.writer;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.PagingQueryProvider;
import org.springframework.batch.item.database.builder.JdbcPagingItemReaderBuilder;
import org.springframework.batch.item.database.support.SqlPagingQueryProviderFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

@Configuration
public class CustomItemWriterJobConfiguration {
	private final JobBuilderFactory jobBuilderFactory;
	private final StepBuilderFactory stepBuilderFactory;
	private final DataSource dataSource;

	public CustomItemWriterJobConfiguration(
		JobBuilderFactory jobBuilderFactory,
		StepBuilderFactory stepBuilderFactory,
		DataSource dataSource
	) {
		this.jobBuilderFactory = jobBuilderFactory;
		this.stepBuilderFactory = stepBuilderFactory;
		this.dataSource = dataSource;
	}

	private static final int chunkSize = 10;

	@Bean
	public Job customItemWriterJob() throws Exception {
		return jobBuilderFactory.get("customItemWriterJob")
			.start(customItemWriterStep())
			.build();
	}

	@Bean
	public Step customItemWriterStep() throws Exception {
		return stepBuilderFactory.get("customItemWriterStep")
			.<Pay, Pay2>chunk(chunkSize)
			.reader(customItemWriterReader())
			.processor(customItemWriterProcessor())
			.writer(customItemWriter())
			.build();
	}

	@Bean
	public JdbcPagingItemReader<Pay> customItemWriterReader() throws Exception {
		Map<String, Object> parameterValues = new HashMap<>();
		parameterValues.put("amount", 2000);

		return new JdbcPagingItemReaderBuilder<Pay>()
			.pageSize(chunkSize)
			.fetchSize(chunkSize)
			.dataSource(dataSource)
			.rowMapper(new BeanPropertyRowMapper<>(Pay.class))
			.queryProvider(createQueryProvider())
			.parameterValues(parameterValues)
			.name("customItemWriterReader")
			.build();
	}

	@Bean
	public PagingQueryProvider createQueryProvider() throws Exception {
		SqlPagingQueryProviderFactoryBean queryProvider = new SqlPagingQueryProviderFactoryBean();
		queryProvider.setDataSource(dataSource); // Database에 맞는 PagingQueryProvider를 선택하기 위해
		queryProvider.setSelectClause("id, amount, tx_name, tx_date_time");
		queryProvider.setFromClause("from pay");
		queryProvider.setWhereClause("where amount >= :amount");

		Map<String, Order> sortKeys = new HashMap<>(1);
		sortKeys.put("id", Order.ASCENDING);

		queryProvider.setSortKeys(sortKeys);

		return queryProvider.getObject();
	}

	@Bean
	public ItemProcessor<Pay, Pay2> customItemWriterProcessor() {
		return pay -> new Pay2(pay.getAmount(), pay.getTxName(), pay.getTxDateTime());
	}

	@Bean
	public ItemWriter<Pay2> customItemWriter() {
		return items -> {
			for (Pay2 item : items) {
				System.out.println(item);
			}
		};
	}
}
