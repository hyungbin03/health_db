package com.example.healthapp.healthapp.controller;

import com.example.healthapp.healthapp.entity.User;
import com.example.healthapp.healthapp.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserRepository repo;
    public UserController(UserRepository repo){ this.repo = repo; }

    @GetMapping
    public List<User> getAllUsers() {
        return repo.findAll();  // 여기서 예외가 터지면 500
    }

    @GetMapping("/{id}")
    public User get(@PathVariable Integer id) {
        return repo.findById(id).orElseThrow();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody User u){ return repo.save(u); }

    @PutMapping("/{id}")
    public User update(@PathVariable Integer id, @RequestBody User u){
        User old = repo.findById(id).orElseThrow();
        old.setUsername(u.getUsername());
        old.setEmail(u.getEmail());
        old.setPassword(u.getPassword());
        return repo.save(old);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){ repo.deleteById(id); }
}
