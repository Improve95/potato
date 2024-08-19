package ru.improve.potato.core.services.user;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.improve.potato.api.dto.user.UserGetResponse;
import ru.improve.potato.api.dto.user.UserPatchRequest;
import ru.improve.potato.api.dto.user.UserPostRequest;
import ru.improve.potato.api.dto.user.UserPostResponse;
import ru.improve.potato.core.exceptions.NotFoundException;
import ru.improve.potato.core.dao.repository.UserRepository;
import ru.improve.potato.model.User;
import ru.improve.potato.model.Wallet;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;

    private final UserPatchMapper userPatchMapper;

    @Transactional
    @Override
    public UserPostResponse createNewUser(User user) {

//        User newUser = new User();
//        userPatchMapper.updateUserFromCreateUserRequest(userPostRequest, newUser);
//        newUser.setPasswordHash(hashCalculator.createHash(userPostRequest.getPassword()));

        Wallet wallet = Wallet.builder()
                .balance(1000)
                .user(newUser)
                .build();

        newUser.setWallet(wallet);

        usersDAO.addUser(newUser);

        return UserPostResponse.builder()
                .id(newUser.getId())
                .build();
    }

    @Override
    public UserGetResponse getById(int id) {
        User user = Optional.ofNullable(usersDAO.getById(id))
                .orElseThrow(() -> new NotFoundException("user not found", List.of("id")));

        return UserGetResponse.builder()
                .firstName(user.getFirstName())
                .secondName(user.getSecondName())
                .telephoneNumber(user.getPhone())
                .email(user.getEmail())
                .birthdate(user.getBirthdate())
                .build();
    }

    @Transactional
    @Override
    public void patchUserById(User user, int id) {
        User patchUser = usersDAO.getById(id);

        if (patchUser == null) {
            throw new NotFoundException("user not found", List.of("id"));
        }

        userPatchMapper.updateUserFromDto(userPatchRequest, patchUser);
    }
}
