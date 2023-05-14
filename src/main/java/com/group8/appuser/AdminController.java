package com.group8.appuser;

import com.group8.event.EventService;
import com.group8.event.Event;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping( "/admin")
@AllArgsConstructor
public class AdminController {
    private final EventService eventService;
    private AppUserRepository appUserRepository;

    @GetMapping("/home")
    public String showHomePage(Model model) {
        List<Event> pendingEvents = eventService.getPendingEvents();
        model.addAttribute("pendingEvents", pendingEvents);

        return "adminHome"; // returns the name of your home page HTML template
    }
    @GetMapping("/disable-user")
    public String showDisableUserPage(Model model) {
        // retrieve the ResourceRequest records for the specific employee
        List<Host> appUsers = appUserRepository.findByEnabledTrue();

        model.addAttribute("appUsers", appUsers);

        return "disableUser"; // returns the name of your home page HTML template
    }
    @PostMapping("/disable-user")
    public String disableUser(@RequestParam("id") String email) {
        appUserRepository.disableAppUser(email);

        return "redirect:/admin/disable-user"; // returns the name of your home page HTML template
    }
    @GetMapping("/enable-user")
    public String showEnableUserPage(Model model) {
        // retrieve the ResourceRequest records for the specific employee
        List<Host> appUsers = appUserRepository.findByEnabledFalse();

        model.addAttribute("appUsers", appUsers);

        return "enableUser"; // returns the name of your home page HTML template
    }
    @PostMapping("/enable-user")
    public String enableUser(@RequestParam("id") String email) {
        appUserRepository.enableAppUser(email);

        return "redirect:/admin/enable-user"; // returns the name of your home page HTML template
    }
}