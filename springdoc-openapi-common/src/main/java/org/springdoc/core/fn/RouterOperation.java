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

package org.springdoc.core.fn;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import io.swagger.v3.oas.models.Operation;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import org.springframework.http.HttpMethod;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * The type Router operation.
 * @author bnasslahsen
 */
public class RouterOperation implements Comparable<RouterOperation> {

	/**
	 * The Path.
	 */
	private String path;

	/**
	 * The Methods.
	 */
	private RequestMethod[] methods;

	/**
	 * The Consumes.
	 */
	private String[] consumes;

	/**
	 * The Produces.
	 */
	private String[] produces;

	/**
	 * The Headers.
	 */
	private String[] headers;

	/**
	 * The Query params.
	 */
	private Map<String, String> queryParams;

	/**
	 * The Operation model.
	 */
	private Operation operation;

	/**
	 * The Bean class.
	 */
	private Class<?> beanClass;

	/**
	 * The Bean method.
	 */
	private String beanMethod;

	/**
	 * The Parameter types.
	 */
	private Class<?>[] parameterTypes;

	public RouterOperation() { }

	public RouterOperation(String path, String[] consumes, String[] produces, String[] headers, Map<String, String> queryParams, RequestMethod[] methods) {
		this.path = path;
		this.methods = methods;
		this.consumes = consumes;
		this.produces = produces;
		this.headers = headers;
		this.queryParams = queryParams;
	}

	/**
	 * Instantiates a new Router operation.
	 *
	 * @param path the path
	 * @param methods the methods
	 */
	public RouterOperation(String path, RequestMethod[] methods) {
		this.path = path;
		this.methods = methods;
	}

	/**
	 * Gets path.
	 *
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * Sets path.
	 *
	 * @param path the path
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * Get methods request method [ ].
	 *
	 * @return the request method [ ]
	 */
	public RequestMethod[] getMethods() {
		return methods;
	}

	/**
	 * Sets methods.
	 *
	 * @param methods the methods
	 */
	public void setMethods(Set<HttpMethod> methods) {
		this.methods = getMethod(methods);
	}

	/**
	 * Get consumes string [ ].
	 *
	 * @return the string [ ]
	 */
	public String[] getConsumes() {
		return consumes;
	}

	/**
	 * Sets consumes.
	 *
	 * @param consumes the consumes
	 */
	public void setConsumes(String[] consumes) {
		this.consumes = consumes;
	}

	/**
	 * Get produces string [ ].
	 *
	 * @return the string [ ]
	 */
	public String[] getProduces() {
		return produces;
	}

	/**
	 * Sets produces.
	 *
	 * @param produces the produces
	 */
	public void setProduces(String[] produces) {
		this.produces = produces;
	}

	/**
	 * Get headers string [ ].
	 *
	 * @return the string [ ]
	 */
	public String[] getHeaders() {
		return headers;
	}

	/**
	 * Sets headers.
	 *
	 * @param headers the headers
	 */
	public void setHeaders(String[] headers) {
		this.headers = headers;
	}

	/**
	 * Gets query params.
	 *
	 * @return the query params
	 */
	public Map<String, String> getQueryParams() {
		return queryParams;
	}

	/**
	 * Sets query params.
	 *
	 * @param queryParams the query params
	 */
	public void setQueryParams(Map<String, String> queryParams) {
		this.queryParams = queryParams;
	}

	public Operation getOperation() {
		return operation;
	}

	public void setOperation(Operation operation) {
		this.operation = operation;
	}

	/**
	 * Add produces.
	 *
	 * @param produces the produces
	 */
	public void addProduces(List<String> produces) {
		this.produces = ArrayUtils.addAll(this.produces, produces.toArray(new String[0]));
	}

	/**
	 * Add consumes.
	 *
	 * @param consumes the consumes
	 */
	public void addConsumes(List<String> consumes) {
		this.consumes = ArrayUtils.addAll(this.consumes, consumes.toArray(new String[0]));
	}

	/**
	 * Add consumes.
	 *
	 * @param consumes the consumes
	 */
	public void addConsumes(String consumes) {
		if (StringUtils.isNotBlank(consumes))
			this.consumes = ArrayUtils.add(this.consumes, consumes);
	}

	/**
	 * Add produces.
	 *
	 * @param produces the produces
	 */
	public void addProduces(String produces) {
		if (StringUtils.isNotBlank(produces))
			this.produces = ArrayUtils.add(this.produces, produces);
	}

	/**
	 * Add headers.
	 *
	 * @param headers the headers
	 */
	public void addHeaders(String headers) {
		if (StringUtils.isNotBlank(headers))
			this.headers = ArrayUtils.add(this.headers, headers);
	}

