package dev.nightzen.recipes.persistance;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import dev.nightzen.recipes.business.entity.User;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
    Optional<User> findByEmail(String email);
}
