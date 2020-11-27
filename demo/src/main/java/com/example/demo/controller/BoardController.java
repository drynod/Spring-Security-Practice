package com.example.demo.controller;

import com.example.demo.domain.Board;
import com.example.demo.dto.BoardDto;
import com.example.demo.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/boardList/{no}")
    public String boardListDetail(@PathVariable("no") Long id, Model model){
        BoardDto boardDto = boardService.getBoard(id);
        model.addAttribute("boardDto", boardDto);
        return "board/boardListDetail";
    }

    @GetMapping("/boardList")
    public String boardList(Model model){
        List<Board> allBoard = boardService.findAllBoard();
        model.addAttribute("allBoard", allBoard);
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

        boardService.createBoard(board);
        return "redirect:/boardList";
    }
}
