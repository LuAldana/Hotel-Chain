package com.globant.mentorship.hotelchain.mapper;

import com.globant.mentorship.hotelchain.domain.contract.out.CityContract;
import com.globant.mentorship.hotelchain.domain.entity.CityEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel =  MappingConstants.ComponentModel.SPRING)
public interface CityMapper extends GenericMapperOut<CityEntity, CityContract> {

    @Override
    CityEntity loadEntityOut(CityContract contractOut);

    @Override
    CityContract loadContractOut(CityEntity entity);

    @Override
    List<CityEntity> loadEntitiesOut(List<CityContract> contractsOut);

    @Override
    List<CityContract> loadContractsOut(List<CityEntity> entities);
}
