package ru.improve.potato.services.user;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ru.improve.potato.dto.auth.SignUpResponse;
import ru.improve.potato.dto.user.UserPatchRequest;
import ru.improve.potato.mappers.UserMapper;
import ru.improve.potato.exceptions.AlreadyExistException;
import ru.improve.potato.exceptions.NotFoundException;
import ru.improve.potato.repositories.UserRepository;
import ru.improve.potato.models.Wallet;
import ru.improve.potato.models.user.Role;
import ru.improve.potato.models.user.User;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Transactional
    @Override
    public SignUpResponse save(User user) {

        user.setRole(Role.USER);

        Wallet wallet = new Wallet(1000, user);
        user.setWallet(wallet);

        try {
            userRepository.save(user);
        } catch (DataIntegrityViolationException ex) {
            throw new AlreadyExistException(ex.getMessage(), List.of("phone"));
        }

        return userMapper.toUserSignUpResponse(user);
    }

    @Override
    public User getById(int id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("user not found", List.of("id")));

    }

    @Override
    public User getByPhone(String phone) {
        return userRepository.findByPhone(phone)
                .orElseThrow(() -> new NotFoundException("user not found", List.of("phone")));
    }

    @Transactional
    @Override
    public void patchById(UserPatchRequest userPatchRequest, int id) {
        User patchUser = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("user not found", List.of("id")));

        userMapper.patchUserFromPatchUserRequest(userPatchRequest, patchUser);
    }
}
