package ch.tbz.carhireapplication.service;

import ch.tbz.carhireapplication.domain.Rentable;
import ch.tbz.carhireapplication.policy.PricingPolicy;
import ch.tbz.carhireapplication.repository.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public abstract class PaymentGatewayImpl<T extends Rentable> implements PaymentGateway<T> {
    protected Map<String, PricingPolicy> pricingPolicyMap;
    protected Repository<T> repository;

    public PaymentGatewayImpl(Repository<T> repository) {
        this.repository = repository;
    }

    @Override
    public void setPricingPolicyMap(Map<String, PricingPolicy> pricingPolicyMap) {
        this.pricingPolicyMap = pricingPolicyMap;
    }

    @Override
    public List<T> getAll() {
        return repository.getAll();
    }

    @Override
    public List<T> getAllByAvailability(boolean isAvailable) {
        List<T> rentables = repository.getAll();
        List<T> rentablesByAvailability = new ArrayList<>();
        for  (T rentable : rentables) {
            if(rentable.isAvailable() == isAvailable) {
                rentablesByAvailability.add(rentable);
            }
        }
        return rentablesByAvailability;
    }

    @Override
    public Map<String, String> rentById(UUID id) {
        T rentable = repository.getById(id);
        PricingPolicy pricingPolicy = decidePricingPolicy(rentable);
        rentable.setIsAvailable(false);
        return pricingPolicy.calculateRent(rentable);
    }

    public abstract PricingPolicy decidePricingPolicy(T rentable);

    @Override
    public void cancelRentById(UUID id) {
        T rentable = repository.getById(id);
        rentable.setIsAvailable(true);
    }
}
