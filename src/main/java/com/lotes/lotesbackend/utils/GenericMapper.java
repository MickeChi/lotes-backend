package com.lotes.lotesbackend.utils;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;

import java.lang.reflect.Type;
import java.util.List;

public abstract class GenericMapper {
    public static ModelMapper getMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper;
    }
    /**
     * Converts a source to a type destination.
     *
     * @param source					The source object
     * @param typeDestination			The type destination
     * @return							The object created
     */
    public static <T, E> E mapper(T source, Class<E> typeDestination) {

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper.map(source, typeDestination);

    }

    /**
     * Converts a source to a type destination.
     *
     * @param source				The source object
     * @param destination			The destination object
     * @return						The object created
     */
    public static <T, E> E mapper(T source, E destination) {

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.map(source, destination);

        return destination;
    }


    /**
     * Converts a source to a type destination.
     *
     * @param source				The souce object
     * @return						The object created
     */
    public static <E, T> List<E> mapper(List<T> source, Type destinationType) {

        List<E> model = null;
        if (source != null && destinationType != null) {

            ModelMapper modelMapper = new ModelMapper();

            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
            model = modelMapper.map(source, destinationType);
        }

        return model;
    }

    public static <T, E> void mapUpdateWithMapping(T source, E destination, PropertyMap<T, E> mapping) {

        if (source != null && destination != null) {

            ModelMapper modelMapper = new ModelMapper();

            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
            modelMapper.addMappings(mapping);
            modelMapper.map(source, destination);
        }
    }


    public static <T, E> E mapWithMapping(T source, Class<E> typeDestination, PropertyMap<T, E> mapping) {

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.addMappings(mapping);
        return modelMapper.map(source, typeDestination);

    }
}
