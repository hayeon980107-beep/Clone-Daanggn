package hy.banana.banana.user;

import hy.banana.banana.common.exception.CustomException;
import hy.banana.banana.common.exception.ErrorCode;
import hy.banana.banana.user.dto.SignUpRequest;
import hy.banana.banana.user.dto.SignUpResponse;
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
}
