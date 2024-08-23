package ru.improve.potato.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.improve.potato.dto.session.LoginResponse;
import ru.improve.potato.security.SessionUserDetails;

import java.util.Date;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AuthMapper {

    LoginResponse toLoginResponse(SessionUserDetails sessionUserDetails, Date expiredAt);
}
