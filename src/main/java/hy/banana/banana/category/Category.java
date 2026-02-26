package hy.banana.banana.category;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(
        name = "category",
        indexes = {
                @Index(name = "idx_category_type", columnList = "type"),
                @Index(name = "idx_category_parent", columnList = "parent_id")
        }
)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long categoryId;

        // 카테고리 이름 (디지털기기, 동네질문 등)
        @Column(nullable = false, length = 50)
        private String name;

        // MARKET / COMMUNITY
        @Enumerated(EnumType.STRING)
        @Column(nullable = false, length = 20)
        private CategoryType type;

        /**
         * 부모 카테고리
         * ex)
         * 디지털기기
         *   └ 노트북
         */
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "parent_id")
        private Category parent;

        // ===== 생성 메서드 =====
        public Category(String name, CategoryType type, Category parent) {
                this.name = name;
                this.type = type;
                this.parent = parent;
        }
}


