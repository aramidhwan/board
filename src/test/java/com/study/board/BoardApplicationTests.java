package com.study.board;

import com.study.board.entity.Board;
import com.study.board.repository.BoardRepository;
import com.study.board.service.BoardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BoardApplicationTests {
	@Autowired
	private BoardService boardService ;

	@Test
	void contextLoads() {
		Board board = Board.builder()
						.title("제목")
								.content("내용")
										.build() ;
		boardService.writeAll(board);

	}

}
