package com.ombdev.inventorysystemapi.controller;

import com.ombdev.inventorysystemapi.request.DeleteRequest;
import com.ombdev.inventorysystemapi.request.ShowRequest;
import com.ombdev.inventorysystemapi.request.user.CreateUserRequest;
import com.ombdev.inventorysystemapi.request.user.UpdateUserRequest;
import com.ombdev.inventorysystemapi.response.user.CreateUserResponse;
import com.ombdev.inventorysystemapi.response.DeleteResponse;
import com.ombdev.inventorysystemapi.response.user.UserResponse;
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
    public UserResponse show(@RequestBody ShowRequest request){
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
    public DeleteResponse delete(@RequestBody DeleteRequest request){
        return userService.delete(request);
    }

}
