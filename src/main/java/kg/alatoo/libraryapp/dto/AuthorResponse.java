package kg.alatoo.libraryapp.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AuthorResponse {
    private Long id;
    private String fullName;
    private List<BookResponse> bookResponses;
}
