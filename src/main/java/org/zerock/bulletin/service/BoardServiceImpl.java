package org.zerock.bulletin.service;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.zerock.bulletin.dto.BoardDTO;
import org.zerock.bulletin.dto.PageRequestDTO;
import org.zerock.bulletin.dto.PageResponseDTO;

@Service
public class BoardServiceImpl implements BoardService{

    @Override
    public PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO) {

        return null;

    }
}
