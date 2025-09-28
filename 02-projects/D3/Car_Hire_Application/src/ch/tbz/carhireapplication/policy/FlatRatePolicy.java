package ch.tbz.carhireapplication.policy;

import ch.tbz.carhireapplication.domain.Rentable;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedHashMap;
import java.util.Map;

public class FlatRatePolicy implements PricingPolicy {
    @Override
    public String getId() {
        return "Flat Rate Policy";
    }

    // @@@@@@ this was done with ai @@@@@@
    @Override
    public Map<String, String> calculateRent(Rentable rentable) {
        BigDecimal base = BigDecimal
                .valueOf(rentable.getFullPrice())      // if already BigDecimal, adjust accordingly
                .setScale(2, RoundingMode.HALF_UP);

        Map<String, String> out = new LinkedHashMap<>();
        out.put("policy", getId());
        out.put("basePrice", base.toPlainString());

        if (base.compareTo(BigDecimal.ZERO) <= 0) {
            out.put("months", "0");
            out.put("monthlyCost", "0.00");
            out.put("lastPayment", "0.00");
            out.put("total", base.toPlainString());
            return out;
        }

        // Pick months automatically: aim for about 100 per month, clamp to [1..60]
        int months = (int) Math.ceil(base.doubleValue() / 100.0);
        months = Math.max(1, Math.min(60, months));

        BigDecimal monthly = base
                .divide(BigDecimal.valueOf(months), 2, RoundingMode.HALF_UP);

        BigDecimal last = base
                .subtract(monthly.multiply(BigDecimal.valueOf(months - 1)))
                .setScale(2, RoundingMode.HALF_UP);

        out.put("months", Integer.toString(months));
        out.put("monthlyCost", monthly.toPlainString());
        out.put("lastPayment", last.toPlainString());
        out.put("total", base.toPlainString());

        return out;
    }
}
