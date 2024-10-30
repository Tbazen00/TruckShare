package com.csc340.truckshare;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebappController {

    @GetMapping({"", "/", "/home", "/index"})
    public String home(){
        return "index";
    }
    
}
