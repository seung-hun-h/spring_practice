package com.shcorp.voucher.domain;

public enum VoucherType {
	PERCENT {
		@Override
		public Voucher createVoucher(int amount) {
			return new PercentAmountVoucher(amount);
		}
	},
	FIXED {
		@Override
		public Voucher createVoucher(int amount) {
			return new FixedAmountVoucher(new Money(amount));
		}
	},
	DEFAULT{
		@Override
		public Voucher createVoucher(int amount) {
			return new DefaultAmountVoucher();
		}
	};
	public abstract Voucher createVoucher(int amount);
}
