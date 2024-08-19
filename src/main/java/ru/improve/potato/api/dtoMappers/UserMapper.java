package ru.improve.potato.api.dtoMappers;

import ru.improve.potato.api.dto.user.UserPostRequest;
import ru.improve.potato.api.dto.user.UserPatchRequest;
import ru.improve.potato.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;

@Component(value = "userPatchMapper")
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {
    void updateUserFromDto(UserPatchRequest userPatchRequest, @MappingTarget User user);

    void updateUserFromCreateUserRequest(UserPostRequest userPostRequest, @MappingTarget User user);


}
