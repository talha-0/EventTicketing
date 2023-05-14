package com.group8.appuser;

import com.group8.event.Event;
import com.group8.event.EventService;
import com.group8.ticket.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping( "/host")
@AllArgsConstructor
public class HostController {
    private AppUserService appUserService;
    private final EventService eventService;
    private final TicketService ticketService;
    @GetMapping("/home")
    public String showHostEvents(Model model, Authentication authentication) {
        String username = authentication.getName();
        List<Event> events = eventService.getHostEvents(username);

        model.addAttribute("events", events);

        return "hostHome";
    }
    @GetMapping("/addEvent")
    public String showAddEventForm(Model model) {
        Event event = new Event();
        model.addAttribute("event", event);
        return "addEvent";
    }

    @PostMapping("/addEvent")
    public String addEvent(@ModelAttribute("event") Event event, Authentication authentication) {
        String username = authentication.getName();
        UserDetails userDetails = appUserService.loadUserByUsername(username);
        AppUser appUser = (AppUser) userDetails;
        event.setAppUser(appUser);
        eventService.saveEvent(event);
        return "redirect:/host/home";
    }
}