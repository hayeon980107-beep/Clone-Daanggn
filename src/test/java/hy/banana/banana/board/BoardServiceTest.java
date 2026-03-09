package hy.banana.banana.board;

import hy.banana.banana.TestFactory;
import hy.banana.banana.board.dto.BoardCreateRequest;
import hy.banana.banana.board.dto.BoardCreateResponse;
import hy.banana.banana.board.dto.BoardGetOneResponse;
import hy.banana.banana.category.Category;
import hy.banana.banana.category.CategoryRepository;
import hy.banana.banana.neighborhood.Neighborhood;
import hy.banana.banana.neighborhood.NeighborhoodRepository;
import hy.banana.banana.user.User;
import hy.banana.banana.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

// 서비스 단위 테스트
@SpringBootTest
@Transactional
public class BoardServiceTest {
//
//    @Autowired
//    BoardService boardService;
//
//    @Autowired
//    MarketBoardRepository boardRepository;
//
//    @Autowired
//    UserRepository userRepository;
//    @Autowired
//    CategoryRepository categoryRepository;
//    @Autowired
//    NeighborhoodRepository neighborhoodRepository;
//
//    @Test
//    void 게시글_생성() {
//        //given
//        BoardCreateRequest request = new BoardCreateRequest("테스트", "내용", 1000,1L,1L);
//
//        // when
//        BoardCreateResponse response = boardService.create(1L, request);
//
//        // then
//        MarketBoard board = boardRepository.findById(response.boardId()).orElseThrow();
//
//        assertThat(board.getTitle()).isEqualTo("테스트");
//    }
//
//    @Test
//    void 게시글_생성_성공() {
//        // given: 테스트 데이터 준비(하드코딩 ID 의존 X)
//        User user = userRepository.save(TestFactory.user());
//        Category category = categoryRepository.save(TestFactory.category());
//        Neighborhood neighborhood = neighborhoodRepository.save(TestFactory.neighborhood());
//
//        BoardCreateRequest req = new BoardCreateRequest(
//                "제목", "내용", 1000, category.getCategoryId(), neighborhood.getNeiId()
//        );
//
//        // when
//        BoardCreateResponse res = boardService.create(user.getUserId(), req);
//
//        // then
//        MarketBoard saved = boardRepository.findById(res.boardId()).orElseThrow();
//        assertThat(saved.getTitle()).isEqualTo("제목");
//        assertThat(saved.getPrice()).isEqualTo(1000);
//    }
//
//    @Test
//    void 게시글_단건_조회() {
//        // given
//        User user = userRepository.save(TestFactory.user());
//        Category category = categoryRepository.save(TestFactory.category());
//        Neighborhood neighborhood = neighborhoodRepository.save(TestFactory.neighborhood());
//
//        MarketBoard board = boardRepository.save(
//                MarketBoard.create(user, category, neighborhood, "제목", "내용", 1000)
//        );
//
//        Long req = board.getBoardId();
//
//        // when
//        BoardGetOneResponse res = boardService.getBoard(req);
//
//        // then
//        assertThat(res.title()).isEqualTo("제목");
//        assertThat(res.userNickName()).isEqualTo(user.getNickName());
//    }
}
