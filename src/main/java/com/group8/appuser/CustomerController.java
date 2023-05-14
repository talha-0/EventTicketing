package com.group8.appuser;

import com.group8.event.Event;
import com.group8.event.EventRepository;
import com.group8.event.EventService;
import com.group8.ticket.*;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RequestMapping( "/customer")
@AllArgsConstructor
@Controller
public class CustomerController {
    private final EventService eventService;
    private final TicketService ticketService;
    private final AppUserRepository appUserRepository;
    private final EventRepository eventRepository;
    private final TicketRepository ticketRepository;
    private final AppUserService appUserService;

    @GetMapping("/home")
    public String showOngoingEvents(Authentication authentication,Model model) {
        String username = authentication.getName();
        UserDetails userDetails = appUserService.loadUserByUsername(username);
        AppUser appUser = (AppUser) userDetails;
        List<Event> ongoingEvents = eventService.getOngoingEvents();
        model.addAttribute("fName", appUser.getFirstName());
        model.addAttribute("events", ongoingEvents);

        return "ongoingEvents"; // returns the name of your ongoing events HTML template
    }
    @GetMapping("/tickets")
    public String showActiveEventTickets(Model model, Authentication authentication) {
        String username = authentication.getName();
        List<Ticket> activeTickets = ticketService.getActiveEventTicketsByUsername(username);
            activeTickets.sort(Comparator.comparing(Ticket::getEventDate));
            model.addAttribute("activeTickets", activeTickets);

        return "tickets";
    }

    @PostMapping("/bookTicket/{eventId}")
    public String bookTicket(@PathVariable Long eventId, Authentication authentication) {
        AppUser appUser = appUserRepository.findByEmail(authentication.getName()).get();

        Optional<Event> optionalEvent = eventRepository.findById(eventId);
        if (!optionalEvent.isPresent()) {
            return "redirect:/customer/home";
        }

        Event event = optionalEvent.get();

        if (!event.getStatus().equals("Active")) {
            return "redirect:/customer/home";
        }

        Ticket ticket = new Ticket();
        ticket.setEvent(event);
        ticket.setAppUser(appUser);

        ticketRepository.save(ticket);

        return "redirect:/customer/tickets";
    }

}
