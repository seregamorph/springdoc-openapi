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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import io.swagger.v3.oas.models.Operation;
import org.apache.commons.lang3.StringUtils;
import org.springdoc.core.Constants;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

/**
 * The type Abstract router function visitor.
 * @author bnasslahsen
 */
public class AbstractRouterFunctionVisitor {

	/**
	 * The Router function datas.
	 */
	protected List<RouterOperation> routerOperations = new ArrayList<>();

	/**
	 * The Nested or paths.
	 */
	protected List<String> nestedOrPaths = new ArrayList<>();

	/**
	 * The Nested and paths.
	 */
	protected List<String> nestedAndPaths = new ArrayList<>();

	/**
	 * The Nested accept headers.
	 */
	protected List<String> nestedAcceptHeaders = new ArrayList<>();

	/**
	 * The Nested content type headers.
	 */
	protected List<String> nestedContentTypeHeaders = new ArrayList<>();

	/**
	 * The Is or.
	 */
	protected boolean isOr;

	/**
	 * The Is nested.
	 */
	protected boolean isNested;

	/**
	 * The Router function data.
	 */
	protected RouterOperation routerOperation;

	protected Map<String,Object> attributes = new HashMap<>();

	/**
	 * Method.
	 *
	 * @param methods the methods
	 */
	public void method(Set<HttpMethod> methods) {
		routerOperation.setMethods(methods);
	}

	/**
	 * Path.
	 *
	 * @param pattern the pattern
	 */
	public void path(String pattern) {
		if (routerOperation != null)
			routerOperation.setPath(pattern);
		else if (isOr)
			nestedOrPaths.add(pattern);
		else if (isNested)
			nestedAndPaths.add(pattern);
	}

	/**
	 * Header.
	 *
	 * @param name the name
	 * @param value the value
	 */
	public void header(String name, String value) {
		if (HttpHeaders.ACCEPT.equals(name)) {
			calculateAccept(value);
		}
		else if (HttpHeaders.CONTENT_TYPE.equals(name)) {
			calculateContentType(value);
		}
		else
			routerOperation.addHeaders(name + "=" + value);
	}



	/**
	 * Query param.
	 *
	 * @param name the name
	 * @param value the value
	 */
	public void queryParam(String name, String value) {
		routerOperation.addQueryParams(name, value);
	}

	/**
	 * Path extension.
	 *
	 * @param extension the extension
	 */
	public void pathExtension(String extension) {
		// Not yet needed
	}

	/**
	 * Param.
	 *
	 * @param name the name
	 * @param value the value
	 */
	public void param(String name, String value) {
		// Not yet needed
	}

	/**
	 * Start and.
	 */
	public void startAnd() {
		// Not yet needed
	}

	/**
	 * And.
	 */
	public void and() {
		// Not yet needed
	}

	/**
	 * End and.
	 */
	public void endAnd() {
		// Not yet needed
	}

	/**
	 * Start or.
	 */
	public void startOr() {
		isOr = true;
	}

	/**
	 * Or.
	 */
	public void or() {
		// Not yet needed
	}

	/**
	 * End or.
	 */
	public void endOr() {
		// Not yet needed
	}

	/**
	 * Start negate.
	 */
	public void startNegate() {
		// Not yet needed
	}

	/**
	 * End negate.
	 */
	public void endNegate() {
		// Not yet needed
	}

	/**
	 * Compute nested.
	 */
	protected void computeNested() {
		if (!nestedAndPaths.isEmpty()) {
			String nestedPath = String.join(StringUtils.EMPTY, nestedAndPaths);
			routerOperations.forEach(routerOperation -> routerOperation.setPath(nestedPath + routerOperation.getPath()));
			nestedAndPaths.clear();
		}
		if (!nestedOrPaths.isEmpty()) {
			List<RouterOperation> routerOperationsClone = new ArrayList<>();
			for (RouterOperation routerOperation : routerOperations) {
				for (String nestedOrPath : nestedOrPaths) {
					RouterOperation routerOperationClone = new RouterOperation(nestedOrPath + routerOperation.getPath(), routerOperation.getConsumes(), routerOperation.getProduces(), routerOperation.getHeaders(), routerOperation.getQueryParams(), routerOperation.getMethods());
					routerOperationsClone.add(routerOperationClone);
				}
			}
			this.routerOperations = routerOperationsClone;
			nestedAndPaths.clear();
		}
		if (!nestedAcceptHeaders.isEmpty()){
			routerOperations.forEach(existingRouterOperation -> existingRouterOperation.addProduces(nestedAcceptHeaders));
			nestedAcceptHeaders.clear();
		}
		if (!nestedContentTypeHeaders.isEmpty()){
			routerOperations.forEach(existingRouterOperation -> existingRouterOperation.addConsumes(nestedContentTypeHeaders));
			nestedContentTypeHeaders.clear();
		}
	}

	/**
	 * Calculate content type.
	 *
	 * @param value the value
	 */
	private void calculateContentType(String value) {
		if (value.contains(",")) {
			String[] mediaTypes = value.substring(1, value.length() - 1).split(", ");
			for (String mediaType : mediaTypes)
				if (routerOperation != null)
					routerOperation.addConsumes(mediaType);
				else
					nestedContentTypeHeaders.addAll(Arrays.asList(mediaTypes));
		}
		else {
			if (routerOperation != null)
				routerOperation.addConsumes(value);
			else
				nestedContentTypeHeaders.add(value);
		}
	}

	/**
	 * Calculate accept.
	 *
	 * @param value the value
	 */
	private void calculateAccept(String value) {
		if (value.contains(",")) {
			String[] mediaTypes = value.substring(1, value.length() - 1).split(", ");
			for (String mediaType : mediaTypes)
				if (routerOperation != null)
					routerOperation.addProduces(mediaType);
				else
					nestedAcceptHeaders.addAll(Arrays.asList(mediaTypes));
		}
		else {
			if (routerOperation != null)
				routerOperation.addProduces(value);
			else
				nestedAcceptHeaders.add(value);
		}
	}

	public List<RouterOperation> getRouterOperations() {
		return routerOperations;
	}

	public void setRouterOperations(List<RouterOperation> routerOperations) {
		this.routerOperations = routerOperations;
	}

	protected void attributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	protected void route() {
		this.routerOperation = new RouterOperation();
		this.routerOperations.add(routerOperation);
		if (attributes.containsKey(Constants.OPERATION_ATTRIBUTE) && attributes.get(Constants.OPERATION_ATTRIBUTE) instanceof Operation) {
			Operation operation = (Operation) attributes.get(Constants.OPERATION_ATTRIBUTE);
			routerOperation.setOperation(operation);
		}
	}
}
