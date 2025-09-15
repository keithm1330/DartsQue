package com.example.dartsque;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/addplayer")
    public String newPlayerPage() {
        return "addPlayer";
    }

}
