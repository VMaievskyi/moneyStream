package com.piramida.entity.mapper.factory;

import java.util.Map;

import com.piramida.entity.mapper.impl.AbstractDtoEntityMapper;

public final class MappersRegistry {
    private Map<Class, AbstractDtoEntityMapper> registeregMaps;

    public Map<Class, AbstractDtoEntityMapper> getRegisteregMaps() {
	return registeregMaps;
    }

    public void setRegisteregMaps(
	    final Map<Class, AbstractDtoEntityMapper> registeregMaps) {
	this.registeregMaps = registeregMaps;
    }

}
