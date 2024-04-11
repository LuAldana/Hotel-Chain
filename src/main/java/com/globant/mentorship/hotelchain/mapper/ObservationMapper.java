package com.globant.mentorship.hotelchain.mapper;


import com.globant.mentorship.hotelchain.domain.contract.out.ObservationContract;
import com.globant.mentorship.hotelchain.domain.entity.ObservationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ObservationMapper extends GenericMapperOut<ObservationEntity, ObservationContract> {

    @Override
    @Mapping(target = "bookingEntity", ignore = true)
    ObservationEntity loadEntityOut(ObservationContract contractOut);

    @Override
    @Mapping(target = "bookingId", source = "bookingEntity.id")
    ObservationContract loadContractOut(ObservationEntity entity);

    @Override
    List<ObservationEntity> loadEntitiesOut(List<ObservationContract> contractsOut);

    @Override
    List<ObservationContract> loadContractsOut(List<ObservationEntity> entities);
}
