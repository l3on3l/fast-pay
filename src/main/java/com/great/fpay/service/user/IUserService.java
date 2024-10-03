package com.great.fpay.service.user;

import com.great.fpay.dto.UserResponse;

import java.util.List;

public interface IUserService {

    UserResponse findById(Long id);

    List<UserResponse> findAll();

    void deleteById(Long id);

}
