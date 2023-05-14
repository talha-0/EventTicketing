package com.group8.ticket;

import com.group8.appuser.AppUserRepository;
import com.group8.event.Event;
import com.group8.appuser.AppUser;
import com.group8.event.EventRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;


@AllArgsConstructor
@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private AppUserRepository appUserRepository;

    @Transactional
    public void saveTicket(Ticket ticket) {
        ticketRepository.save(ticket);
    }

    @Transactional(readOnly = true)
    public List<Ticket> getTicketsForEvent(Event event) {
        return ticketRepository.findByEvent(event);
    }

    @Transactional(readOnly = true)
    public List<Ticket> getTicketsForUser(AppUser appUser) {
        return ticketRepository.findByAppUser(appUser);
    }
    public List<Ticket> getActiveEventTicketsByUsername(String username) {
        AppUser appUser = appUserRepository.findByEmail(username).get();
        LocalDate now = LocalDate.now();
        return ticketRepository.findByAppUser(appUser);
    }


}
