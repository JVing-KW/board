package org.zerock.bulletin.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.bulletin.dto.BoardDTO;
import org.zerock.bulletin.dto.PageRequestDTO;
import org.zerock.bulletin.service.BoardService;

@RequiredArgsConstructor
@Log4j2
@RequestMapping(value="/board")
@Controller
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model){
        PageRequestDTO<BoardDTO> requestDTO =

    }



}
