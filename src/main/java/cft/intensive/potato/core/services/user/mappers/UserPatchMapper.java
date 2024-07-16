package cft.intensive.potato.core.services.user.mappers;

import cft.intensive.potato.api.dto.user.UserPostRequest;
import cft.intensive.potato.api.dto.user.UserPatchRequest;
import cft.intensive.potato.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;

@Component(value = "userPatchMapper")
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserPatchMapper {
    void updateUserFromDto(UserPatchRequest userPatchRequest, @MappingTarget User user);

    void updateUserFromCreateUserRequest(UserPostRequest userPostRequest, @MappingTarget User user);
}
