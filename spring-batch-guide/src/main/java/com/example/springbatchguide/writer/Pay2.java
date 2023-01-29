package com.example.springbatchguide.writer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

public class Pay2 {
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");

	@Id
	private Long id;
	@Column(value = "amount")
	private Long amount;
	@Column(value = "tx_name")
	private String txName;
	@Column(value = "tx_date_time")
	private LocalDateTime txDateTime;

	public Pay2() {
	}

	public Pay2(Long amount, String txName, LocalDateTime txDateTime) {
		this(0L, amount, txName, txDateTime);
	}

	public Pay2(Long id, Long amount, String txName, LocalDateTime txDateTime) {
		this.id = id;
		this.amount = amount;
		this.txName = txName;
		this.txDateTime = txDateTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public String getTxName() {
		return txName;
	}

	public void setTxName(String txName) {
		this.txName = txName;
	}

	public LocalDateTime getTxDateTime() {
		return txDateTime;
	}

	public void setTxDateTime(LocalDateTime txDateTime) {
		this.txDateTime = txDateTime;
	}

	@Override
	public String toString() {
		return "Pay2{" +
			"id=" + id +
			", amount=" + amount +
			", txName='" + txName + '\'' +
			", txDateTime=" + txDateTime +
			'}';
	}
}
