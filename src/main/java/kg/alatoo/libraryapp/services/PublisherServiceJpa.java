package kg.alatoo.libraryapp.services;

import kg.alatoo.libraryapp.dto.PublisherRequest;
import kg.alatoo.libraryapp.entities.Publisher;
import kg.alatoo.libraryapp.repositories.PublisherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PublisherServiceJpa implements PublisherService{
    private final PublisherRepository publisherRepository;

    @Override
    public void add(PublisherRequest request) {
        Publisher publisher = new Publisher();
        publisher.setName(request.getName());
        publisher.setEmail(request.getEmail());
        publisherRepository.save(publisher);
    }

    @Override
    public void delete(String email) {
        publisherRepository.deleteByEmail(email);
    }
}
