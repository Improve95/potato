package ru.improve.potato.core.services.user;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ru.improve.potato.api.dto.user.UserPatchRequest;
import ru.improve.potato.api.dto.user.UserPostResponse;
import ru.improve.potato.api.dtoMappers.UserMapper;
import ru.improve.potato.core.dao.repository.UserRepository;
import ru.improve.potato.core.exceptions.AlreadyExistException;
import ru.improve.potato.core.exceptions.NotFoundException;
import ru.improve.potato.model.User;
import ru.improve.potato.model.Wallet;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Transactional
    @Override
    public UserPostResponse save(User user) {

        Wallet wallet = new Wallet(1000, user);
        user.setWallet(wallet);

        try {
            userRepository.save(user);
        } catch (DataIntegrityViolationException ex) {
            throw new AlreadyExistException(ex.getMessage(), List.of("phone"));
        }

        return userMapper.toUserPostResponse(user);
    }

    @Override
    public User getById(int id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("user not found", List.of("id")));

    }

    @Transactional
    @Override
    public void patchById(UserPatchRequest userPatchRequest, int id) {
        User patchUser = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("user not found", List.of("id")));

        userMapper.patchUserFromPatchUserRequest(userPatchRequest, patchUser);
    }
}
