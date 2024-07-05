package com.project.bloquera.services;

import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.bloquera.dtos.usuario.UserCreateRequest;
import com.project.bloquera.exceptions.ResourceNotFoundException;
import com.project.bloquera.exceptions.notfound.ClienteNotFoundException;
import com.project.bloquera.mappers.UserMapper;
import com.project.bloquera.models.Authority;
import com.project.bloquera.models.User;
import com.project.bloquera.repositories.AuthorityRepository;
import com.project.bloquera.repositories.UserJpaRepository;

@Service
public class UserService {
    private final UserJpaRepository userJpaRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;

    private final UserMapper userMapper;

    public static final String USER_NOT_FOUND = "Usuario #%d no encontrado";

    public UserService(UserJpaRepository userJpaRepository,
        AuthorityRepository authorityRepository,
        PasswordEncoder passwordEncoder,
        UserMapper userMapper) {
        this.userJpaRepository = userJpaRepository;
        this.authorityRepository = authorityRepository;
        this.passwordEncoder = passwordEncoder;

        this.userMapper = userMapper;
    }

    public Page<User> getAllUsuarios(Pageable pageable) {
        return userJpaRepository.findAll(pageable);
    }

    public User getUsuarioById(Long id) {
        return userJpaRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(
                USER_NOT_FOUND.formatted(id)));
    }

    public User createUser(UserCreateRequest userRequest) {
        Authority rol = authorityRepository.findById(userRequest.rol())
            .orElseThrow();

        String encript = passwordEncoder.encode(userRequest.password());
        UserCreateRequest encriptUserRequest = new UserCreateRequest(
            userRequest.username(), encript, userRequest.email(), userRequest.rol());

        User user = userMapper.createRequestToModel(encriptUserRequest);
        user.setAuthorities(Set.of(rol));

        return userJpaRepository.save(user);
    }

    public User updateUser(Long id, User user) {
        boolean productoExists = userJpaRepository.existsById(id);

        if (!productoExists) {
            throw new ResourceNotFoundException(USER_NOT_FOUND.formatted(id));
        }

        user.setId(id);

        return userJpaRepository.save(user);
    }

    public void deleteUser(Long id) {
        User user = userJpaRepository.findById(id)
            .orElseThrow(() -> new ClienteNotFoundException(id));

        userJpaRepository.delete(user);
    }
}
