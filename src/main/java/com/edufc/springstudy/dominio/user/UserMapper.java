package com.edufc.springstudy.dominio.user;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    public static User valueOf(final UserEntity entity) {
        return new User(entity.getId(), entity.getName());
    }

    public static List<User> valueOf(final List<UserEntity> entity) {
        return entity.stream().map(UserMapper::valueOf).collect(Collectors.toList());
    }

    public static UserEntity valueOf(final User dto) {
        return new UserEntity(dto.id(), dto.name());
    }

    public static void merge(UserEntity entity, final User dto) {
        entity.setName(dto.name());
    }
}