package com.sy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PageController {
    
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    
    @GetMapping("/join")
    public String join() {
        return "join";
    }
    
    @GetMapping("/shelf")
    public String shelf() {
        return "shelf";
    }

    @GetMapping("/book/{id}")
    public String bookDetail(@PathVariable Long id) {
        return "book-detail";
    }
} 