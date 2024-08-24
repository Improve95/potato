package ru.improve.potato.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.improve.potato.dto.session.SessionResponseDto;
import ru.improve.potato.models.Session;

import java.util.Date;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SessionMapper {

    SessionResponseDto toSessionResponseDto(Session session, Date expiredAt);

//    SessionResponseDto
}
