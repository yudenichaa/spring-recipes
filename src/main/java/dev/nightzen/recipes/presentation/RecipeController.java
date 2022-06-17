package dev.nightzen.recipes.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import dev.nightzen.recipes.business.entity.Recipe;
import dev.nightzen.recipes.business.service.RecipeService;
import dev.nightzen.recipes.security.UserDetailsImpl;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/recipe/")
public class RecipeController {
    private final RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("{id}")
    public Recipe getRecipe(@PathVariable Long id) {
        Optional<Recipe> recipe = recipeService.getRecipe(id);

        if (recipe.isPresent()) {
            return recipe.get();
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @PostMapping("new")
    public Map<String, Long> postRecipe(@RequestBody @Valid Recipe recipe,
                                        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return Map.of("id", recipeService.addRecipe(recipe, userDetails.getUser()));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRecipe(@PathVariable Long id,
                             @AuthenticationPrincipal UserDetailsImpl userDetails) {
        recipeService.deleteRecipe(id, userDetails.getUser());
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void putRecipe(@PathVariable Long id,
                          @RequestBody @Valid Recipe recipe,
                          @AuthenticationPrincipal UserDetailsImpl userDetails) {
        recipeService.updateRecipe(id, recipe, userDetails.getUser());
    }

    @GetMapping("search")
    public List<Recipe> searchRecipe(@RequestParam Map<String, String> params) {
        if (params.size() != 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } else if (params.containsKey("category")) {
            return recipeService.searchByCategory(params.get("category"));
        } else if (params.containsKey("name")) {
            return recipeService.searchByName(params.get("name"));
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
