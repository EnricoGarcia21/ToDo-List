package com.enrico.projeto_user.user;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "user")
public class UserRestController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "teste")
    public ResponseEntity<Object>teste(){
        return ResponseEntity.ok().body("funfano");
    }


    @PostMapping(value = "create")
    public ResponseEntity create(@RequestBody UserModel user) {
        var usuario = this.userRepository.findByUserName(user.getUserName());

        if(usuario!=null){
            //System.out.println("Usuario ja existe");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuario ja existe");
        }
        var passwordHash = BCrypt.withDefaults().hashToString(12, user.getPassword().toCharArray());
        user.setPassword(passwordHash);
        var userCreated = this.userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
    }
}
