/*
 *
 *  *
 *  *  * Copyright 2019-2020 the original author or authors.
 *  *  *
 *  *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  *  * you may not use this file except in compliance with the License.
 *  *  * You may obtain a copy of the License at
 *  *  *
 *  *  *      https://www.apache.org/licenses/LICENSE-2.0
 *  *  *
 *  *  * Unless required by applicable law or agreed to in writing, software
 *  *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *  * See the License for the specific language governing permissions and
 *  *  * limitations under the License.
 *  *
 *
 */

package org.springdoc.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import io.swagger.v3.oas.models.OpenAPI;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springdoc.core.customizers.OperationCustomizer;

import org.springframework.util.CollectionUtils;

import static org.springdoc.core.Constants.GROUP_NAME_NOT_NULL;

/**
 * The type Grouped open api.
 * @author bnasslahsen
 */
public class GroupedOpenApi {

	/**
	 * The Group.
	 */
	private final String group;

	/**
	 * The Open api customizers.
	 */
	private final List<OpenApiCustomizer> openApiCustomizers;

	/**
	 * The Operation customizers.
	 */
	private final List<OperationCustomizer> operationCustomizers;

	/**
	 * The Paths to match.
	 */
	private final List<String> pathsToMatch;

	/**
	 * The Packages to scan.
	 */
	private final List<String> packagesToScan;

	/**
	 * The Packages to exclude.
	 */
	private final List<String> packagesToExclude;

	/**
	 * The Paths to exclude.
	 */
	private final List<String> pathsToExclude;

	/**
	 * The Produces to match.
	 */
	private final List<String> producesToMatch;

	/**
	 * The Headers to match.
	 */
	private final List<String> headersToMatch;

	/**
	 * The Consumes to match.
	 */
	private final List<String> consumesToMatch;

	/**
	 * Instantiates a new Grouped open api.
	 *
	 * @param builder the builder
	 */
	private GroupedOpenApi(Builder builder) {
		this.group = Objects.requireNonNull(builder.group, GROUP_NAME_NOT_NULL);
		this.pathsToMatch = builder.pathsToMatch;
		this.packagesToScan = builder.packagesToScan;
		this.producesToMatch = builder.producesToMatch;
		this.consumesToMatch = builder.consumesToMatch;
		this.headersToMatch = builder.headersToMatch;
		this.packagesToExclude = builder.packagesToExclude;
		this.pathsToExclude = builder.pathsToExclude;
		this.openApiCustomizers = Objects.requireNonNull(builder.openApiCustomizers);
		this.operationCustomizers = Objects.requireNonNull(builder.operationCustomizers);
		if (CollectionUtils.isEmpty(this.pathsToMatch)
				&& CollectionUtils.isEmpty(this.packagesToScan)
				&& CollectionUtils.isEmpty(this.producesToMatch)
				&& CollectionUtils.isEmpty(this.consumesToMatch)
				&& CollectionUtils.isEmpty(this.headersToMatch)
				&& CollectionUtils.isEmpty(this.pathsToExclude)
				&& CollectionUtils.isEmpty(this.packagesToExclude)
				&& CollectionUtils.isEmpty(openApiCustomizers)
				&& CollectionUtils.isEmpty(operationCustomizers))
			throw new IllegalStateException("Packages to scan or paths to filter or openApiCustomizers/operationCustomizers can not be all null for the group: " + this.group);
	}

	/**
	 * Builder builder.
	 *
	 * @return the builder
	 */
	public static Builder builder() {
		return new Builder();
	}

	/**
	 * Gets produces to match.
	 *
	 * @return the produces to match
	 */
	public List<String> getProducesToMatch() {
		return producesToMatch;
	}

	/**
	 * Gets headers to match.
	 *
	 * @return the headers to match
	 */
	public List<String> getHeadersToMatch() {
		return headersToMatch;
	}

	/**
	 * Gets consumes to match.
	 *
	 * @return the consumes to match
	 */
	public List<String> getConsumesToMatch() {
		return consumesToMatch;
	}

	/**
	 * Gets group.
	 *
	 * @return the group
	 */
	public String getGroup() {
		return group;
	}

	/**
	 * Gets paths to match.
	 *
	 * @return the paths to match
	 */
	public List<String> getPathsToMatch() {
		return pathsToMatch;
	}

	/**
	 * Gets packages to scan.
	 *
	 * @return the packages to scan
	 */
	public List<String> getPackagesToScan() {
		return packagesToScan;
	}

	/**
	 * Gets packages to exclude.
	 *
	 * @return the packages to exclude
	 */
	public List<String> getPackagesToExclude() {
		return packagesToExclude;
	}

	/**
	 * Gets paths to exclude.
	 *
	 * @return the paths to exclude
	 */
	public List<String> getPathsToExclude() {
		return pathsToExclude;
	}

	/**
	 * Will be deleted in 2.0.
	 */
	@Deprecated
	public List<OpenApiCustomiser> getOpenApiCustomisers() {
		return openApiCustomizers.stream()
				.map(customizer -> (OpenApiCustomiser) customizer::customize)
				.collect(Collectors.toList());
	}

