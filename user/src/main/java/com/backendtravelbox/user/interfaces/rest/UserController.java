package com.backendtravelbox.user.interfaces.rest;

import com.backendtravelbox.user.domain.model.commands.DeleteUserCommand;
import com.backendtravelbox.user.domain.model.queries.GetAllUserQuery;
import com.backendtravelbox.user.domain.model.queries.GetUserByIdQuery;
import com.backendtravelbox.user.domain.model.queries.GetUserByUsernameAndPassword;
import com.backendtravelbox.user.domain.service.UserCommandService;
import com.backendtravelbox.user.domain.service.UserQueryService;
import com.backendtravelbox.user.interfaces.rest.resource.CreateUserResource;
import com.backendtravelbox.user.interfaces.rest.resource.UpdateUserResource;
import com.backendtravelbox.user.interfaces.rest.resource.UserResource;
import com.backendtravelbox.user.interfaces.rest.transform.CreateUserCommandFromResourceAssembler;
import com.backendtravelbox.user.interfaces.rest.transform.UpdateUserCommandFromResourceAssembler;
import com.backendtravelbox.user.interfaces.rest.transform.UserResourceFromEntityAssembler;
import com.backendtravelbox.user.shared.JwtToken;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "api/v1/users", produces = APPLICATION_JSON_VALUE)
@Tag(name = "User", description = "User Management Endpoints")
public class UserController {

    private final UserCommandService userCommandService;
    private final UserQueryService userQueryService;

    private final JwtToken jwtToken;

    public UserController(UserCommandService userCommandService, UserQueryService userQueryService, JwtToken jwtToken) {
        this.userCommandService = userCommandService;
        this.userQueryService = userQueryService;
        this.jwtToken = jwtToken;
    }

    @PostMapping
    public ResponseEntity<UserResource> createUser(@RequestBody CreateUserResource createUserResource) {

        var createUserCommand = CreateUserCommandFromResourceAssembler.toCommandFromResource(createUserResource);
        var id = userCommandService.handle(createUserCommand);
        if (id == 0L) {
            return ResponseEntity.badRequest().build();
        }

        var getUserByIdQuery = new GetUserByIdQuery(id);
        var user = userQueryService.handle(getUserByIdQuery);
        if (user.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(user.get());
        return new ResponseEntity<>(userResource, HttpStatus.CREATED);
    }

    @GetMapping("/{username}/{password}")
    public ResponseEntity<String> login(@PathVariable String username, @PathVariable String password) {
        var getUserByUsernameAndPassword = new GetUserByUsernameAndPassword(username, password);
        var user = userQueryService.handle(getUserByUsernameAndPassword);
        if (user.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        String tokenString = jwtToken.generateToken(user.get().getId());

        return ResponseEntity.ok(tokenString);
    }

    @GetMapping
    public ResponseEntity<List<UserResource>> getAllUsers() {
        var getAllUserQuery = new GetAllUserQuery();
        var user = userQueryService.handle(getAllUserQuery);
        var userResource = user.stream().map(UserResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(userResource);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResource> getUserById(@PathVariable Long id) {
        var getUserByIdQuery = new GetUserByIdQuery(id);
        var user = userQueryService.handle(getUserByIdQuery);
        if (user.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(user.get());
        return ResponseEntity.ok(userResource);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResource> updateUser(@PathVariable Long id, @RequestBody UpdateUserResource updateUserResource) {
        var updateUserCommand = UpdateUserCommandFromResourceAssembler.toCommandFromResource(id, updateUserResource);
        var updateUser = userCommandService.handle(updateUserCommand);
        if (updateUser.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(updateUser.get());
        return ResponseEntity.ok(userResource);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        var deleteUserCommand = new DeleteUserCommand(id);
        userCommandService.handle(deleteUserCommand);
        return ResponseEntity.ok("User with given id successfully deleted");
    }

    @GetMapping("/myObject")
    public ResponseEntity<UserResource> getUserByToken(@RequestHeader("Authorization") String token) {
        if (token == null) {
            return ResponseEntity.badRequest().build();
        }

        Long id = Long.valueOf(jwtToken.getIdFromToken(token));

        var getUserByIdQuery = new GetUserByIdQuery(id);
        var user = userQueryService.handle(getUserByIdQuery);
        if (user.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(user.get());
        return ResponseEntity.ok(userResource);
    }
}