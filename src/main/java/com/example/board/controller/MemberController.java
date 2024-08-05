package com.example.board.controller;


import com.example.board.dto.MemberJoinDTO;
import com.example.board.dto.MemberSecurityDTO;
import com.example.board.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/member")
@Log4j2
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/join")
    public void joinGET(){

        log.info("join get...");

    }


    @PostMapping("/join")
    public String joinPOST(MemberJoinDTO memberJoinDTO, RedirectAttributes redirectAttributes){

        log.info("join post...");
        log.info(memberJoinDTO);

        try {
            memberService.join(memberJoinDTO);
        } catch (MemberService.MidExistException e) {

            redirectAttributes.addFlashAttribute("error", "mid");
            return "redirect:/member/join";
        }

        redirectAttributes.addFlashAttribute("result", "success");

        return "redirect:/member/login"; //회원 가입 후 로그인
    }

    @GetMapping("modify")
    public void modifyGET(   ){


    }
    @PostMapping("modify")
    public String modifyPost(MemberJoinDTO memberJoinDTO,RedirectAttributes redirectAttributes){
//        memberService

        try {
            memberService.join(memberJoinDTO);

        } catch (MemberService.MidExistException e ) {
            //     두 가지 상황에 대해서 예외처리? 그냥 if문?
            redirectAttributes.addFlashAttribute("error", "mid");
            return "redirect:/member/modify";
        }
        redirectAttributes.addFlashAttribute("result", "success");
        redirectAttributes.addFlashAttribute("dto", memberJoinDTO);
     return "redirect:/member/login";
    }
    @GetMapping("/login")
    public void loginGET(String error, String logout) {
        log.info("login get..............");
        log.info("logout: " + logout);
    }

}