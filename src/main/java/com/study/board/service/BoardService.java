package com.study.board.service;

import com.study.board.entity.Board;
import com.study.board.repository.BoardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository ;

    public void write(Board board) {
        boardRepository.save(board) ;
    }

    public void writeAll(Board board) {
        Board dummy = null ;
        List<Board> list = new ArrayList<Board>();

        for (int inx=0 ; inx < 1000 ; inx++) {
            dummy = Board.builder()
                            .title(board.getTitle()+inx)
                                    .content(board.getContent()+inx).build();

            list.add(dummy) ;
        }

        boardRepository.saveAll(list);
    }

    public List<Board> boardList() {
        return boardRepository.findAll() ;
    }

    public Board boardView(Integer id) {
        return boardRepository.findById(id).get() ;
    }

    public void boardDelete(Integer id) {
        boardRepository.deleteById(id);
    }
}
