package ru.krivonogova.springcourse.securityapp.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import ru.krivonogova.springcourse.securityapp.security.PersonDetails;

@Controller
public class HelloController {
	@GetMapping("/hello")
	public String sayHello() {
		return "hello";
	}
	
    @GetMapping("/showUserInfo")
    public String showUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        System.out.println(personDetails.getPerson());

        return "hello";
    }
    
    @GetMapping("/admin")
    public String adminPage() {
//        adminService.doAdminStuff();
        return "admin";
    }
}
