package org.prgrms.kdt;

import java.util.UUID;

public class PersentDiscountVoucher implements Voucher {

    private final UUID voucherId;
    private final long percent;

    public PersentDiscountVoucher(UUID voucherId, long percent) {
        this.voucherId = voucherId;
        this.percent = percent;
    }

    @Override
    public UUID getVoucherId() {
        return voucherId;
    }

    @Override
    public long discount(long beforeDiscount) {
        return beforeDiscount * (percent / 100);
    }
}
