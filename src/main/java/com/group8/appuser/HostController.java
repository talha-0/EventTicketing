package com.group8.appuser;

import com.group8.event.EventService;
import com.group8.ticket.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping( "/employee")
@AllArgsConstructor
public class HostController {
    private AppUserService appUserService;
    private final EventService eventService;
    private final TicketService ticketService;
      @GetMapping("/home")
    public String showHomePage(Authentication authentication, Model model) {
        // retrieve the user's information from the Authentication object
        String username = authentication.getName();
        UserDetails userDetails = appUserService.loadUserByUsername(username);
        AppUser appUser = (AppUser) userDetails;
        // retrieve the ResourceRequest records for the specific employee
        // add the user's information and ResourceRequest records to the model
        model.addAttribute("fName", appUser.getFirstName());

        return "ongoingEvents"; // returns the name of your home page HTML template
    }
}