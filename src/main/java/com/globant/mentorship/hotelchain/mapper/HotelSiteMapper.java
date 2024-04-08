package com.globant.mentorship.hotelchain.mapper;

import com.globant.mentorship.hotelchain.domain.contract.out.HotelSiteContract;
import com.globant.mentorship.hotelchain.domain.entity.HotelSiteEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface HotelSiteMapper extends GenericMapperOut<HotelSiteEntity, HotelSiteContract> {

    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cityEntity", ignore = true)
    @Mapping(target = "neighborhood", expression = "java(hotelSiteContract.getNeighborhood().toUpperCase())")
    HotelSiteEntity loadEntityOut(HotelSiteContract hotelSiteContract);

    @Override
    @Mapping(target = "cityCode", source = "cityEntity.code")
    HotelSiteContract loadContractOut(HotelSiteEntity hotelSiteEntity);

    @Override
    List<HotelSiteEntity> loadEntitiesOut(List<HotelSiteContract> hotelSiteContractsOut);

    @Override
    List<HotelSiteContract> loadContractsOut(List<HotelSiteEntity> hotelSiteEntities);

}
