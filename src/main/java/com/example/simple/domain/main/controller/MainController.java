package com.example.simple.domain.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.simple.domain.main.dto.ResMainPostDTO;
import com.example.simple.domain.main.dto.ResPostDTO;
import com.example.simple.domain.main.service.MainService;

import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {
    
    @Autowired
    private MainService mainService;

    @GetMapping("/")
    public String getMainPage(Model model) {
        ResMainPostDTO dto = mainService.getPostListData();
        model.addAttribute("dto", dto);
        return "main";
    }

    @GetMapping("/post/{postIdx}")
    public String getPostPage(Model model, @PathVariable Long postIdx) {
        ResPostDTO dto = mainService.getPostData(postIdx);
        model.addAttribute("dto", dto);

        return "post/post";
    }

    @GetMapping("/post-write")
    private String getPostWrite(Model model, HttpSession session) {

        if (session.getAttribute("dto") == null) {
            return "redirect:/";
        }

        return "post/post-write";
    }

}
