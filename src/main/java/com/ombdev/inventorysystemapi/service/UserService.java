package com.ombdev.inventorysystemapi.service;

import com.ombdev.inventorysystemapi.model.User;
import com.ombdev.inventorysystemapi.repository.UserRepository;
import com.ombdev.inventorysystemapi.request.DeleteRequest;
import com.ombdev.inventorysystemapi.request.ShowRequest;
import com.ombdev.inventorysystemapi.request.user.UserRequest;
import com.ombdev.inventorysystemapi.response.DeleteResponse;
import com.ombdev.inventorysystemapi.response.user.UserResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponse create(User user){
        user.setStatus(false);
        return User.toUserResponse(userRepository.save(user));
    }

    public List<UserResponse> index(){

        return userRepository.findAll()
                .stream()
                .map(User::toUserResponse)
                .collect(Collectors.toList());
    }

    public UserResponse update(User user) {
        return User.toUserResponse(userRepository.save(user));
    }

    public DeleteResponse delete(Long id) {
        userRepository.deleteById(id);
        return new DeleteResponse("User deleted successfully :)");
    }

    public UserResponse show(Long id) {
        User user = userRepository.findById(id).get();
        return User.toUserResponse(user);
    }
}
