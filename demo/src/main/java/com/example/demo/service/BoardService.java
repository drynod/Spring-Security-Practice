package com.example.demo.service;

import com.example.demo.domain.Board;
import com.example.demo.domain.BoardDto;

import java.util.List;

public interface BoardService {
    public void createBoard(Board board);
    public BoardDto getBoard(Long id);
    public List<Board> findAllBoard();
}