	public void setMethods(RequestMethod[] methods) {
		this.methods = methods;
	}

	public Class<?> getBeanClass() {
		return beanClass;
	}

	public void setBeanClass(Class<?> beanClass) {
		this.beanClass = beanClass;
	}

	public String getBeanMethod() {
		return beanMethod;
	}

	public void setBeanMethod(String beanMethod) {
		this.beanMethod = beanMethod;
	}

	public Class<?>[] getParameterTypes() {
		return parameterTypes;
	}

	public void setParameterTypes(Class<?>[] parameterTypes) {
		this.parameterTypes = parameterTypes;
	}

	/**
	 * Add query params.
	 *
	 * @param name the name
	 * @param value the value
	 */
	public void addQueryParams(String name, String value) {
		this.queryParams.put(name, value);
	}

	/**
	 * Get method request method [ ].
	 *
	 * @param methods the methods
	 * @return the request method [ ]
	 */
	private RequestMethod[] getMethod(Set<HttpMethod> methods) {
		if (!CollectionUtils.isEmpty(methods)) {
			return methods.stream().map(this::getRequestMethod).toArray(RequestMethod[]::new);
		}
		return ArrayUtils.toArray();
	}


	/**
	 * Gets request method.
	 *
	 * @param httpMethod the http method
	 * @return the request method
	 */
	private RequestMethod getRequestMethod(HttpMethod httpMethod) {
		RequestMethod requestMethod = null;
		switch (httpMethod) {
			case GET:
				requestMethod = RequestMethod.GET;
				break;
			case POST:
				requestMethod = RequestMethod.POST;
				break;
			case PUT:
				requestMethod = RequestMethod.PUT;
				break;
			case DELETE:
				requestMethod = RequestMethod.DELETE;
				break;
			case PATCH:
				requestMethod = RequestMethod.PATCH;
				break;
			case HEAD:
				requestMethod = RequestMethod.HEAD;
				break;
			case OPTIONS:
				requestMethod = RequestMethod.OPTIONS;
				break;
			default:
				// Do nothing here
				break;
		}
		return requestMethod;
	}

	@Override
	public int compareTo(RouterOperation routerOperation) {
		int result = path.compareTo(routerOperation.getPath());
		if (result == 0 && !ArrayUtils.isEmpty(methods))
			result = methods[0].compareTo(routerOperation.getMethods()[0]);
		if (result == 0 && operation != null && routerOperation.getOperation() != null)
			result = operation.getOperationId().compareTo(routerOperation.getOperation().getOperationId());
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		RouterOperation that = (RouterOperation) o;

		if (path != null ? !path.equals(that.path) : that.path != null) return false;
		// Probably incorrect - comparing Object[] arrays with Arrays.equals
		if (!Arrays.equals(methods, that.methods)) return false;
		// Probably incorrect - comparing Object[] arrays with Arrays.equals
		if (!Arrays.equals(consumes, that.consumes)) return false;
		// Probably incorrect - comparing Object[] arrays with Arrays.equals
		if (!Arrays.equals(produces, that.produces)) return false;
		// Probably incorrect - comparing Object[] arrays with Arrays.equals
		if (!Arrays.equals(headers, that.headers)) return false;
		if (queryParams != null ? !queryParams.equals(that.queryParams) : that.queryParams != null)
			return false;
		if (operation != null ? !operation.equals(that.operation) : that.operation != null)
			return false;
		if (beanClass != null ? !beanClass.equals(that.beanClass) : that.beanClass != null)
			return false;
		if (beanMethod != null ? !beanMethod.equals(that.beanMethod) : that.beanMethod != null)
			return false;
		// Probably incorrect - comparing Object[] arrays with Arrays.equals
		return Arrays.equals(parameterTypes, that.parameterTypes);
	}

	@Override
	public int hashCode() {
		int result = path != null ? path.hashCode() : 0;
		result = 31 * result + Arrays.hashCode(methods);
		result = 31 * result + Arrays.hashCode(consumes);
		result = 31 * result + Arrays.hashCode(produces);
		result = 31 * result + Arrays.hashCode(headers);
		result = 31 * result + (queryParams != null ? queryParams.hashCode() : 0);
		result = 31 * result + (operation != null ? operation.hashCode() : 0);
		result = 31 * result + (beanClass != null ? beanClass.hashCode() : 0);
		result = 31 * result + (beanMethod != null ? beanMethod.hashCode() : 0);
		result = 31 * result + Arrays.hashCode(parameterTypes);
		return result;
	}
}
