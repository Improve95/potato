package ru.improve.potato.core.services.user;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.improve.potato.api.dto.user.UserGetResponse;
import ru.improve.potato.api.dto.user.UserPatchRequest;
import ru.improve.potato.api.dto.user.UserPostRequest;
import ru.improve.potato.api.dto.user.UserPostResponse;
import ru.improve.potato.core.exceptions.IncorrectRequestException;
import ru.improve.potato.core.exceptions.transfer.NotFoundException;
import ru.improve.potato.core.exceptions.user.UserAlreadyExistException;
import ru.improve.potato.core.repository.UsersRepository;
import ru.improve.potato.core.validators.user.UserValidator;
import ru.improve.potato.model.User;
import ru.improve.potato.model.Wallet;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

    private final UsersRepository usersRepository;

    private final UserValidator userValidator;

    private final UserPatchMapper userPatchMapper;

    @Transactional
    @Override
    public UserPostResponse createUser(UserPostRequest userPostRequest) {
        if (!userValidator.validateUserCreateRequest(userPostRequest)) {
            throw new IncorrectRequestException();
        }
        if (usersRepository.userIsExist(userPostRequest.getTelephoneNumber())) {
            throw new UserAlreadyExistException();
        }

        User newUser = new User();
        userPatchMapper.updateUserFromCreateUserRequest(userPostRequest, newUser);
//        newUser.setPasswordHash(hashCalculator.createHash(userPostRequest.getPassword()));

        Wallet wallet = Wallet.builder()
                .balance(1000)
                .user(newUser)
                .build();

        newUser.setWallet(wallet);

        usersRepository.addUser(newUser);

        return UserPostResponse.builder()
                .id(newUser.getId())
                .build();
    }

    @Override
    public UserGetResponse getById(int id) {
        User user = Optional.ofNullable(usersRepository.getById(id))
                .orElseThrow(() -> new NotFoundException("user not found by id"));

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
    public void patchUserById(UserPatchRequest userPatchRequest, int id) {
        User patchUser = usersRepository.getById(id);

        if (patchUser == null) {
            throw new NotFoundException("user not found");
        }

        userPatchMapper.updateUserFromDto(userPatchRequest, patchUser);
    }
}
