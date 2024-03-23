package kg.alatoo.libraryapp.mapper;

import kg.alatoo.libraryapp.dto.BookDTO;
import kg.alatoo.libraryapp.dto.PublisherDTO;
import kg.alatoo.libraryapp.entities.Book;
import kg.alatoo.libraryapp.entities.Publisher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

public interface BookMapper {



//    @Mapping(target = "publisher", ignore = true)


    BookDTO bookToBookDto(Book book);
}
