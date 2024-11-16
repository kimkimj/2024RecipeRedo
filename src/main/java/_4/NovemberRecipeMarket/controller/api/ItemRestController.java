package _4.NovemberRecipeMarket.controller.api;

import _4.NovemberRecipeMarket.domain.dto.Response;
import _4.NovemberRecipeMarket.domain.dto.item.ItemDeleteResponse;
import _4.NovemberRecipeMarket.domain.dto.item.ItemGetResponse;
import _4.NovemberRecipeMarket.domain.dto.item.ItemRequest;
import _4.NovemberRecipeMarket.domain.dto.item.ItemResponse;
import _4.NovemberRecipeMarket.service.ItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ItemRestController {

    private final ItemService itemService;

    @GetMapping("/items/{itemId}")
    public Response<ItemGetResponse> getItemById(@PathVariable Long itemId) {
        ItemGetResponse itemGetResponse = itemService.getItem(itemId);
        return Response.success(itemGetResponse);
    }

    @PostMapping("/sellers/{sellerId}/items")
    public Response<ItemResponse> createItem(@PathVariable Long sellerId, Authentication authentication,
                                             @Valid @RequestBody ItemRequest itemRequest) {
        ItemResponse itemResponse = itemService.createItem(sellerId, authentication.getName(), itemRequest);
        return Response.success(itemResponse);
    }

    @PutMapping("/sellers/{sellerId}/items/{itemId}")
    public Response<ItemResponse> updateItem(@PathVariable Long sellerId, @PathVariable Long itemId,
                                             Authentication authentication, @Valid @RequestBody ItemRequest itemRequest) {
        ItemResponse itemResponse = itemService.updateItem(sellerId, authentication.getName(), itemId, itemRequest);
        return Response.success(itemResponse);
    }

    @DeleteMapping("sellers/{sellerId}/items/{itemId}")
    public Response<ItemDeleteResponse> deleteItem(@PathVariable Long sellerId, @PathVariable Long itemId,
                                                   Authentication authentication) {
        ItemDeleteResponse itemDeleteResponse = itemService.deleteItem(sellerId, authentication.getName(), itemId);
        return Response.success(itemDeleteResponse);
    }

    @GetMapping("/sellers/{sellerId}/items")
    public Response<Page<ItemGetResponse>> getAllItemsBySeller(@PathVariable Long sellerId, Pageable pageable) {
        Page<ItemGetResponse> allItemsBySeller = itemService.getAllItemsBySeller(sellerId, pageable);
        return Response.success(allItemsBySeller);
    }
}