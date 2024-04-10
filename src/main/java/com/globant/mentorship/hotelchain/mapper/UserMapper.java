package com.globant.mentorship.hotelchain.mapper;

import com.globant.mentorship.hotelchain.domain.contract.out.UserContract;
import com.globant.mentorship.hotelchain.domain.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper extends GenericMapperOut<UserEntity, UserContract> {

    @Override
    @Mapping(target = "documentTypeEntity", ignore = true)
    @Mapping(target = "cityEntity", ignore = true)
    @Mapping(target = "userTypeEntity", ignore = true)
    UserEntity loadEntityOut(UserContract contractOut);

    @Override
    @Mapping(target = "documentTypeId", source = "documentTypeEntity.id")
    @Mapping(target = "cityCode", source = "cityEntity.code")
    @Mapping(target = "userTypeId", source = "userTypeEntity.id")
    UserContract loadContractOut(UserEntity entity);

    @Override
    List<UserEntity> loadEntitiesOut(List<UserContract> contractsOut);

    @Override
    List<UserContract> loadContractsOut(List<UserEntity> entities);
}
