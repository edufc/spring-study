package com.edufc.springstudy.dominio.user;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class UserService {
    @Autowired
    private UserRepository UserRepository;

    public List<User> getAllUsers() {
        var entitys = this.UserRepository.findAll();

        return UserMapper.valueOf(entitys);
    }

    public Optional<User> getUser(final UUID id) {
        return this.UserRepository.findById(id).map(UserMapper::valueOf);
    }

    @Transactional
    public User addUser(final User dto) {
        var entity = this.UserRepository.save(UserMapper.valueOf(dto));

        return UserMapper.valueOf(entity);
    }

    @Transactional
    public void updateUser(final User dto) {
        var entity = this.UserRepository.findById(dto.id());


        if (entity.isPresent()) {
            UserMapper.merge(entity.get(), dto);
            this.UserRepository.save(entity.get());
        }
    }

    @Transactional
    public boolean deleteUser(final UUID id) {
        var entity = this.UserRepository.findById(id);

        if (entity.isPresent()) {
            this.UserRepository.delete(entity.get());
            return true;
        } else {
            return false;
        }
    }
}