package test.org.springdoc.api.app69;

import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.parameters.RequestBody;
import org.springdoc.core.Constants;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RoutingConfiguration {

	@Bean
	public RouterFunction<ServerResponse> monoRouterFunction(UserHandler userHandler) {
		return route().GET("/api/user/index", accept(MediaType.APPLICATION_JSON), userHandler::getAll)
				.withAttribute(Constants.OPERATION_ATTRIBUTE, new Operation().operationId("getAllUsers").description("get all the users"))
				.withAttribute(Constants.RESPONSE_ARRAY_TYPE, User.class)

				.GET("/api/user/{id}", accept(MediaType.APPLICATION_JSON), userHandler::getUser)
				.withAttribute(Constants.OPERATION_ATTRIBUTE, new Operation().operationId("getUserById")
						.addParametersItem(new Parameter().in(ParameterIn.PATH.toString()).description("The user Id")))
				.withAttribute(Constants.RESPONSE_TYPE, User.class)

				.POST("/api/user/post", accept(MediaType.APPLICATION_JSON), userHandler::postUser)
				.withAttribute(Constants.OPERATION_ATTRIBUTE, new Operation().operationId("saveUser"))
				.withAttribute(Constants.REQUESTBODY_ATTRIBUTE, new RequestBody().description("new desc"))
				.withAttribute(Constants.REQUESTBODY_TYPE, User.class)

				.PUT("/api/user/put/{id}", accept(MediaType.APPLICATION_JSON), userHandler::putUser)
				.withAttribute(Constants.OPERATION_ATTRIBUTE, new Operation().operationId("putUser")
						.addParametersItem(new Parameter().in(ParameterIn.PATH.toString())))
				.withAttribute(Constants.OPENAPI_ARRAY_TYPE, User.class)

				.DELETE("/api/user/delete/{id}", accept(MediaType.APPLICATION_JSON), userHandler::deleteUser)
				.withAttribute(Constants.BEAN_CLASS, UserRepository.class)
				.withAttribute(Constants.BEAN_METHOD, "deleteUser").build();
	}

	//@Bean
	public RouterFunction<ServerResponse> monoRouterFunctionOld(UserHandler userHandler) {
		return route(GET("/api/user/index").and(accept(MediaType.APPLICATION_JSON)), userHandler::getAll)
				.withAttribute(Constants.OPERATION_ATTRIBUTE, new Operation().operationId("getAllUsers").description("get all the users"))
				.withAttribute(Constants.RESPONSE_ARRAY_TYPE, User.class)

				.andRoute(GET("/api/user/{id}").and(accept(MediaType.APPLICATION_JSON)), userHandler::getUser)
				.withAttribute(Constants.OPERATION_ATTRIBUTE, new Operation().operationId("getUserById")
						.addParametersItem(new Parameter().in(ParameterIn.PATH.toString()).description("The user Id")))
				.withAttribute(Constants.RESPONSE_TYPE, User.class)

				.andRoute(POST("/api/user/post").and(accept(MediaType.APPLICATION_JSON)), userHandler::postUser)
				.withAttribute(Constants.OPERATION_ATTRIBUTE, new Operation().operationId("saveUser"))
				.withAttribute(Constants.REQUESTBODY_ATTRIBUTE, new RequestBody().description("new desc"))
				.withAttribute(Constants.REQUESTBODY_TYPE, User.class)

				.andRoute(PUT("/api/user/put/{id}").and(accept(MediaType.APPLICATION_JSON)), userHandler::putUser)
				.withAttribute(Constants.OPERATION_ATTRIBUTE, new Operation().operationId("putUser")
						.addParametersItem(new Parameter().in(ParameterIn.PATH.toString())))
				.withAttribute(Constants.OPENAPI_ARRAY_TYPE, User.class)

				.andRoute(DELETE("/api/user/delete/{id}").and(accept(MediaType.APPLICATION_JSON)), userHandler::deleteUser)
				.withAttribute(Constants.BEAN_CLASS, UserRepository.class)
				.withAttribute(Constants.BEAN_METHOD, "deleteUser");
	}

}