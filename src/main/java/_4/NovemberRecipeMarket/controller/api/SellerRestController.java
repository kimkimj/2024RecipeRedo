package _4.NovemberRecipeMarket.controller.api;

import _4.NovemberRecipeMarket.domain.dto.Response;
import _4.NovemberRecipeMarket.domain.dto.seller.*;
import _4.NovemberRecipeMarket.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/sellers")
@RequiredArgsConstructor
public class SellerRestController {

    private final SellerService sellerService;

    @PostMapping("/join")
    public Response<SellerJoinResponse> join(@RequestBody SellerJoinRequest request) {
        SellerJoinResponse sellerJoinResponse = sellerService.join(request);
        return Response.success(sellerJoinResponse);
    }

    @PostMapping("/login")
    public Response<SellerLoginResponse> login(@RequestBody SellerLoginRequest request) {
        SellerLoginResponse sellerLoginResponse = sellerService.login(request);
        return Response.success(sellerLoginResponse);
    }

    @DeleteMapping("/{id}")
    public Response<SellerDeleteResponse> deleteSeller(@PathVariable Long id, Authentication authentication) {
        SellerDeleteResponse sellerDeleteResponse = sellerService.delete(id, authentication.getName());
        return Response.success(sellerDeleteResponse);
    }

    @PostMapping("/{id}")
    public Response<SellerResponse> updateSeller(@PathVariable Long id, Authentication authentication,
                                                @RequestBody SellerUpdateRequest request) {
        SellerResponse sellerResponse = sellerService.update(id, authentication.getName(), request);
        return Response.success(sellerResponse);
    }

    // 마이페이지 - 회원정보 조회
    @GetMapping("/my/{id}")
    public Response<SellerResponse> findMyPage(@PathVariable Long id, Authentication authentication) {
        SellerResponse sellerResponse = sellerService.getMyPage(id, authentication.getName());
        return Response.success(sellerResponse);
    }

    // 마이페이지 - 비밀번호 변경
    @PutMapping("/my/{id}/password")
    public Response<String> updatePassword(@PathVariable Long id, Authentication authentication,
                                           String currentPassword, String newPassword) {
        String message = sellerService.updatePassword(id, authentication.getName(), currentPassword, newPassword);
        return Response.success(message);
    }

    // 마이페이지 업데이트
    @PutMapping("/my/{id}")
    public Response<SellerResponse> updateMyPpage(@PathVariable Long id, Authentication authentication,
                                                  @RequestBody SellerUpdateRequest request) {
        SellerResponse sellerResponse = sellerService.update(id, authentication.getName(), request);
        return Response.success(sellerResponse);
    }
}
