package com.globant.mentorship.hotelchain.mapper;

import com.globant.mentorship.hotelchain.domain.contract.out.RoomContract;
import com.globant.mentorship.hotelchain.domain.entity.RoomEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface RoomMapper extends GenericMapperOut<RoomEntity, RoomContract> {
    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "roomTypeEntity", ignore = true)
    @Mapping(target = "hotelSiteEntity", ignore = true)
    RoomEntity loadEntityOut(RoomContract contractOut);

    @Override
    @Mapping(target = "roomTypeId", source = "roomTypeEntity.id")
    @Mapping(target = "hotelSiteId", source = "hotelSiteEntity.id")
    RoomContract loadContractOut(RoomEntity entity);

    @Override
    List<RoomEntity> loadEntitiesOut(List<RoomContract> contractsOut);

    @Override
    List<RoomContract> loadContractsOut(List<RoomEntity> entities);
}
