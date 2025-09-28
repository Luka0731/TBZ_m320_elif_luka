package ch.tbz.carhireapplication.util;

import ch.tbz.carhireapplication.domain.Rentable;
import ch.tbz.carhireapplication.policy.PricingPolicy;
import ch.tbz.carhireapplication.service.PaymentGateway;

import java.util.*;

public class PaymentGatewayRegistry {
    private final Map<String, PaymentGateway<? extends Rentable>> paymentGatewayMap = new LinkedHashMap<>();

    public PaymentGatewayRegistry(List<PaymentGateway<? extends Rentable>> paymentGateways, List<PricingPolicy> pricingPolicies) {
        Map<String, PricingPolicy> pricingPolicyMap = new LinkedHashMap<>();
        for (PricingPolicy pricingPolicy : pricingPolicies) pricingPolicyMap.put(pricingPolicy.getId(), pricingPolicy);
        for (PaymentGateway<? extends Rentable> paymentGateway : paymentGateways) {
            paymentGatewayMap.put(paymentGateway.getId(), paymentGateway);
            paymentGateway.setPricingPolicyMap(pricingPolicyMap);
        }
    }

    public List<PaymentGateway<? extends Rentable>> getAllPaymentGateways() {
        return List.copyOf(paymentGatewayMap.values());
    }

    public List<String> getAllIds() {
        return new ArrayList<>(paymentGatewayMap.keySet()); // must be arraylist, otherwise immutable error in client
    }
}
