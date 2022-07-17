package hello.core.optional;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class OptionalTest {
    
    @Test
    @DisplayName("Optional Stream 테스트")
    void optionalStream() {
        // given
        List<Person> people = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Person person = new Person();
            Car car = new Car();
            Insurance insurance = new Insurance();
            insurance.setName("insurance" + i);
            car.setInsurance(Optional.of(insurance));
            person.setCar(Optional.of(car));

            people.add(person);
        }

        // when
        people.stream()
                .map(Person::getCar)
                .map(optCar -> optCar.flatMap(Car::getInsurance))
                .map(optInsurance -> optInsurance.map(Insurance::getName))
                .flatMap(Optional::stream) // Stream<String>
                .collect(Collectors.toSet());
        // then

    }

    @Data
    class Person {
        private Optional<Car> car;
    }

    @Data
    class Car {
        private Optional<Insurance> insurance;
    }

    @Data
    class Insurance {
        private String name;
    }
}
