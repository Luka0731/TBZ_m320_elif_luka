package ch.tbz.carhireapplication.app;

import ch.tbz.carhireapplication.domain.Rentable;
import ch.tbz.carhireapplication.policy.DiscountPolicy;
import ch.tbz.carhireapplication.policy.FlatRatePolicy;
import ch.tbz.carhireapplication.policy.PricingPolicy;
import ch.tbz.carhireapplication.repository.CarRepository;
import ch.tbz.carhireapplication.service.CarPaymentGateway;
import ch.tbz.carhireapplication.service.PaymentGateway;
import ch.tbz.carhireapplication.util.PaymentGatewayRegistry;
import ch.tbz.carhireapplication.ui.Client;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is the starting point and also puts all the Parts together.
 */
public class App {
    public static void main(String[] args) {
        // Make repositorys
        CarRepository carRepository = new CarRepository("data");

        // Make Policies
        List<PricingPolicy> pricingPolicies = new ArrayList<>();
        pricingPolicies.add(new FlatRatePolicy());
        pricingPolicies.add(new DiscountPolicy());

        // Make payment gateways
        List<PaymentGateway<? extends Rentable>> paymentGateways = new ArrayList<>();
        paymentGateways.add(new CarPaymentGateway(carRepository));

        // Create the registry
        PaymentGatewayRegistry registry = new PaymentGatewayRegistry(paymentGateways, pricingPolicies);

        // Make client and run it
        Client client = new Client(registry);
        client.run();
    }
}
