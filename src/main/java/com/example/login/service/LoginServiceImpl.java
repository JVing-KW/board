package com.example.login.service;

import com.example.login.domain.LoginEntity;
import com.example.login.dto.LoginDTO;
import com.example.login.repository.LoginRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LoginServiceImpl implements  LoginService{

    @Autowired
    LoginRepository loginRepository;

    @Autowired
    ModelMapper modelMapper;
    @Override
    public void loginCreate(LoginDTO loginDTO) {

        if(loginDTO.getId() != null && loginDTO.getPassword() != null){
            LoginEntity loginEntity   =  modelMapper.map(loginDTO, LoginEntity.class);

            loginRepository.save(loginEntity);
        }else {
            retrun
        }
    }




















}
