package com.example.demo.service;

import com.example.demo.domain.Board;
import com.example.demo.domain.BoardDto;
import com.example.demo.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    @Override
    public void createBoard(Board board) {
        boardRepository.save(board);
    }

    @Override
    public BoardDto getBoard(Long id) {
        Optional<Board> findBoard = boardRepository.findById(id);
        Board board = findBoard.get();

        BoardDto boardDto = new BoardDto();
        boardDto.setTitle(board.getTitle());
        boardDto.setAuthor(board.getAuthor());
        boardDto.setContent(board.getContent());
        boardDto.setLocalDateTime(LocalDateTime.now());

        return boardDto;
    }

    @Override
    public List<Board> findAllBoard() {
        return boardRepository.findAll();
    }
}
