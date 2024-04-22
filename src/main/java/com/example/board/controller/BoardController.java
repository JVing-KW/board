package com.example.board.controller;


import com.example.board.dto.BoardDTO;
import com.example.board.dto.PageRequestDTO;
import com.example.board.dto.PageResponseDTO;
import com.example.board.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/todo")
@Log4j2
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

//    @RequestMapping("/list")
//    public void list(Model model){
//
//        log.info("todo list.......");
//
//        //model.addAttribute("dtoList", todoService.getAll());
//    }

    @GetMapping("/register")
    public void registerGET() {
        log.info("GET todo register.......");
    }

    @PostMapping("/register")
    public String registerPost(@Valid BoardDTO boardDTO,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {

        log.info("POST todo register.......");

        if(bindingResult.hasErrors()) {
            log.info("has errors.......");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors() );
            return "redirect:/todo/register";
        }

        log.info(boardDTO);

        todoService.register(boardDTO);

        return "redirect:/todo/list";
    }


//    @GetMapping("/read")
//    public void read(Long bno, Model model){
//
//        TodoDTO todoDTO = todoService.getOne(bno);
//        log.info(todoDTO);
//
//        model.addAttribute("dto", todoDTO);
//
//    }

//    @GetMapping({"/read", "/modify"})
//    public void read(Long bno, Model model){
//
//        TodoDTO todoDTO = todoService.getOne(bno);
//        log.info(todoDTO);
//
//        model.addAttribute("dto", todoDTO );
//
//    }

    @GetMapping({"/read", "/modify"})
    public void read(Long bno, PageRequestDTO pageRequestDTO, Model model){

        BoardDTO todoDTO = boardService.readOne(bno);
        log.info(todoDTO);

        model.addAttribute("dto", todoDTO );

    }


//    @PostMapping("/remove")
//    public String remove(Long bno, RedirectAttributes redirectAttributes){
//
//        log.info("-------------remove------------------");
//        log.info("bno: " + bno);
//
//        todoService.remove(bno);
//
//        return "redirect:/todo/list";
//    }


//    @PostMapping("/remove")
//    public String remove(Long bno, PageRequestDTO pageRequestDTO, RedirectAttributes redirectAttributes){
//
//        log.info("-------------remove------------------");
//        log.info("bno: " + bno);
//
//        todoService.remove(bno);
//
//        redirectAttributes.addAttribute("page", 1);
//        redirectAttributes.addAttribute("size", pageRequestDTO.getSize());
//        return "redirect:/todo/list";
//    }

    @PostMapping("/remove")
    public String remove(Long bno, PageRequestDTO pageRequestDTO, RedirectAttributes redirectAttributes){

        log.info("-------------remove------------------");
        log.info("bno: " + bno);

        todoService.remove(bno);

        return "redirect:/todo/list?" + pageRequestDTO.getLink();
    }


//    @PostMapping("/modify")
//    public String modify(@Valid TodoDTO todoDTO,
//                         PageRequestDTO pageRequestDTO,
//                         BindingResult bindingResult,
//                         RedirectAttributes redirectAttributes){
//
//        if(bindingResult.hasErrors()) {
//            log.info("has errors.......");
//            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors() );
//            redirectAttributes.addAttribute("bno", todoDTO.getbno() );
//            return "redirect:/todo/modify";
//        }
//
//        log.info(todoDTO);
//
//        todoService.modify(todoDTO);
//
//        redirectAttributes.addAttribute("page", pageRequestDTO.getPage());
//        redirectAttributes.addAttribute("size", pageRequestDTO.getSize());
//
//        return "redirect:/todo/list";
//    }


    @PostMapping("/modify")
    public String modify(
            PageRequestDTO pageRequestDTO,
            @Valid BoardDTO boardDTO,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()) {
            log.info("has errors.......");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors() );
            redirectAttributes.addAttribute("bno", boardDTO.getBno() );
            return "redirect:/todo/modify";
        }

        log.info(boardDTO);

        boardService.modify(boardDTO);

        redirectAttributes.addAttribute("bno", boardDTO.getBno());

        return "redirect:/todo/read";
    }




    @GetMapping("/list")
    public void list(@Valid PageRequestDTO pageRequestDTO, BindingResult bindingResult, Model model){

        log.info(pageRequestDTO);

        if(bindingResult.hasErrors()){
            pageRequestDTO = PageRequestDTO.builder().build();
        }
        model.addAttribute("responseDTO", boardService.getList(pageRequestDTO));
    }


}