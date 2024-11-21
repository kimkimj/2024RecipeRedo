package _4.NovemberRecipeMarket.controller.api;

import _4.NovemberRecipeMarket.domain.dto.Response;
import _4.NovemberRecipeMarket.domain.dto.cart.*;
import _4.NovemberRecipeMarket.service.CartService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class CartRestController {

    private final CartService cartService;

    @PostMapping("/carts")
    public Response<CartCreateResponse> createCart(Authentication authentication) {
        CartCreateResponse cart = cartService.createCart(authentication.getName());
        return Response.success(cart);
    }

    // 카트 가져오기
    @GetMapping("/users/carts")
    public Response<Page<CartItemForListResponse>> getCartByUser(Authentication authentication, Pageable pageable) {
        Page<CartItemForListResponse> response = cartService.getCartByUser(authentication.getName(), pageable);
        return Response.success(response);
    }

    // 장바구니 삭제
    @DeleteMapping("/users/carts")
    public Response<String> deleteCart(Authentication authentication) {
        String delteCartResponse = cartService.deleteCart(authentication.getName());
        return Response.success(delteCartResponse);
    }

    // 상품 추가
    @PutMapping("/users/carts/items")
    public Response<CartItemResponse> addItemToCart(@RequestBody CartItemRequest cartItemRequest,
                                                    Authentication authentication) {
        CartItemResponse cartItemResponse = cartService.addToCart(cartItemRequest, authentication.getName());
        return Response.success(cartItemResponse);
    }

    // 상품 제거
    @DeleteMapping("/users/carts/items")
    public Response<CartItemDeleteResponse> removeItemFromCart(@RequestBody CartItemDeleteRequest deleteRequest,
                                                               Authentication authentication) {
        CartItemDeleteResponse deleteResponse = cartService.removeFromCart(deleteRequest, authentication.getName());
        return Response.success(deleteResponse);
    }

    @PutMapping("/users/carts/items/update_quantity")
    public Response<CartItemResponse> updateQuantity(@RequestBody CartItemRequest cartItemRequest,
                                                     Authentication authentication) {
        CartItemResponse cartItemResponse = cartService.updateQuantity(cartItemRequest, authentication.getName());
        return Response.success(cartItemResponse);
    }


}
