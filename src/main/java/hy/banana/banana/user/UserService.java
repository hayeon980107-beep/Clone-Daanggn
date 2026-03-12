package hy.banana.banana.user;

import hy.banana.banana.common.exception.CustomException;
import hy.banana.banana.common.exception.ErrorCode;
import hy.banana.banana.common.jwt.JwtProvider;
import hy.banana.banana.user.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @Transactional
    public SignUpResponse signUp(SignUpRequest request) {

        if (userRepository.existsByEmail(request.email())) {
            throw new CustomException(ErrorCode.DUPLICATE_EMAIL);
        }

        if (userRepository.existsByNickName(request.nickName())) {
            throw new CustomException(ErrorCode.DUPLICATE_NICKNAME);
        }

        String encodedPassword = passwordEncoder.encode(request.password());

        User user = User.create(
                request.email(),
                encodedPassword,
                request.nickName(),
                request.phoneNum(),
                request.address()
        );

        User savedUser = userRepository.save(user);

        return new SignUpResponse(
                savedUser.getUserId(),
                savedUser.getEmail(),
                savedUser.getNickName()
        );
    }

    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new CustomException(ErrorCode.INVALID_LOGIN));

        if(!passwordEncoder.matches(request.password(), user.getPasswordHash())) {
            throw new CustomException(ErrorCode.INVALID_LOGIN);
        }

        String accessToken = jwtProvider.createAccessToken(
                user.getUserId(),
                user.getEmail()
        );

        return new LoginResponse(
                user.getUserId(),
                user.getNickName(),
                user.getEmail(),
                accessToken
        );
    }

    @Transactional
    public void activate(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        if (user.isWithdrawn()) {
            throw new CustomException(ErrorCode.INVALID_USER_STATUS);
        }

        user.activate();
    }

    @Transactional
    public void ban(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        if (user.isWithdrawn()) {
            throw new CustomException(ErrorCode.INVALID_USER_STATUS);
        }

        user.ban();
    }

    @Transactional
    public void withdraw(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        user.withdraw();
    }
}
