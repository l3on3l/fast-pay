package com.great.fpay.service.user;

import com.great.fpay.dto.UserResponse;
import com.great.fpay.exceptions.UserNotFoundException;
import com.great.fpay.mapper.UserMapper;
import com.great.fpay.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Override
    public UserResponse findById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toUserResponse)
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public List<UserResponse> findAll() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toUserResponse)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        if (userRepository.findById(id).isEmpty()) {
            throw new UserNotFoundException();
        }

        userRepository.deleteById(id);
    }
}
