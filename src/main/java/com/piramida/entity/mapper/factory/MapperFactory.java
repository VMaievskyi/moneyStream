package com.piramida.entity.mapper.factory;

import com.piramida.entity.mapper.impl.AbstractDtoEntityMapper;

public interface MapperFactory {

    AbstractDtoEntityMapper createInstance(Class sourceClass);
}
