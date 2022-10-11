package com.ombdev.inventorysystemapi.controller;

import com.ombdev.inventorysystemapi.request.CreateUserRequest;
import com.ombdev.inventorysystemapi.request.DeleteUserRequest;
import com.ombdev.inventorysystemapi.request.ShowUserRequest;
import com.ombdev.inventorysystemapi.request.UpdateUserRequest;
import com.ombdev.inventorysystemapi.response.CreateUserResponse;
import com.ombdev.inventorysystemapi.response.DeleteResponse;
import com.ombdev.inventorysystemapi.response.UserResponse;
import com.ombdev.inventorysystemapi.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/admin/users")
    public List<UserResponse> index(){
        return userService.getUsers();
    }

    @GetMapping("/admin/users/show")
    public UserResponse show(@RequestBody ShowUserRequest request){
        return userService.showUser(request);
    }

    @PostMapping("/admin/users/create")
    public CreateUserResponse create(@RequestBody CreateUserRequest request){
        return userService.createUser(request);
    }

    @PutMapping("/admin/users/update")
    public UserResponse update(@RequestBody UpdateUserRequest request){
        return userService.updateUser(request);
    }

    @DeleteMapping("/admin/users/delete")
    public DeleteResponse delete(@RequestBody DeleteUserRequest request){
        return userService.delete(request);
    }

}
