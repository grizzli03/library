package kg.alatoo.libraryapp.repositories;

import kg.alatoo.libraryapp.entities.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long> {
    Optional<Publisher> findByName(String name);
    void deleteByEmail(String email);
}
