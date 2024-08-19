package ru.improve.potato.core.services.user;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.improve.potato.api.dto.user.UserGetResponse;
import ru.improve.potato.api.dto.user.UserPatchRequest;
import ru.improve.potato.api.dto.user.UserPostResponse;
import ru.improve.potato.api.dtoMappers.UserDtoConverters;
import ru.improve.potato.api.dtoMappers.UserMapper;
import ru.improve.potato.core.dao.repository.UserRepository;
import ru.improve.potato.core.exceptions.NotFoundException;
import ru.improve.potato.model.User;
import ru.improve.potato.model.Wallet;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;

    private final UserDtoConverters userDtoConverters;
    private final UserMapper userMapper;

    @Transactional
    @Override
    public UserPostResponse saveNewUser(User user) {

        Wallet wallet = new Wallet(1000, user);
        user.setWallet(wallet);

        userRepository.save(user);

        return UserPostResponse.builder()
                .id(user.getId())
                .build();
    }

    @Override
    public UserGetResponse getById(int id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("user not found", List.of("id")));

        return userDtoConverters.convertToGetResponse(user);
    }

    @Transactional
    @Override
    public void patchUserById(UserPatchRequest userPatchRequest, int id) {
        User patchUser = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("user not found", List.of("id")));

        userMapper.updateUserFromDto(userPatchRequest, patchUser);
    }
}
