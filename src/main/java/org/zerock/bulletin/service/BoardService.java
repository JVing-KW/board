package org.zerock.bulletin.service;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.zerock.bulletin.dto.BoardDTO;
import org.zerock.bulletin.dto.PageRequestDTO;
import org.zerock.bulletin.dto.PageResponseDTO;

@Service
public interface BoardService {

    public PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO);
}
