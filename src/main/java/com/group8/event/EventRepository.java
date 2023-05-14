package com.group8.event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findByStatus(String status);

    List<Event> findByStartDateLessThanEqualAndEndDateGreaterThanEqual(LocalDate now, LocalDate now1);
}

