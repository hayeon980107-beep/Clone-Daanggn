package hy.banana.banana;

import hy.banana.banana.category.Category;
import hy.banana.banana.category.CategoryType;
import hy.banana.banana.neighborhood.Neighborhood;
import hy.banana.banana.user.User;
import hy.banana.banana.user.UserStatus;

public class TestFactory {
    public static User user() {
        return User.create(
                "test1@test.com",
                "encoded-password",
                "테스트유저",
                "01012345678",
                "판교동"
        );
    }

    public static User user(String email) {
        return User.create(
                email,
                "encoded-password",
                "닉네임" + System.currentTimeMillis(),
                "01000000000",
                "판교동"
        );
    }


    public static Category category() {
        return new Category(
                "디지털기기",
                CategoryType.MARKET,
                null
        );
    }

    public static Neighborhood neighborhood() {
        return Neighborhood.create("판교동");
    }

}
