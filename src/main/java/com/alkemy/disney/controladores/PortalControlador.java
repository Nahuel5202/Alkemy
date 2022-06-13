
package com.alkemy.disney.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class PortalControlador {
    
    @GetMapping("/")
    public String inicio(){
        return "index.html";
    }
    
}
