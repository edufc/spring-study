package com.edufc.springstudy.dominio.user;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.edufc.springstudy.util.ControllerUtil;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User User) {
        var response = this.userService.addUser(User);

        return ResponseEntity.created(ControllerUtil.getLocation(response.id())).build();
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        var response = this.userService.getAllUsers();

        return ResponseEntity.ok(response);
    }


    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable final UUID id) {
        var response = this.userService.getUser(id);

        if (response.isPresent())
            return ResponseEntity.ok(response.get());
        else
            return ResponseEntity.notFound().build();
    }

    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody User User) {
        this.userService.updateUser(User);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable final UUID id) {
        var isDeleted = this.userService.deleteUser(id);

        if (isDeleted)
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.notFound().build();
    }
}
