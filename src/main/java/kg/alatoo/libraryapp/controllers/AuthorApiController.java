package kg.alatoo.libraryapp.controllers;

import kg.alatoo.libraryapp.services.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/author")
@RequiredArgsConstructor
public class AuthorApiController {
    private final AuthorService authorService;

    @GetMapping("/not-found")
    public void throwNotFound() {
        throw new NotFoundException("The object not found");
    }

    @PostMapping("/add")
    public void add(@RequestParam String fullName){
        authorService.add(fullName);
    }
    @DeleteMapping("/delete")
    public void delete(@RequestParam String fullName){
        authorService.delete(fullName);
    }
}
