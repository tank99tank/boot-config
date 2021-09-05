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

import org.bobo.boot.cspcc.client.CspccPropertySourceLocator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xiaojing
 */
@Configuration
@ConditionalOnProperty(name = "cspcc.enabled", matchIfMissing = true)
public class CspccConfigBootstrapConfiguration {

	@Bean
	@ConditionalOnMissingBean
	public CspccConfigProperties nacosConfigProperties() {
		return new CspccConfigProperties();
	}

	@Bean
	public CspccPropertySourceLocator nacosPropertySourceLocator(
			CspccConfigProperties nacosConfigProperties) {
		return new CspccPropertySourceLocator(nacosConfigProperties);
	}

}
