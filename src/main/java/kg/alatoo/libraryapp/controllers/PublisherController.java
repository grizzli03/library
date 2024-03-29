package kg.alatoo.libraryapp.controllers;

import kg.alatoo.libraryapp.dto.PublisherRequest;
import kg.alatoo.libraryapp.services.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/publisher")
@RequiredArgsConstructor
public class PublisherController {
    private final PublisherService service;

    @PostMapping("/add")
    public void addPublisher(@Validated @RequestBody PublisherRequest request){
        service.add(request);
    }
    @PostMapping("/delete")
    public void delete(@RequestParam String email){
        service.delete(email);
    }
}
