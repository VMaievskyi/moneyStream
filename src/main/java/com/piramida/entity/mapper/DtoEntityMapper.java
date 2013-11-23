package com.piramida.entity.mapper;

public interface DtoEntityMapper<D, E> {

    E map(D source);

    D unmap(E source);
}
