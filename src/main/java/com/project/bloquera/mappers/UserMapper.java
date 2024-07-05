package com.project.bloquera.mappers;

import org.springframework.stereotype.Component;

import com.project.bloquera.dtos.usuario.UserCreateRequest;
import com.project.bloquera.models.User;

@Component
public class UserMapper {
    
    public User createRequestToModel(UserCreateRequest createRequest) {
        User user = new User();
        user.setUsername(createRequest.username());
        user.setPassword(createRequest.password());
        user.setEmail(createRequest.email());

        return user;
    }
}
