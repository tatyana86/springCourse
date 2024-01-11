package ru.krivonogova.springcourse.RestAppTemperatureSensor.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.krivonogova.springcourse.RestAppTemperatureSensor.models.Measurement;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {

}
