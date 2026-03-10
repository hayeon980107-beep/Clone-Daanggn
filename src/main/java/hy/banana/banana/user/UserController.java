package hy.banana.banana.user;

import hy.banana.banana.common.response.ApiResponse;
import hy.banana.banana.user.dto.LoginRequest;
import hy.banana.banana.user.dto.LoginResponse;
import hy.banana.banana.user.dto.SignUpRequest;
import hy.banana.banana.user.dto.SignUpResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ApiResponse<SignUpResponse> signUp(
            @RequestBody @Valid SignUpRequest request
    ) {
        return ApiResponse.success(userService.signUp(request));
    }

    @PostMapping("/login")
    public ApiResponse<LoginResponse> login(
            @RequestBody @Valid LoginRequest request
            ) {
        return ApiResponse.success(userService.login(request));
    }
}
