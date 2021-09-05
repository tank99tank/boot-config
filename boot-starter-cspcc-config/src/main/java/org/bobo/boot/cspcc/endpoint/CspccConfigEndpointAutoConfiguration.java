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

package org.bobo.boot.cspcc.endpoint;

import org.bobo.boot.cspcc.CspccConfigProperties;
import org.bobo.boot.cspcc.refresh.CspccRefreshHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.endpoint.condition.ConditionalOnEnabledEndpoint;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author xiaojing
 */
@ConditionalOnWebApplication
@ConditionalOnClass(value = Endpoint.class)
@ConditionalOnProperty(name = "cspcc.enabled", matchIfMissing = true)
public class CspccConfigEndpointAutoConfiguration {

	@Autowired
	private CspccConfigProperties nacosConfigProperties;

	@Autowired
	private CspccRefreshHistory nacosRefreshHistory;

	@ConditionalOnMissingBean
	@ConditionalOnEnabledEndpoint
	@Bean
	public CspccConfigEndpoint nacosConfigEndpoint() {
		return new CspccConfigEndpoint(nacosConfigProperties, nacosRefreshHistory);
	}

	@Bean
	public CspccConfigHealthIndicator nacosConfigHealthIndicator() {
		return new CspccConfigHealthIndicator(nacosConfigProperties,
				nacosConfigProperties.configServiceInstance());
	}
}
