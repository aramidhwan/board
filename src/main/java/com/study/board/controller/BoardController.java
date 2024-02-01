package com.study.board.controller;

import com.study.board.entity.Board;
import com.study.board.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@Slf4j
public class BoardController {

    @Autowired
    private BoardService boardService ;

    @Value("${server.port}")
    private String serverPort ;

    @GetMapping("/board")
    public String welcome(Model model) {
        log.info("################### log.info ########################");
        model.addAttribute("port", serverPort) ;
        return "index";
    }

    @GetMapping("/board/write")
    public String boardWriteForm() {
        return "boardwrite";
    }

    @PostMapping("/board/writepro")
    public String boardwritePro(Board board, Model model) {
        boardService.write(board);

        model.addAttribute("message", "성공적으로 저장되었습니다.") ;
        model.addAttribute("searchUrl", "/board/list") ;

        return "message" ;
    }

    @GetMapping("/board/list")
    public String boardList(Model model) {
        List<Board> boardList = boardService.boardList() ;
        model.addAttribute("list", boardList) ;
        return "boardlist" ;
    }

    @GetMapping("board/view")
    public String boardView(Model model, Integer id) {
        Board board = boardService.boardView(id) ;
        model.addAttribute("board", board) ;

        return "boardview" ;
    }

    @GetMapping("board/delete")
    public String boardDelete(Integer id) {
        boardService.boardDelete(id);

        return "redirect:/board/list" ;
    }

    @GetMapping("board/modify/{id}")
    public String boardModify(@PathVariable("id") Integer id, Model model) {
        Board board = boardService.boardView(id);

        model.addAttribute("board", board) ;
        return "boardmodify" ;
    }

    @PostMapping("board/update/{id}")
    public String boardUpdate(@PathVariable("id") Integer id, Board board) {

        Board modified = boardService.boardView(id) ;
        modified.setTitle(board.getTitle()) ;
        modified.setContent(board.getContent());

        boardService.write(modified);

        return "redirect:/board/list" ;
    }
}
