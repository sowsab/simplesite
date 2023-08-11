package com.example.simple.domain.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.simple.common.dto.LoginDTO;
import com.example.simple.domain.auth.dto.ResAdminGetUserDTO;
import com.example.simple.domain.auth.dto.ResAdminGetUserDetailDTO;
import com.example.simple.domain.auth.dto.ResGetPostCommentDTO;
import com.example.simple.domain.auth.dto.ResGetUpdateDTO;
import com.example.simple.domain.auth.service.AuthService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

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

    @GetMapping("/auth/user/detail")
    public String getUserPostList(Model model, HttpSession session) {

        if (session.getAttribute("dto") == null) {
            return "redirect:/";
        }

        ResGetPostCommentDTO dto = authService.getUserPostComment(session);

        model.addAttribute("dto", dto);

        return "auth/user-detail";
    }

    @GetMapping("/auth/user/admin")
    public String getAdminMain(Model model, HttpSession session) {

        if (session.getAttribute("dto") == null) {
            return "redirect:/";
        }

        LoginDTO loginDTO = (LoginDTO) session.getAttribute("dto");

        if (!loginDTO.getUser().getRoleList().contains("ADMIN")) {
            return "redirect:/";
        }

        ResAdminGetUserDTO dto = authService.adminGetUser(session);

        model.addAttribute("dto", dto);

        return "admin/main";
    }

    @GetMapping("/auth/user/admin/user/{userIdx}")
    public String adminGetUserDetail(Model model, HttpSession session, @PathVariable Long userIdx) {

        if (session.getAttribute("dto") == null) {
            return "redirect:/";
        }

        LoginDTO loginDTO = (LoginDTO) session.getAttribute("dto");

        if (!loginDTO.getUser().getRoleList().contains("ADMIN")) {
            return "redirect:/";
        }

        ResAdminGetUserDetailDTO dto = authService.adminGetUserDetail(userIdx, session);

        model.addAttribute("dto", dto);

        return "admin/user-detail";
    }

}
