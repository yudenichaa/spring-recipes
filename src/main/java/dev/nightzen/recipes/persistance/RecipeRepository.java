package dev.nightzen.recipes.persistance;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import dev.nightzen.recipes.business.entity.Recipe;

import java.util.List;

@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Long> {
    List<Recipe> findByCategoryIgnoreCaseOrderByDateDesc(String category);
    List<Recipe> findByNameContainingIgnoreCaseOrderByDateDesc(String name);
}
