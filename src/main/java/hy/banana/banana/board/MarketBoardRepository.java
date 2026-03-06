package hy.banana.banana.board;

import hy.banana.banana.board.dto.BoardListItemResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MarketBoardRepository extends JpaRepository<MarketBoard, Long> {
    /*
    JpaRepository안에 다 포함되어있음
    Optional<MarketBoard> findById(Long id);
    List<MarketBoard> findAll();
    MarketBoard save(MarketBoard entity);
    void deleteById(Long id);
     */

    // likeCount의 경우 서비스에서 별개의 레포지토리로 가져오는 것 보다 이렇게 한번에 쿼리로 가져오는 것이 좋음
    // 현재 카테고리 id = one 인데 나중에 && in (세부 선택한 카테고리들) 도 가져와야함
    @Query(
            value = """
    select new hy.banana.banana.board.dto.BoardListItemResponse(
        b.boardId,
        b.title,
        b.price,
        n.name,
        u.nickName,
        b.viewCount,
        count(pl)
    )
    from MarketBoard b
    join b.user u
    join b.neighborhood n
    left join PostLike pl on pl.board = b
    join b.category c
    where n.neiId = :neiId
    and (:categoryId is null or c.categoryId = :categoryId)
    group by b.boardId, b.title, b.price, n.name, u.nickName, b.viewCount
    order by b.createdAt desc
    """,
            countQuery = """
    select count(b)
    from MarketBoard b
    join b.neighborhood n
    join b.category c
    where n.neiId = :neiId
    """
    )
    Page<BoardListItemResponse> findList(@Param("neiId") Long neiId, Pageable pageable, Long categoryId);
}
