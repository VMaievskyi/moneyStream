package com.piramida.entity.mapper.factory.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.piramida.entity.mapper.factory.MapperFactory;
import com.piramida.entity.mapper.factory.MappersRegistry;
import com.piramida.entity.mapper.impl.AbstractDtoEntityMapper;

public class MapperFactoryImpl implements MapperFactory {

    @Autowired
    private MappersRegistry mappersRegistry;

    public AbstractDtoEntityMapper createInstance(final Class sourceClass) {
	return mappersRegistry.getRegisteregMaps().get(sourceClass);
    }

    public MappersRegistry getMappersRegistry() {
	return mappersRegistry;
    }

    public void setMappersRegistry(final MappersRegistry mappersRegistry) {
	this.mappersRegistry = mappersRegistry;
    }

}
