package com.globant.mentorship.hotelchain.mapper;

import com.globant.mentorship.hotelchain.domain.contract.out.RoomTypeContract;
import com.globant.mentorship.hotelchain.domain.entity.RoomTypeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface RoomTypeMapper extends GenericMapperOut<RoomTypeEntity, RoomTypeContract> {

    @Override
    RoomTypeEntity loadEntityOut(RoomTypeContract contractOut);

    @Override
    RoomTypeContract loadContractOut(RoomTypeEntity entity);

    @Override
    List<RoomTypeEntity> loadEntitiesOut(List<RoomTypeContract> contractsOut);

    @Override
    List<RoomTypeContract> loadContractsOut(List<RoomTypeEntity> entities);
}
