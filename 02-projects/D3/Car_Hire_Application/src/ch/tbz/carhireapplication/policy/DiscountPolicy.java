package ch.tbz.carhireapplication.policy;

import ch.tbz.carhireapplication.domain.Rentable;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedHashMap;
import java.util.Map;

public class DiscountPolicy implements PricingPolicy {
    @Override
    public String getId() {
        return "Discount Policy";
    }

    // @@@@@@ this was done with ai @@@@@@
    @Override
    public Map<String, String> calculateRent(Rentable rentable) {
        BigDecimal base = BigDecimal.valueOf(rentable.getFullPrice()).setScale(2, RoundingMode.HALF_UP);

        Map<String, String> out = new LinkedHashMap<>();
        out.put("basePrice", base.toPlainString());

        if (base.signum() <= 0) {
            out.put("policy", getId());
            out.put("discountPct", "0");
            out.put("discountAmount", "0.00");
            out.put("finalPrice", base.toPlainString());
            out.put("months", "0");
            out.put("monthly", "0.00");
            out.put("last", "0.00");
            out.put("total", base.toPlainString());
            return out;
        }

        // automatisch: kleiner, mittlerer, grosser Rabatt – nur vom Preis abhängig
        BigDecimal rate =
                base.compareTo(new BigDecimal("1000")) >= 0 ? new BigDecimal("0.10") :
                        base.compareTo(new BigDecimal("300"))  >= 0 ? new BigDecimal("0.05") :
                                BigDecimal.ZERO;

        BigDecimal discount   = base.multiply(rate).setScale(2, RoundingMode.HALF_UP);
        BigDecimal finalPrice = base.subtract(discount).setScale(2, RoundingMode.HALF_UP);

        // Monate automatisch (≈120/Monat), gekappt 1..36
        int months = Math.min(36, Math.max(1, (int)Math.ceil(finalPrice.doubleValue() / 120.0)));

        BigDecimal monthly = finalPrice.divide(BigDecimal.valueOf(months), 2, RoundingMode.HALF_UP);
        BigDecimal last    = finalPrice.subtract(monthly.multiply(BigDecimal.valueOf(months - 1)))
                .setScale(2, RoundingMode.HALF_UP);

        out.put("policy", getId());
        out.put("discountPct", rate.multiply(new BigDecimal("100")).stripTrailingZeros().toPlainString()); // z.B. "5" oder "10"
        out.put("discountAmount", discount.toPlainString());
        out.put("finalPrice", finalPrice.toPlainString());
        out.put("months", Integer.toString(months));
        out.put("monthly", monthly.toPlainString()); // Standardrate
        out.put("last", last.toPlainString());       // letzte, angepasste Rate
        out.put("total", finalPrice.toPlainString()); // Summe aller Raten

        return out;
    }
}
