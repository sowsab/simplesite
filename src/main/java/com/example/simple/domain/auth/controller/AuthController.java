package com.example.simple.domain.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.simple.domain.auth.dto.ResGetUpdateDTO;
import com.example.simple.domain.auth.service.AuthService;

import jakarta.servlet.http.HttpSession;


@Controller
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping("/auth/login")
    public String login(Model model) {

        return "auth/login";
    }

    @GetMapping("/auth/join")
    public String join(Model model) {

        return "auth/join";
    }

    @GetMapping("/auth/update")
    public String update(Model model, HttpSession session) {

        if (session.getAttribute("dto") == null) {
            return "redirect:/";
        }

        ResGetUpdateDTO dto = authService.getUpdateUser(session);

        model.addAttribute("dto", dto);

        return "auth/update";
    }

    @GetMapping("/auth/logout")
    public String logout(HttpSession session) {

        session.invalidate();

        return "redirect:/";
    }
    
    
    
}
