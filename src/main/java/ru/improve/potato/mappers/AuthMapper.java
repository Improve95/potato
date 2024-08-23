package ru.improve.potato.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.improve.potato.dto.session.CreateSessionResponse;
import ru.improve.potato.models.Session;

import java.util.Date;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AuthMapper {

    CreateSessionResponse toLoginResponse(Session session, Date expiredAt);
}