	/**
	 * Gets open api customisers.
	 *
	 * @return the open api customisers
	 */
	public List<OpenApiCustomizer> getOpenApiCustomizers() {
		return openApiCustomizers;
	}

	/**
	 * Gets operation customizers.
	 *
	 * @return the operation customizers
	 */
	public List<OperationCustomizer> getOperationCustomizers() {
		return operationCustomizers;
	}

	/**
	 * The type Builder.
	 * @author bnasslahsen
	 */
	public static class Builder {
		/**
		 * The Open api customizers.
		 */
		private final List<OpenApiCustomizer> openApiCustomizers = new ArrayList<>();

		/**
		 * The Operation customizers.
		 */
		private final List<OperationCustomizer> operationCustomizers = new ArrayList<>();

		/**
		 * The Group.
		 */
		private String group;

		/**
		 * The Paths to match.
		 */
		private List<String> pathsToMatch;

		/**
		 * The Packages to scan.
		 */
		private List<String> packagesToScan;

		/**
		 * The Packages to exclude.
		 */
		private List<String> packagesToExclude;

		/**
		 * The Paths to exclude.
		 */
		private List<String> pathsToExclude;

		/**
		 * The Produces to match.
		 */
		private List<String> producesToMatch;

		/**
		 * The Headers to match.
		 */
		private List<String> headersToMatch;

		/**
		 * The Consumes to match.
		 */
		private List<String> consumesToMatch;

		/**
		 * Instantiates a new Builder.
		 */
		private Builder() {
			// use static factory method in parent class
		}

		/**
		 * Group builder.
		 *
		 * @param group the group
		 * @return the builder
		 */
		public Builder group(String group) {
			this.group = group;
			return this;
		}

		/**
		 * Paths to match builder.
		 *
		 * @param pathsToMatch the paths to match
		 * @return the builder
		 */
		public Builder pathsToMatch(String... pathsToMatch) {
			this.pathsToMatch = Arrays.asList(pathsToMatch);
			return this;
		}

		/**
		 * Packages to scan builder.
		 *
		 * @param packagesToScan the packages to scan
		 * @return the builder
		 */
		public Builder packagesToScan(String... packagesToScan) {
			this.packagesToScan = Arrays.asList(packagesToScan);
			return this;
		}

		/**
		 * Produces to match builder.
		 *
		 * @param producesToMatch the produces to match
		 * @return the builder
		 */
		public Builder producesToMatch(String... producesToMatch) {
			this.producesToMatch = Arrays.asList(producesToMatch);
			return this;
		}

		/**
		 * Consumes to match builder.
		 *
		 * @param consumesToMatch the consumes to match
		 * @return the builder
		 */
		public Builder consumesToMatch(String... consumesToMatch) {
			this.consumesToMatch = Arrays.asList(consumesToMatch);
			return this;
		}

		/**
		 * Headers to match builder.
		 *
		 * @param headersToMatch the headers to match
		 * @return the builder
		 */
		public Builder headersToMatch(String... headersToMatch) {
			this.headersToMatch = Arrays.asList(headersToMatch);
			return this;
		}

		/**
		 * Paths to exclude builder.
		 *
		 * @param pathsToExclude the paths to exclude
		 * @return the builder
		 */
		public Builder pathsToExclude(String... pathsToExclude) {
			this.pathsToExclude = Arrays.asList(pathsToExclude);
			return this;
		}

		/**
		 * Packages to exclude builder.
		 *
		 * @param packagesToExclude the packages to exclude
		 * @return the builder
		 */
		public Builder packagesToExclude(String... packagesToExclude) {
			this.packagesToExclude = Arrays.asList(packagesToExclude);
			return this;
		}

		/**
		 * Add open api customizer builder.
		 *
		 * @param openApiCustomizer the open api customizer
		 * @return the builder
		 */
		public Builder addOpenApiCustomizer(OpenApiCustomizer openApiCustomizer) {
			this.openApiCustomizers.add(openApiCustomizer);
			return this;
		}

		/**
		 * This method kept only for back compatibility, will be deleted in 2.0.
		 *
		 * @see #addOpenApiCustomizer(OpenApiCustomizer)
		 */
		@Deprecated
		public Builder addOpenApiCustomiser(OpenApiCustomiser openApiCustomiser) {
			return addOpenApiCustomizer(openApiCustomiser);
		}

		/**
		 * Add operation customizer builder.
		 *
		 * @param operationCustomizer the operation customizer
		 * @return the builder
		 */
		public Builder addOperationCustomizer(OperationCustomizer operationCustomizer) {
			this.operationCustomizers.add(operationCustomizer);
			return this;
		}

		/**
		 * Build grouped open api.
		 *
		 * @return the grouped open api
		 */
		public GroupedOpenApi build() {
			return new GroupedOpenApi(this);
		}
	}
}
