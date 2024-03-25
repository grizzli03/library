package kg.alatoo.libraryapp.controllers;

import kg.alatoo.libraryapp.dto.AuthorResponse;
import kg.alatoo.libraryapp.services.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
@RequiredArgsConstructor
public class AuthorApiController {
    private final AuthorService authorService;

    @PostMapping("/add")
    public void add(@RequestParam String fullName){
        authorService.add(fullName);
    }
    @DeleteMapping("/delete")
    public void delete(@RequestParam String fullName){
        authorService.delete(fullName);
    }

    @GetMapping("/all")
    public List<AuthorResponse> all(){
        return authorService.getAll();
    }
}
