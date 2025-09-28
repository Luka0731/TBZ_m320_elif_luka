package ch.tbz.carhireapplication.service;

import ch.tbz.carhireapplication.domain.Rentable;
import ch.tbz.carhireapplication.policy.PricingPolicy;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Basically the Service Class.
 */
public interface PaymentGateway<T extends Rentable> {
    String getId();

    void setPricingPolicyMap(Map<String, PricingPolicy> pricingPolicyMap);

    List<T> getAll();

    List<T> getAllByAvailability(boolean isAvailable);

    /**
     * @return All information of the rental process.
     */
    Map<String, String> rentById(UUID id);

    void cancelRentById(UUID id);
}
