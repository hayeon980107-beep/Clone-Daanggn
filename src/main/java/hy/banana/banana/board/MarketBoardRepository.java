package hy.banana.banana.board;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MarketBoardRepository extends JpaRepository<MarketBoard, Long> {
    /*
    JpaRepository안에 다 포함되어있음
    Optional<MarketBoard> findById(Long id);
    List<MarketBoard> findAll();
    MarketBoard save(MarketBoard entity);
    void deleteById(Long id);
     */
}
