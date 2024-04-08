package com.globant.mentorship.hotelchain.mapper;

import com.globant.mentorship.hotelchain.domain.contract.out.BookingContract;
import com.globant.mentorship.hotelchain.domain.entity.BookingEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BookingMapper extends GenericMapperOut<BookingEntity, BookingContract> {

    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "roomEntity", ignore = true)
    @Mapping(target = "userEntity", ignore = true)
    BookingEntity loadEntityOut(BookingContract contractOut);

    @Override
    @Mapping(target = "roomId", source = "roomEntity.id")
    @Mapping(target = "userId", source = "userEntity.id")
    BookingContract loadContractOut(BookingEntity entity);

    @Override
    List<BookingEntity> loadEntitiesOut(List<BookingContract> contractsOut);

    @Override
    List<BookingContract> loadContractsOut(List<BookingEntity> entities);
}
