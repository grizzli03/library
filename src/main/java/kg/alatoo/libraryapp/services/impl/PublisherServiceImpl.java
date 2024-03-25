package kg.alatoo.libraryapp.services.impl;

import kg.alatoo.libraryapp.dto.PublisherRequest;
import kg.alatoo.libraryapp.entities.Publisher;
import kg.alatoo.libraryapp.exception.BadRequestException;
import kg.alatoo.libraryapp.exception.NotFoundException;
import kg.alatoo.libraryapp.repositories.PublisherRepository;
import kg.alatoo.libraryapp.services.PublisherService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class PublisherServiceImpl implements PublisherService {
    private final PublisherRepository publisherRepository;

    public PublisherServiceImpl(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void add(PublisherRequest request) {
        if (request.getEmail().isEmpty())
            throw new BadRequestException("set email for publisher!");
        if (publisherRepository.findByEmail(request.getEmail()).isPresent())
            throw new NotFoundException("the publisher with this email is already exist!", HttpStatus.NOT_FOUND);

        Publisher publisher = new Publisher();
        publisher.setEmail(request.getEmail());
        publisher.setName(request.getName());
        publisherRepository.save(publisher);
    }

    @Override
    public void delete(String email) {
        publisherRepository.deleteByEmail(email);
    }
}
