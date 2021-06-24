package guru.springframework.repository;

import guru.springframework.module.Catagory;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CatagoryRepository extends CrudRepository<Catagory,Long> {
    // it is doing one type of query method to retrieve only one tuple from our database by the description
    // which is string type . and as you know we use optional just incase that the return is null so
    // in this case there is not any null point exception .
    Optional<Catagory> findByDescription(String description);
}
