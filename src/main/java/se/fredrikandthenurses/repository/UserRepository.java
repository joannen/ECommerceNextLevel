package se.fredrikandthenurses.repository;

import org.springframework.data.repository.CrudRepository;
import se.fredrikandthenurses.model.User;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);

}
