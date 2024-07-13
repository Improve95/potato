package cft.intensive.potato.core.services.user.implementations;

import cft.intensive.potato.api.dto.user.UserCreateRequest;
import cft.intensive.potato.api.dto.user.UserCreateResponse;
import cft.intensive.potato.api.dto.user.UserGetResponse;
import cft.intensive.potato.api.dto.user.UserPatchRequest;
import cft.intensive.potato.core.exceptions.IncorrectRequestException;
import cft.intensive.potato.core.exceptions.transfer.NotFoundException;
import cft.intensive.potato.core.exceptions.user.UserAlreadyExistException;
import cft.intensive.potato.core.repository.UsersRepository;
import cft.intensive.potato.core.services.user.HashCalculator;
import cft.intensive.potato.core.services.user.UserCreateValidator;
import cft.intensive.potato.core.services.user.UserService;
import cft.intensive.potato.core.services.user.mappers.UserPatchMapper;
import cft.intensive.potato.model.User;
import cft.intensive.potato.model.Wallet;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

    private final UsersRepository usersRepository;

    private final UserCreateValidator userCreateValidator;

    private final HashCalculator hashCalculator;
    private final UserPatchMapper userPatchMapper;

    @Transactional
    @Override
    public UserCreateResponse createUser(UserCreateRequest userCreateRequest) {
        if (!userCreateValidator.validateUserCreateRequest(userCreateRequest)) {
            throw new IncorrectRequestException();
        }
        if (usersRepository.userIsExist(userCreateRequest.getTelephoneNumber())) {
            throw new UserAlreadyExistException();
        }

        User newUser = new User();
        userPatchMapper.updateUserFromCreateUserRequest(userCreateRequest, newUser);
        newUser.setPasswordHash(hashCalculator.createHash(userCreateRequest.getPassword()));

        Wallet wallet = Wallet.builder()
                .balance(1000)
                .user(newUser)
                .build();

        newUser.setWallet(wallet);

        usersRepository.addUser(newUser);

        return UserCreateResponse.builder()
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
                .telephoneNumber(user.getTelephoneNumber())
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
