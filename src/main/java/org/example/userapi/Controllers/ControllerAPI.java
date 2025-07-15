package org.example.userapi.Controllers;

import org.example.userapi.MyClasses.Country;
import org.example.userapi.MyClasses.Users;
import org.example.userapi.MyClasses.UsersRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users-api/v1/users")
public class ControllerAPI {
    private final UsersRepository usersRepository;

    public ControllerAPI(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;

        if (usersRepository.count() == 0) {
            usersRepository.save(new Users("John", 34, Country.French));
            usersRepository.save(new Users("Aya", 21, Country.Morocco));
            usersRepository.save(new Users("Den", 21, Country.Italy));
            usersRepository.save(new Users("Kate", 25, Country.Türkiye));
        }
    }

    @GetMapping
    public Iterable<Users> getUsers() {
        return usersRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Users> createUser(@RequestBody Users request) {
        try {
            Users user = new Users(request.getFirstName(), request.getAge(), request.getCountry());
            Users savedUser = usersRepository.save(user);
            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        usersRepository.deleteById(id);
    }
}
