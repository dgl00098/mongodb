package com.qilongtu.mongodb.controller;

import com.qilongtu.mongodb.dao.BookRepository;
import com.qilongtu.mongodb.entity.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

/**
 * @ClassName BookController
 * @Description TODO
 * @Author DGL
 * @Date 2020/10/18  15:32
 **/
@RestController
@RequestMapping("/index")
@Slf4j
public class BookController {

    @Autowired
    BookRepository bookRepository;

    /**
     * 删除图书
     */
    @GetMapping("/delete/{id}")
    public void delete(@PathVariable Integer id) {
        bookRepository.deleteById(id);
        log.info("==============操作成功=============");
    }

    /**
     * 分页查询图书
     */
    @GetMapping("/findByPage/{page}/{size}")
    public String findByPage(@PathVariable int page,@PathVariable int size) {
        Sort.Direction tmp= Sort.Direction.DESC;
        String sortStr="id";
        //参数1:排序方式  参数2:排序字段
        Sort sort = Sort.by(tmp, sortStr);
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Book> bookPage = bookRepository.findAll(pageable);
        log.info("==============操作成功=============");
        log.info("数据是: " + bookPage.getContent());
        return bookPage.getContent().toString();
    }

    /**
     * 批量新增图书
     */
    @GetMapping("/batchInsert/{count}")
    public String batchInsert(@PathVariable Integer count) {
        ArrayList<Book> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Book build = Book.builder().author("王志远"+i).bookName("往上爬还是地上走").version("1.0." + i).id(i).build();
            list.add(build);
        }
        Iterable<Book> all = bookRepository.saveAll(list);
        log.info("==============操作成功=============");
        log.info("==============成功插入"+list.size()+" 条=============");
        log.info("数据是: " + all);
        return all.toString();
    }

    /**
     * 新增图书
     */
    @GetMapping("/add/{id}")
    public String addBook(@PathVariable Integer id) {
        Book build = Book.builder().author("王志远").bookName("往上爬还是地上走").version("0.0." + id).id(id).build();
        Book save = bookRepository.save(build);
        log.info("==============操作成功=============");
        log.info("数据是: " + save);
        return save.toString();
    }

    /**
     * 查询图书的数量
     */
    @GetMapping("/count")
    public String count() {
        long count = bookRepository.count();
        log.info("==============操作成功=============");
        log.info("数据是: " + count);
        return String.valueOf(count);
    }

    /**
     * 更新图书
     */
    @GetMapping("/updateBookById")
    public String updateBookById(@RequestParam Integer id) {
        Book build = Book.builder().author("杨东升").bookName("谢谢你的爱").version("0.0." + id).id(id).build();
        Book save = bookRepository.save(build);
        log.info("==============操作成功=============");
        log.info("数据是: " + save);
        return String.valueOf(save);
    }

    /**
     * 根据图书id查询图书
     */
    @GetMapping("/findById")
    public String findByBookNameLike(@RequestParam Integer id) {
        Optional<Book> optional = bookRepository.findById(id);
        log.info("==============操作成功=============");
        log.info("数据是: " + optional);
        return String.valueOf(optional);
    }

    /**
     * 根据图书名字模糊查询图书
     */
    @GetMapping("/findByBookNameLike")
    public String findByBookNameLike(@RequestParam String name) {
        Iterable<Book> all = bookRepository.findByBookNameLike(name);
        log.info("==============操作成功=============");
        log.info("数据是: " + all);
        return all.toString();
    }

    /**
     * 根据图书名字查询图书
     */
    @GetMapping("/findByBookName")
    public String findByBookName(@RequestParam String name) {
        Iterable<Book> all = bookRepository.findByBookName(name);
        log.info("==============操作成功=============");
        log.info("数据是: " + all);
        return all.toString();
    }

    /**
     * 查询图书
     */
    @GetMapping("/findAll")
    public String findBook() {
        Iterable<Book> all = bookRepository.findAll();
        log.info("==============操作成功=============");
        log.info("数据是: " + all);
        return all.toString();
    }
}
