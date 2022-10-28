package com.ombdev.inventorysystemapi.service;

import com.ombdev.inventorysystemapi.model.SortBy;
import com.ombdev.inventorysystemapi.model.User;
import com.ombdev.inventorysystemapi.repository.UserRepository;
import com.ombdev.inventorysystemapi.response.user.UserResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponse store(User user){
        user.setStatus(false);
        return User.toUserResponse(userRepository.save(user));
    }

    public Page<UserResponse> index(String keyword, int page, int size, SortBy sortBy){

        Page<User> usersPage;

        switch (sortBy){
            case USER_FULL_NAME_ASC -> usersPage = userRepository
                    .findAllByFullNameContainingOrUsernameContainingOrderByFullNameAsc
                            (keyword, keyword, PageRequest.of(page, size));
            case USER_FULL_NAME_DESC -> usersPage = userRepository
                    .findAllByFullNameContainingOrUsernameContainingOrderByFullNameDesc
                            (keyword, keyword, PageRequest.of(page, size));
            case USER_USERNAME_ASC -> usersPage = userRepository
                    .findAllByFullNameContainingOrUsernameContainingOrderByUsernameAsc
                            (keyword, keyword, PageRequest.of(page, size));
            case USER_USERNAME_DESC -> usersPage = userRepository
                    .findAllByFullNameContainingOrUsernameContainingOrderByUsernameDesc
                            (keyword, keyword, PageRequest.of(page, size));
            case USER_EMAIL_ASC -> usersPage = userRepository
                    .findAllByFullNameContainingOrUsernameContainingOrderByEmailAsc
                            (keyword, keyword, PageRequest.of(page, size));
            case USER_EMAIL_DESC -> usersPage = userRepository
                    .findAllByFullNameContainingOrUsernameContainingOrderByEmailDesc
                            (keyword, keyword, PageRequest.of(page, size));
            case USER_PHONE_ASC -> usersPage = userRepository
                    .findAllByFullNameContainingOrUsernameContainingOrderByPhoneAsc
                            (keyword, keyword, PageRequest.of(page, size));
            case USER_PHONE_DESC -> usersPage = userRepository
                    .findAllByFullNameContainingOrUsernameContainingOrderByPhoneDesc
                            (keyword, keyword, PageRequest.of(page, size));
            default ->  usersPage = userRepository
                    .findAllByFullNameContainingOrUsernameContainingOrderByIdDesc
                            (keyword, keyword, PageRequest.of(page, size));
        }

        List<UserResponse> users = usersPage
                .stream()
                .map(User::toUserResponse)
                .collect(Collectors.toList());

        return new PageImpl<>(users, PageRequest.of(page, size), usersPage.getTotalElements());
    }

    public UserResponse update(User user) {
        return User.toUserResponse(userRepository.save(user));
    }

    public UserResponse show(Long id) {
        User user = userRepository.findById(id).get();
        return User.toUserResponse(user);
    }


}
