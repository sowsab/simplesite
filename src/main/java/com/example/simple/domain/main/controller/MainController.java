package com.example.simple.domain.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.simple.domain.main.dto.ResGetCommentUpdateDataDTO;
import com.example.simple.domain.main.dto.ResGetPostUpdateDTO;
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

    @RequestMapping("/**")
    public String redirectToRoot() {
        return "redirect:/";
    }

    @GetMapping("/post/{postIdx}")
    public String getPostPage(Model model, @PathVariable Long postIdx) {
        try {
            ResPostDTO dto = mainService.getPostData(postIdx);
            model.addAttribute("dto", dto);
        } catch (Exception e) {
            return "redirect:/";
        }

        return "post/post";
    }

    @GetMapping("/post/update/{postIdx}")
    public String getUpdatePostPage(Model model, @PathVariable Long postIdx, HttpSession session) {
        try {
            ResGetPostUpdateDTO dto = mainService.getPostUpdateData(postIdx, session);
            model.addAttribute("dto", dto);
            return "post/post-update";
        } catch (RuntimeException e) {
            // 서비스 레이어에서 로그인 정보가 없을 때 처리할 내용을 여기에 추가
            // 예를 들어, 에러 페이지로 리다이렉트하거나 적절한 오류 메시지를 띄우는 등의 처리를 할 수 있습니다.
            return "redirect:/post/{postIdx}";
        }
    }

    @GetMapping("/post-write")
    private String getPostWrite(Model model, HttpSession session) {

        if (session.getAttribute("dto") == null) {
            return "redirect:/";
        }

        return "post/post-write";
    }

    @GetMapping("/post/comment/{commentIdx}")
    public String getUpdateCommentPage(Model model, @PathVariable Long commentIdx, HttpSession session) {
        try {
            ResGetCommentUpdateDataDTO dto = mainService.getCommentUpdateData(commentIdx, session);
            model.addAttribute("dto", dto);
            return "/post/comment-update";
        } catch (Exception e) {
            return "redirect:/";
        }

    }

}
