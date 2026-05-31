package com.jeimandei.imanuelbytes.gateway.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PublicPageController {

    @GetMapping("/")
    public String home() { return "public/index"; }

    @GetMapping("/about")
    public String about() { return "public/about"; }

    @GetMapping("/services")
    public String services() { return "public/services"; }

    @GetMapping("/sermons")
    public String sermons() { return "public/sermons"; }

    @GetMapping("/events")
    public String events() { return "public/events"; }

    @GetMapping("/livestream")
    public String livestream() { return "public/livestream"; }

    @GetMapping("/ministries")
    public String ministries() { return "public/ministries"; }

    @GetMapping("/contact")
    public String contact() { return "public/contact"; }

    @GetMapping("/prayer-request")
    public String prayerRequest() { return "public/prayer-request"; }

    @GetMapping("/giving")
    public String giving() { return "public/giving"; }

    @GetMapping("/news")
    public String news() { return "public/news"; }

    @GetMapping("/gallery")
    public String gallery() { return "public/gallery"; }

    @GetMapping("/leadership")
    public String leadership() { return "public/leadership"; }

    @GetMapping("/new-here")
    public String newHere() { return "public/new-here"; }

    @GetMapping("/faq")
    public String faq() { return "public/faq"; }

    @GetMapping("/login")
    public String login() { return "auth/login"; }

    @GetMapping("/register")
    public String register() { return "auth/register"; }
}
