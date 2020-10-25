/*
 *
 *
 *  *
 *  *  *
 *  *  *  * Copyright 2019-2020 the original author or authors.
 *  *  *  *
 *  *  *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  *  *  * you may not use this file except in compliance with the License.
 *  *  *  * You may obtain a copy of the License at
 *  *  *  *
 *  *  *  *      https://www.apache.org/licenses/LICENSE-2.0
 *  *  *  *
 *  *  *  * Unless required by applicable law or agreed to in writing, software
 *  *  *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  *  *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *  *  * See the License for the specific language governing permissions and
 *  *  *  * limitations under the License.
 *  *  *
 *  *
 *
 */

package org.springdoc.core.fn.builders;

import java.lang.annotation.Annotation;

import io.swagger.v3.oas.annotations.extensions.Extension;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.apache.commons.lang3.ArrayUtils;

/**
 * The type Request body builder.
 */
public class RequestBodyBuilder {

	/**
	 * A brief description of the request body.
	 *
	 */
	private String description = "";

	/**
	 * The content of the request body.
	 *
	 */
	private Content[] content = {};

	/**
	 * Determines if the request body is required in the request. Defaults to false.
	 *
	 */
	private boolean required;

	/**
	 * The list of optional extensions
	 *
	 */
	private Extension[] extensions = {};

	/**
	 * A reference to a RequestBody defined in components RequestBodies.
	 *
	 * @since swagger -core 2.0.3
	 */
	private String ref = "";


	/**
	 * Instantiates a new Request body builder.
	 */
	private RequestBodyBuilder() {
	}

	/**
	 * Builder request body builder.
	 *
	 * @return the request body builder
	 */
	public static RequestBodyBuilder builder() {
		return new RequestBodyBuilder();
	}

	/**
	 * Description request body builder.
	 *
	 * @param description the description
	 * @return the request body builder
	 */
	public RequestBodyBuilder description(String description) {
		this.description = description;
		return this;
	}

	/**
	 * Content request body builder.
	 *
	 * @param contentBuilder the content builder
	 * @return the request body builder
	 */
	public RequestBodyBuilder content(ContentBuilder contentBuilder) {
		this.content = ArrayUtils.add( this.content, contentBuilder.build());
		return this;
	}

	/**
	 * Implementation request body builder.
	 *
	 * @param clazz the clazz
	 * @return the request body builder
	 */
	public RequestBodyBuilder implementation(Class clazz) {
		this.content = ArrayUtils.add(this.content, ContentBuilder.builder().schema(SchemaBuilder.builder().implementation(clazz)).build());
		return this;
	}

	/**
	 * Required request body builder.
	 *
	 * @param required the required
	 * @return the request body builder
	 */
	public RequestBodyBuilder required(boolean required) {
		this.required = required;
		return this;
	}

	/**
	 * Extension request body builder.
	 *
	 * @param extensionBuilder the extension builder
	 * @return the request body builder
	 */
	public RequestBodyBuilder extension(ExtensionBuilder extensionBuilder) {
		this.extensions = ArrayUtils.add( this.extensions, extensionBuilder.build());
		return this;
	}

	/**
	 * Ref request body builder.
	 *
	 * @param ref the ref
	 * @return the request body builder
	 */
	public RequestBodyBuilder ref(String ref) {
		this.ref = ref;
		return this;
	}

	/**
	 * Build request body.
	 *
	 * @return the request body
	 */
	public RequestBody build() {
		return new RequestBody(){

			@Override
			public Class<? extends Annotation> annotationType() {
				return null;
			}

			@Override
			public String description() {
				return description;
			}

			@Override
			public Content[] content() {
				return content;
			}

			@Override
			public boolean required() {
				return required;
			}

			@Override
			public Extension[] extensions() {
				return extensions;
			}

			@Override
			public String ref() {
				return ref;
			}
		};
	}
}