package com.project.shop.Service.Imp;

import com.project.shop.DTO.CreationalUserDTO;
import com.project.shop.DTO.UserDTO;
import com.project.shop.ExceptionHandler.ResourceNotFoundException;
import com.project.shop.ExceptionHandler.UserDataNotAccepted;
import com.project.shop.Model.UserSec;
import com.project.shop.Repository.IUserRepository;
import com.project.shop.Service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Optional;

@Service
public class UserService implements IUserService {
    @Autowired
    private IUserRepository userRepository;

    @Override
    public UserDTO createUser(CreationalUserDTO user) {

        UserSec newUserSec = new UserSec(user);
        newUserSec.setPassword(this.encryptPassword(user.getPassword()));

        userRepository.save(newUserSec);
        return new UserDTO(newUserSec);
    }

    @Override
    public UserDTO editUser(UserDTO userDTO) {
        Optional<UserSec> userOptional = userRepository.findById(userDTO.getId());

        if (userOptional.isEmpty())
            throw new ResourceNotFoundException("This user doesn't exist.");

        UserSec userSec = userOptional.get();

        userSec.setName(userDTO.getName());
        userSec.setLastname(userDTO.getLastname());
        userSec.setAddress(userDTO.getAddress());
        userSec.setEmail(userDTO.getEmail());
        userSec.setPhoneNumber(userDTO.getPhoneNumber());
        userSec.setBirthday(userDTO.getBirthday());
        userSec.setEnabled(userDTO.isActive());

        userRepository.save(userSec);

        return userDTO;
    }

    @Override
    public void deleteUser(Long userId) {
        Optional<UserSec> userSec = userRepository.findById(userId);

        if (userSec.isEmpty())
            throw new ResourceNotFoundException("This user doesn't exist.");

        UserSec user = userSec.get();
        user.setEnabled(false);

        userRepository.save(user);
    }

    @Override
    public UserDTO editPassword(UserSec user) {
        Optional<UserSec> userOptional = userRepository.findById(user.getId());

        if (userOptional.isEmpty())
            throw new ResourceNotFoundException("This user doesn't exist.");

        UserSec userSecAux = userOptional.get();
        userSecAux.setPassword(this.encryptPassword(user.getPassword()));

        userRepository.save(userSecAux);

        return new UserDTO(userSecAux);
    }

    @Override
    public String encryptPassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

    @Override
    public UserDTO getUserById(long id) {
        Optional<UserSec> userOptional = userRepository.findById(id);

        if (userOptional.isEmpty())
            throw new ResourceNotFoundException("This id doesn't exist.");

        return new UserDTO(userOptional.get());
    }

    @Override
    public UserDTO getUserByUsername(String name) {

        Optional<UserSec> userOptional = userRepository.findUserEntityByUsername(name);

        if (userOptional.isEmpty())
            throw new ResourceNotFoundException("This id doesn't exist.");


        return new UserDTO(userOptional.get());
    }
}