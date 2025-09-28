package ch.tbz.carhireapplication.service;

import ch.tbz.carhireapplication.domain.Car;
import ch.tbz.carhireapplication.policy.PricingPolicy;
import ch.tbz.carhireapplication.repository.CarRepository;

public class CarPaymentGateway extends PaymentGatewayImpl<Car>{
    public CarPaymentGateway(CarRepository carRepository) {
        super(carRepository);
    }

    @Override
    public String getId() {
        return "Car";
    }

    @Override
    public PricingPolicy decidePricingPolicy(Car car) {
        int age = java.time.Year.now().getValue() - car.getYear();
        return age >= 20 ? pricingPolicyMap.get("Flat Rate Policy") : pricingPolicyMap.get("Discount Policy");
    }
}
