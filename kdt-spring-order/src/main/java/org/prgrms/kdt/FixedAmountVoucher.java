package org.prgrms.kdt;

import java.util.UUID;

public class FixedAmountVoucher implements Voucher{

    private final long amount;
    private final UUID voucherId;

    public FixedAmountVoucher(UUID voucherId, long amount) {
        this.amount = amount;
        this.voucherId = voucherId;
    }

    @Override
    public UUID getVoucherId() {
        return null;
    }

    public long discount(long beforeDiscount) {
        return beforeDiscount - amount;
    }
}
