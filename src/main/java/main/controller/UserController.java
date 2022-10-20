package main.controller;


import main.Metod;
import main.entity.UserEntity;
import main.exception.UserAlreadyExistException;
import main.exception.UserNotFoundException;
import main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity registration(@RequestBody UserEntity user){
        try {
            userService.registration(user);
            return ResponseEntity.ok("Пользователь успешно сохранен !");
        }catch (UserAlreadyExistException e){
            return  ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }


    //----------------------------------------//
    @Bean
    @GetMapping("/bean")
    public List bean(){
        List list = Metod.num();
        return list;
    }
    //---------------------------------------//

    @GetMapping("/oneuser")
    public ResponseEntity getOneUser(@RequestParam Long id){
        try {
            return ResponseEntity.ok(userService.getOne(id));
        }catch (UserNotFoundException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }


    @GetMapping("/alluser")
    public ResponseEntity getAllUser(){
        try {
            return ResponseEntity.ok(userService.allUser());
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser (@PathVariable Long id) {
        try {
            return ResponseEntity.ok(userService.delete(id));
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }


    @DeleteMapping("/alldelete")
    public ResponseEntity allDeleteUser(){
        try {
            return ResponseEntity.ok(userService.allDelete());
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
}
