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

import java.util.ArrayList;
import java.util.List;

import org.bobo.boot.cspcc.CspccConfigProperties;
import org.bobo.boot.cspcc.client.CspccPropertySource;
import org.bobo.boot.cspcc.client.CspccPropertySourceRepository;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.util.StringUtils;

import com.alibaba.nacos.api.config.ConfigService;

/**
 * @author xiaojing
 */
public class CspccConfigHealthIndicator extends AbstractHealthIndicator {

	private final CspccConfigProperties nacosConfigProperties;

	private final List<String> dataIds;

	private final ConfigService configService;

	public CspccConfigHealthIndicator(CspccConfigProperties nacosConfigProperties,
			ConfigService configService) {
		this.nacosConfigProperties = nacosConfigProperties;
		this.configService = configService;

		this.dataIds = new ArrayList<>();
		for (CspccPropertySource nacosPropertySource : CspccPropertySourceRepository
				.getAll()) {
			this.dataIds.add(nacosPropertySource.getDataId());
		}
	}

	@Override
	protected void doHealthCheck(Health.Builder builder) throws Exception {
		for (String dataId : dataIds) {
			try {
				String config = configService.getConfig(dataId,
						nacosConfigProperties.getGroup(),
						nacosConfigProperties.getTimeout());
				if (StringUtils.isEmpty(config)) {
					builder.down().withDetail(String.format("dataId: '%s', group: '%s'",
							dataId, nacosConfigProperties.getGroup()), "config is empty");
				}
			}
			catch (Exception e) {
				builder.down().withDetail(String.format("dataId: '%s', group: '%s'",
						dataId, nacosConfigProperties.getGroup()), e.getMessage());
			}
		}
		builder.up().withDetail("dataIds", dataIds);
	}
}
