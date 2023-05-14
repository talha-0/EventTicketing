package com.group8.ticket;

import com.group8.event.Event;
import com.group8.appuser.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    List<Ticket> findByEvent(Event event);

    List<Ticket> findByAppUser(AppUser appUser);

    List<Ticket> findByAppUserAndEventEndDateGreaterThanEqual(AppUser appUser, LocalDate now);
}
