package test.org.springdoc.api.app69;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserRepository {

    public Mono<User> getUserById(Long id);

    public Flux<User> getAllUsers();

	public Flux<User> getAllUsers(String firstname);

    public Mono<Void> saveUser(Mono<User> user);

    public Mono<User> putUser(Long id, Mono<User> user);

    public Mono<String> deleteUser(@Parameter(in = ParameterIn.PATH) Long id);
}