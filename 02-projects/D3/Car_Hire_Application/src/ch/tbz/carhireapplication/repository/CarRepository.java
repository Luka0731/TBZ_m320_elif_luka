package ch.tbz.carhireapplication.repository;

import ch.tbz.carhireapplication.domain.Car;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

// todo: make this class fr
// todo: make a RepositoryImpl class to make everything easier
// ####### mocked class #######
public class CarRepository implements Repository<Car> {
    List<Car> cars;

    public CarRepository(String path) {
        // ####### creating mock data #######
        cars = new ArrayList<>();
        cars.add(new Car("Ferrari", "E23", "12Baka", 2020, 9500750.00, 4, "Benzin"));
        cars.add(new Car("Mercedes", "11F", "OiOii", 1981, 7900000.95, 5, "Roland"));
    }

    // ####### mock method #######
    @Override
    public Car getById(UUID id) {
        for (Car car : cars) {
            if (car.getId().equals(id)) {
                return car;
            }
        }
        return null;
    }

    // ####### mock method #######
    @Override
    public List<Car> getAll() {
        return cars;
    }
}
