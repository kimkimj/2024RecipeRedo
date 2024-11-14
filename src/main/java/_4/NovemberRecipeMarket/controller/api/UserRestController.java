package _4.NovemberRecipeMarket.controller.api;

import _4.NovemberRecipeMarket.domain.dto.*;
import _4.NovemberRecipeMarket.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserRestController {

    private final UserService userService;

    //회원가입
    @PostMapping("/users/join")
    public Response<UserJoinResponse> join(@RequestBody UserJoinRequest userJoinRequest) {
        UserJoinResponse response = userService.join(userJoinRequest);
        return Response.success(response);
    }

    // 로그인
    @PostMapping("/users/login")
    public Response<UserLoginResponse> login(@RequestBody UserLoginRequest loginRequest) {
        String token = userService.login(loginRequest.getUsername(), loginRequest.getPassword());
        return Response.success(new UserLoginResponse(token));
    }


    // update
    @PostMapping("/users/{id}")
    public Response<UserResponse> updateUser(@PathVariable Long id, @RequestBody UserUpdateRequest request,
                                             Authentication authentication) {
        UserResponse response = userService.updateUser(request, id, authentication.getName());
        return Response.success(response);
    }

    // delete
    @DeleteMapping("/users/{id}")
    public Response<UserDeleteResponse> deleteUser(@PathVariable Long id, Authentication authentication) {
        UserDeleteResponse response = userService.deleteUser(id, authentication.getName());
        return Response.success(response);
    }

    // 마이페이지 - 회원 정보 조회
    @PutMapping("/my/{id}")
    public Response<UserResponse> findMyPage(@PathVariable Long id) {
        UserResponse response = userService.findMyPage(id);
        return Response.success(response);
    }
}
