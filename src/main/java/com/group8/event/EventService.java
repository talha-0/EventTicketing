package com.group8.event;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
@AllArgsConstructor
@Transactional
@Service
public class EventService {

    private final EventRepository eventRepository;
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Optional<Event> getEventById(Long id) {
        return eventRepository.findById(id);
    }

    public Event saveEvent(Event event) {
        return eventRepository.save(event);
    }

    public void deleteEvent(Event event) {
        eventRepository.delete(event);
    }
    public List<Event> getPendingEvents() {
        return eventRepository.findByStatus("Pending");
    }

    public List<Event> getOngoingEvents() {
        LocalDate now = LocalDate.now();
        return eventRepository.findByStartDateLessThanEqualAndEndDateGreaterThanEqual(now, now);
    }

    public List<Event> getHostEvents(String username) {
        return eventRepository.findByAppUserEmail(username);
    }

}
