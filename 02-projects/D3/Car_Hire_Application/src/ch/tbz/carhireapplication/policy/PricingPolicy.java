package ch.tbz.carhireapplication.policy;

import ch.tbz.carhireapplication.domain.Rentable;

import java.util.Map;

public interface PricingPolicy {
    String getId();

    /**
     * @return All information of the rental process.
     */
    Map<String, String> calculateRent(Rentable rentable);
}
