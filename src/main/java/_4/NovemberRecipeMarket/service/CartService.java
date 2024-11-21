package _4.NovemberRecipeMarket.service;

import _4.NovemberRecipeMarket.domain.dto.cart.*;
import _4.NovemberRecipeMarket.domain.entity.Cart;
import _4.NovemberRecipeMarket.domain.entity.CartItem;
import _4.NovemberRecipeMarket.domain.entity.Item;
import _4.NovemberRecipeMarket.domain.entity.User;
import _4.NovemberRecipeMarket.exception.AppException;
import _4.NovemberRecipeMarket.exception.ErrorCode;
import _4.NovemberRecipeMarket.repository.CartItemRepository;
import _4.NovemberRecipeMarket.repository.CartRepository;
import _4.NovemberRecipeMarket.repository.ItemRepository;
import _4.NovemberRecipeMarket.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;
    private final CartItemRepository cartItemRepository;

    public CartCreateResponse createCart(String username) {
        User user = validateUserByUsername(username);
        Cart cart = new Cart(user);
        cartRepository.save(cart);
        return new CartCreateResponse(cart.getId());
    }

    // 장바구니 삭제
    public String deleteCart(String username) {
        User user = validateUserByUsername(username);
        Cart cart = validateCart(user);
        cartRepository.delete(cart);
        return "장바구니를 삭제했습니다.";
    }

    public Page<CartItemForListResponse> getCartByUser(String username, Pageable pageable) {
        User user = validateUserByUsername(username);
        Cart cart = validateCart(user);
        return cartItemRepository.findAllByCart(cart, pageable).map(this::toListDto);
    }

    public CartItemResponse addToCart(CartItemRequest request, String username) {
        User user = validateUserByUsername(username);
        Cart cart = validateCart(user);
        Item item = validateItem(request.getItemId());
        checkStock(item, request.getQuantity());
        CartItem cartItem = new CartItem(cart, item, request.getQuantity());
        cart.addToCart(cartItem);
        return new CartItemResponse(item.getId(), item.getItemName(), request.getQuantity(),
                "상품이 장바구니에 추가되었습니다.");
    }

    // 상품 삭제
    public CartItemDeleteResponse removeFromCart(CartItemDeleteRequest deleteRequest, String username) {
        User user = validateUserByUsername(username);
        Cart cart = validateCart(user);
        Item item = validateItem(deleteRequest.getItemId());

        CartItem cartItem = hasCartItem(cart, item);
        cart.removeFromCart(cartItem);
        return new CartItemDeleteResponse(item.getItemName());
    }

    public CartItemResponse updateQuantity(CartItemRequest request, String username) {
        User user = validateUserByUsername(username);
        Cart cart = validateCart(user);
        Item item = validateItem(request.getItemId());
        CartItem cartItem = hasCartItem(cart, item);

        int newQuantity = request.getQuantity();
        checkStock(item, newQuantity);
        cartItem.updateQuantity(newQuantity);
        return new CartItemResponse(item.getId(), item.getItemName(), cartItem.getQuantity(),
                "수량을 변경했습니다..");
    }

    private CartItemForListResponse toListDto(CartItem cartItem) {
        return CartItemForListResponse.builder()
                .cartId(cartItem.getCart().getId())
                .itemId(cartItem.getItem().getId())
                .itemName(cartItem.getItem().getItemName())
                .price(cartItem.getItem().getPrice())
                .quantity(cartItem.getQuantity())
                .imagePath("") //TODO: ADD PRODUCT IMAGE
                .build();
    }

    private CartItem hasCartItem(Cart cart, Item item) {
        return cartItemRepository.findByCartAndItem(cart, item)
                .orElseThrow(() -> new AppException(ErrorCode.CART_ITEM_NOT_FOUND));
    }

    private void checkStock(Item item, int quantity) {
        if (item.getStock() < quantity) {
            throw new AppException(ErrorCode.NOT_ENOUGH_STOCK);
        }
    }

    private Cart validateCart(User user) {
        return cartRepository.findCartByUser(user)
                .orElseThrow(() -> new AppException(ErrorCode.CART_NOT_FOUND));
    }

    private Item validateItem(Long itemId) {
        return itemRepository.findById(itemId)
                .orElseThrow(() -> new AppException(ErrorCode.ITEM_NOT_FOUND));
    }

    private User validateUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new AppException(ErrorCode.USERNAME_NOT_FOUND));
        return user;
    }
}
