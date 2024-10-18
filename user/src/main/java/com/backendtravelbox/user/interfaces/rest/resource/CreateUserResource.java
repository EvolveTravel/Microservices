package com.backendtravelbox.user.interfaces.rest.resource;

public record CreateUserResource (String firstName,
                                  String lastName,
                                  String email,
                                  String userName,
                                  String password,
                                  String phoneNumber) {
}