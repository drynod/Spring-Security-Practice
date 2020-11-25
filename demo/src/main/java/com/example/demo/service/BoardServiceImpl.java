package com.example.demo.service;

import com.example.demo.domain.Board;
import com.example.demo.domain.BoardDto;
import com.example.demo.domain.Member;
import com.example.demo.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    @Override
    public void createBoard(Board board) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Member principal = (Member) auth.getPrincipal();

        board.setAuthor(principal.getUsername());
        board.setLocalDateTime(LocalDate.now());
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
        boardDto.setLocalDateTime(LocalDate.now());

        return boardDto;
    }

    @Override
    public List<Board> findAllBoard() {
        return boardRepository.findAll();
    }
}
