package com.globant.mentorship.hotelchain.mapper;

import java.util.List;

public interface GenericMapperOut<E, O> {
    E loadEntityOut(O contractOut);
    O loadContractOut(E entity);

    List<E> loadEntitiesOut(List<O> contractsOut);
    List<O> loadContractsOut(List<E> entities);
}
