package dev.nightzen.recipes.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import dev.nightzen.recipes.business.entity.Recipe;
import dev.nightzen.recipes.business.entity.User;
import dev.nightzen.recipes.persistance.RecipeRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class RecipeService {
    private final RecipeRepository recipeRepository;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public Optional<Recipe> getRecipe(Long id) {
        return recipeRepository.findById(id);
    }

    @Transactional
    public Long addRecipe(Recipe recipe, User user) {
        recipe.setUser(user);
        Recipe savedRecipe = recipeRepository.save(recipe);
        return savedRecipe.getId();
    }

    @Transactional
    public void updateRecipe(Long id, Recipe newRecipe, User user) {
        Optional<Recipe> recipe = recipeRepository.findById(id);

        if (recipe.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        User recipeUser = recipe.get().getUser();

        if (!Objects.equals(recipeUser.getId(), user.getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        newRecipe.setId(id);
        newRecipe.setUser(user);
        recipeRepository.save(newRecipe);
    }

    @Transactional
    public void deleteRecipe(Long id, User user) {
        Optional<Recipe> recipe = recipeRepository.findById(id);

        if (recipe.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }


        User recipeUser = recipe.get().getUser();

        if (!Objects.equals(recipeUser.getId(), user.getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        recipeRepository.deleteById(id);
    }

    public List<Recipe> searchByCategory(String category) {
        return recipeRepository.findByCategoryIgnoreCaseOrderByDateDesc(category);
    }

    public List<Recipe> searchByName(String name) {
        return recipeRepository.findByNameContainingIgnoreCaseOrderByDateDesc(name);
    }
}
