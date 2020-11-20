package com.example.demo.controller;

import com.example.demo.domain.Board;
import com.example.demo.domain.BoardDto;
import com.example.demo.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/boardList")
    public String boardList(Model model){

        return "board/boardList";
    }

    @GetMapping("/boardDetail")
    public String boardDetail(Model model){
        model.addAttribute("boardDto", new BoardDto());
        return "board/boardDetail";
    }

    @PostMapping("/boardDetail")
    public String createBoardDetail(BoardDto boardDto){
        ModelMapper modelMapper = new ModelMapper();
        Board board = modelMapper.map(boardDto, Board.class);

        board.setLocalDateTime(LocalDateTime.now());
        boardService.createBoard(board);
        return "redirect:/boardList";
    }
}
