package _4.NovemberRecipeMarket.controller.api;

import _4.NovemberRecipeMarket.domain.dto.Response;
import _4.NovemberRecipeMarket.domain.dto.recipe.*;
import _4.NovemberRecipeMarket.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/recipes")
public class RecipeRestController {

    private final RecipeService recipeService;

    @GetMapping("/{recipeId}")
    public Response<RecipeGetResponse> getRecipeById(@PathVariable Long recipeId) {
        RecipeGetResponse recipeGetResponse = recipeService.getRecipeById(recipeId);
        return Response.success(recipeGetResponse);
    }

    @PostMapping()
    public Response<RecipeCreateResponse> createRecipe(Authentication authentication,
                                                    @RequestBody RecipeRequest recipeRequest) {
        RecipeCreateResponse recipeCreateResponse = recipeService.createRecipe(authentication.getName(), recipeRequest);
        return Response.success(recipeCreateResponse);

    }

    @PutMapping("/{recipeId}")
    public Response<RecipeUpdateResponse> updateRecipe(@PathVariable Long recipeId,
                                                        Authentication authentication,
                                                        @RequestBody RecipeRequest recipeRequest) {
        RecipeUpdateResponse recipeUpdateResponse = recipeService.updateRecipe(recipeId, authentication.getName(),
                recipeRequest);
        return Response.success(recipeUpdateResponse);
    }

    @DeleteMapping("/{recipeId}")
    public Response<RecipeDeleteResponse> deleteRecipe(@PathVariable Long recipeId, Authentication authentication) {
        RecipeDeleteResponse recipeDeleteResponse = recipeService.deleteRecipe(recipeId, authentication.getName());
        return Response.success(recipeDeleteResponse);
    }

    @GetMapping("/users/{userId}")
    public Response<Page<RecipeGetResponseForList>> getAllRecipesByUser(@PathVariable Long userId) {
        Pageable pageable = PageRequest.of(0, 20, Sort.by("createdDate").descending());
        Page<RecipeGetResponseForList> recipeList = recipeService.getAllRecipesByUser(userId, pageable);
        return Response.success(recipeList);
    }

    @GetMapping("/list")
    public Response<Page<RecipeGetResponseForList>> getAllRecipes() {
        Pageable pageable = PageRequest.of(0, 20, Sort.by("createdDate").descending());
        Page<RecipeGetResponseForList> recipeList = recipeService.getAllRecipes(pageable);
        return Response.success(recipeList);
    }

    @PostMapping("/{recipeId}/likes")
    public Response<String> pushLike(@PathVariable Long recipeId, Authentication authentication) {
        String result = recipeService.pushLike(recipeId, authentication.getName());
        return Response.success(result);
    }

    @GetMapping("/{recipeId}/likes")
    public Response<Integer> getLikes(@PathVariable Long recipeId) {
        int likeCount = recipeService.getLikeCount(recipeId);
        return Response.success(likeCount);
    }
}
