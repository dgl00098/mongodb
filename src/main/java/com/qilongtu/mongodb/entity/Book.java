package com.qilongtu.mongodb.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @ClassName Book
 * @Description TODO
 * @Author DGL
 * @Date 2020/10/18  15:20
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName="qilongtu",type="book")
public class Book {
    private Integer id;
    private String bookName;
    private String version;
    private String author;
}
