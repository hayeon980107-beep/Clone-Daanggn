package hy.banana.banana.user;

import hy.banana.banana.common.jwt.CustomUserDetails;
import hy.banana.banana.common.response.ApiResponse;
import hy.banana.banana.user.dto.LoginRequest;
import hy.banana.banana.user.dto.LoginResponse;
import hy.banana.banana.user.dto.SignUpRequest;
import hy.banana.banana.user.dto.SignUpResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("/withdraw")
    public ApiResponse<Void> withdraw(
            @AuthenticationPrincipal CustomUserDetails userDetails
            ) {
        userService.withdraw(userDetails.getUserId());
        return ApiResponse.success(null, "탈퇴가 완료되었습니다.");
    }

    @PatchMapping("/admin/users/{userId}/ban")
    public ApiResponse<Void> banUser(@PathVariable Long userId) {
        userService.ban(userId);
        return ApiResponse.success(null, "사용자를 정지했습니다.");
    }

    @PatchMapping("/admin/users/{userId}/activate")
    public ApiResponse<Void> activateUser(@PathVariable Long userId) {
        userService.activate(userId);
        return ApiResponse.success(null, "사용자를 활성화했습니다.");
    }
}
