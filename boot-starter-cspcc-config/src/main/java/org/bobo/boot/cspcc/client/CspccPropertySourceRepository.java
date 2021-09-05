/*
 * Copyright (C) 2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.bobo.boot.cspcc.client;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xiaojing
 * @author pbting
 */
public class CspccPropertySourceRepository {

	private final static ConcurrentHashMap<String, CspccPropertySource> NACOS_PROPERTY_SOURCE_REPOSITORY = new ConcurrentHashMap<>();

	/**
	 * @return all nacos properties from application context
	 */
	public static List<CspccPropertySource> getAll() {
		List<CspccPropertySource> result = new ArrayList<>();
		result.addAll(NACOS_PROPERTY_SOURCE_REPOSITORY.values());
		return result;
	}

	public static void collectNacosPropertySources(CspccPropertySource nacosPropertySource) {
		NACOS_PROPERTY_SOURCE_REPOSITORY.putIfAbsent(nacosPropertySource.getDataId(),nacosPropertySource);
	}

	public static CspccPropertySource getNacosPropertySource(String dataId) {
		return NACOS_PROPERTY_SOURCE_REPOSITORY.get(dataId);
	}
}
