package hy.banana.banana.board;

import hy.banana.banana.TestFactory;
import hy.banana.banana.category.Category;
import hy.banana.banana.category.CategoryRepository;
import hy.banana.banana.neighborhood.Neighborhood;
import hy.banana.banana.neighborhood.NeighborhoodRepository;
import hy.banana.banana.user.User;
import hy.banana.banana.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MarketBoardRepositoryTest {

//    @Autowired MarketBoardRepository boardRepository;
//    @Autowired UserRepository userRepository;
//    @Autowired CategoryRepository categoryRepository;
//    @Autowired NeighborhoodRepository neighborhoodRepository;
//
//    @Test
//    void 게시글_저장하면_조회된다() {
//        // given (테스트 데이터 준비)
//        User user = userRepository.save(TestFactory.user());
//        Category category = categoryRepository.save(TestFactory.category());
//        Neighborhood neighborhood = neighborhoodRepository.save(TestFactory.neighborhood());
//
//        MarketBoard board = MarketBoard.create(
//                user, category, neighborhood,
//                "제목", "내용", 1000
//        );
//
//        // when
//        MarketBoard saved = boardRepository.save(board);
//
//        // then
//        MarketBoard found = boardRepository.findById(saved.getBoardId()).orElseThrow();
//        assertThat(found.getTitle()).isEqualTo("제목");
//        assertThat(found.getUser().getUserId()).isEqualTo(user.getUserId());
//    }
}