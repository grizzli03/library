package kg.alatoo.libraryapp.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;

    @OneToMany(mappedBy = "publisher")
    private Set<Book> books;
}
