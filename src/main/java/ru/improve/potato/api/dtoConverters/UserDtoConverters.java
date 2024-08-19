package ru.improve.potato.api.dtoConverters;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.improve.potato.api.dto.user.UserGetResponse;
import ru.improve.potato.api.dto.user.UserPatchRequest;
import ru.improve.potato.api.dto.user.UserPostRequest;
import ru.improve.potato.api.dto.user.UserPostResponse;
import ru.improve.potato.model.User;

@Component
@RequiredArgsConstructor
public class UserDtoConverters {

    private final ModelMapper modelMapper;

    public User convertToUser(UserPostRequest userPostRequest) {
        return modelMapper.map(userPostRequest, User.class);
    }

    public User convertToUser(UserPatchRequest userPatchRequest) {
        return modelMapper.map(userPatchRequest, User.class);
    }

    public UserPostResponse convertToPostResponse(User user) {
        return modelMapper.map(user, UserPostResponse.class);
    }

    public UserGetResponse convertToGetResponse(User user) {
        return modelMapper.map(user, UserGetResponse.class);
    }
}
