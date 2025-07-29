package org.example.service;

import org.example.model.Books;
import org.example.repository.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BooksServiceImpl implements BooksService {
    private BooksRepository booksRepository;

    @Autowired
    public BooksServiceImpl(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    @Override
    public List<Books> findAllBooks(){
        return booksRepository.findAll();
    }

    @Override
    public Books findBookById(int id){
        return booksRepository.findById(id).get();
    }

    @Override
    public void saveBook(Books book){
        booksRepository.save(book);
    }

    @Override
    public List<Books> findAllBooksByTitle(String title){
        return booksRepository.findAllByTitle(title);
    }

    @Override
    public List<Books> findAllBooksByPublisher(String publisher){
        return booksRepository.findAllByPublisher(publisher);
    }

    @Override
    public void deleteBook(Books book){
        booksRepository.delete(book);
    }

    @Override
    public void updateBook(Books book){
        booksRepository.save(book);
    }

}
