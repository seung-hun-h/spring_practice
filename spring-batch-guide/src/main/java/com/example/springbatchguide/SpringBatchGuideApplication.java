package com.example.springbatchguide;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

import com.example.springbatchguide.job.DeciderJobConfiguration;
import com.example.springbatchguide.job.ScopeJobConfiguration;
import com.example.springbatchguide.job.SimpleJobConfiguration;
import com.example.springbatchguide.job.SimpleNextConditionalJobConfiguration;
import com.example.springbatchguide.job.SimpleNextJobConfiguration;


@EnableBatchProcessing // 배치 기능 활성화
@SpringBootApplication(scanBasePackages = "com.example.springbatchguide.processor")
public class SpringBatchGuideApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBatchGuideApplication.class, args);
	}

}
