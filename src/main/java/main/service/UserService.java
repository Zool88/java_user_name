package main.service;

import main.entity.UserEntity;
import main.exception.UserAlreadyExistException;
import main.exception.UserNotFoundException;
import main.model.User;
import main.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public UserEntity registration(UserEntity user) throws UserAlreadyExistException {
        if(userRepo.findByUsername(user.getUsername()) != null){
            throw new UserAlreadyExistException("Пользователь уже сеществует !");
        }
        return userRepo.save(user);
    }

    public User getOne(Long id) throws UserNotFoundException {
        UserEntity user = userRepo.findById(id).get();
        if(user == null) {
            throw new UserNotFoundException("Пользователь не найден");
        }
        return User.toModel(user);
    }




    public String delete(Long id){
        userRepo.deleteById(id);
        return "ID с номером " + id + " удален !!!";
    }

    public String allDelete(){
        userRepo.deleteAll();
        return "Все пользователи удален !!!";
    }

    public Iterable<UserEntity> allUser(){
        return userRepo.findAll();
    }

}
