package com.qilongtu.mongodb.dao;


import com.qilongtu.mongodb.entity.Book;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface BookRepository extends ElasticsearchRepository<Book,Integer> {

    /**
     * 按照作者来查询
     * @param author
     * @return
     */
    List<Book> findByAuthor(String author);

    List<Book> findByBookName(String bookName);

    List<Book> findByBookNameLike(String bookName);


    Optional<Book> findById(Integer id);



    @Override
    List<Book> findAll();
}
