package kg.alatoo.libraryapp.services;

import kg.alatoo.libraryapp.dto.PublisherRequest;

public interface PublisherService {
    void add(PublisherRequest request);

    void delete(String email);
}
