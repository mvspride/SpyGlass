package com.codedifferently.groupone.SpyGlass.controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/login")
public class LoginController {

    @GetMapping
    public RedirectView redirectToFrontend(RedirectAttributes attributes) throws ServletException {
        attributes.addFlashAttribute("flashAttribute", "login");
        attributes.addAttribute("attribute", "login");
        return new RedirectView("http://localhost:3000/admin/dashboard");
    }
}