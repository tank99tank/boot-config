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

package org.bobo.boot.cspcc;

import org.bobo.boot.cspcc.refresh.CspccContextRefresher;
import org.bobo.boot.cspcc.refresh.CspccRefreshHistory;
import org.bobo.boot.cspcc.refresh.CspccRefreshProperties;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author juven.xuxb
 */
@Configuration
@ConditionalOnProperty(name = "cspcc.enabled", matchIfMissing = true)
public class CspccConfigAutoConfiguration {

	@Bean
	public CspccConfigProperties nacosConfigProperties(ApplicationContext context) {
		if (context.getParent() != null
				&& BeanFactoryUtils.beanNamesForTypeIncludingAncestors(
						context.getParent(), CspccConfigProperties.class).length > 0) {
			return BeanFactoryUtils.beanOfTypeIncludingAncestors(context.getParent(),
					CspccConfigProperties.class);
		}
		CspccConfigProperties nacosConfigProperties = new CspccConfigProperties();
		return nacosConfigProperties;
	}

	@Bean
	public CspccRefreshProperties nacosRefreshProperties() {
		return new CspccRefreshProperties();
	}

	@Bean
	public CspccRefreshHistory nacosRefreshHistory() {
		return new CspccRefreshHistory();
	}

	@Bean
	public CspccContextRefresher nacosContextRefresher(
			CspccConfigProperties nacosConfigProperties,
			CspccRefreshProperties nacosRefreshProperties,
			CspccRefreshHistory refreshHistory) {
		return new CspccContextRefresher(nacosRefreshProperties, refreshHistory,
				nacosConfigProperties.configServiceInstance());
	}
}
