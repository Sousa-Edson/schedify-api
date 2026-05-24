package com.schedify.schedify_api.infrastructure.persistence.mapper;

import com.schedify.schedify_api.domain.entity.Disponibilidade;
import com.schedify.schedify_api.infrastructure.persistence.DisponibilidadeEntity;
import java.time.DayOfWeek;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DisponibilidadeEntityMapper {

    @Mapping(target = "diaSemana", expression = "java(integerToDayOfWeek(entity.getDiaSemana()))")
    Disponibilidade toDomain(DisponibilidadeEntity entity);

    @Mapping(target = "diaSemana", expression = "java(dayOfWeekToInteger(domain.getDiaSemana()))")
    DisponibilidadeEntity toEntity(Disponibilidade domain);

    default DayOfWeek integerToDayOfWeek(Integer value) {
        return value == null ? null : DayOfWeek.of(value);
    }

    default Integer dayOfWeekToInteger(DayOfWeek dayOfWeek) {
        return dayOfWeek == null ? null : dayOfWeek.getValue();
    }
}
