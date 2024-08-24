package ru.improve.potato.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import ru.improve.potato.dto.user.UserPostResponse;
import ru.improve.potato.dto.user.UserGetResponse;
import ru.improve.potato.dto.user.UserPatchRequest;
import ru.improve.potato.dto.user.UserPostRequest;
import ru.improve.potato.models.User;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    User toUser(UserPostRequest userPostRequest);

    User toUser(UserPatchRequest userPatchRequest);

    UserPostResponse toUserPostResponse(User user);

    UserGetResponse toUserGetResponse(User user);

    void patchUserFromPatchUserRequest(UserPatchRequest userPatchRequest, @MappingTarget User user);
}
