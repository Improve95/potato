package ru.improve.potato.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import ru.improve.potato.dto.auth.SignUpResponse;
import ru.improve.potato.dto.user.UserGetResponse;
import ru.improve.potato.dto.user.UserPatchRequest;
import ru.improve.potato.dto.auth.SingUpRequest;
import ru.improve.potato.models.user.User;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    User toUser(SingUpRequest singUpRequest);

    User toUser(UserPatchRequest userPatchRequest);

    SignUpResponse toUserSignUpResponse(User user);

    UserGetResponse toUserGetResponse(User user);

    void patchUserFromPatchUserRequest(UserPatchRequest userPatchRequest, @MappingTarget User user);
}
