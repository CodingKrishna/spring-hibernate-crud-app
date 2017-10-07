package com.pioneercoders.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dozer.DozerBeanMapperSingletonWrapper;

public class CollectionMapper {

	public static <T, U> List<U> getMappingList(Iterator<T> listIter,
			Class<U> mapperClass) {
		List<U> mapperList = new ArrayList<U>();
		while (listIter.hasNext()) {
			mapperList.add(DozerBeanMapperSingletonWrapper.getInstance().map(
					listIter.next(), mapperClass));
		}
		return mapperList;
	}

	public static <T, U> List<U> getMappingList(Iterator<T> listIter,
			Class<U> mapperClass, String mapName) {
		List<U> mapperList = new ArrayList<U>();
		while (listIter.hasNext()) {
			mapperList.add(DozerBeanMapperSingletonWrapper.getInstance().map(
					listIter.next(), mapperClass, mapName));
		}
		return mapperList;
	}
}
