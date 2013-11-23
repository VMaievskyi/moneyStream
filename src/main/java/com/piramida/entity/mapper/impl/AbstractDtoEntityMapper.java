package com.piramida.entity.mapper.impl;

import com.piramida.entity.mapper.DtoEntityMapper;

public abstract class AbstractDtoEntityMapper<D, E> implements
	DtoEntityMapper<D, E> {

    @Override
    public D unmap(final E source) {
	throw new UnsupportedOperationException();
    }
}
