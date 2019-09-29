package com.github.airatgaliev.bookstore.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.springframework.web.multipart.MultipartFile;

@Entity
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String title;
  private String author;
  private String publisher;
  private String publicationDate;
  private String language;
  private String category;
  private int numberOfPages;
  private String format;
  private int isbn;
  private double shoppingWeight;
  private double listPrice;
  private double ourPrice;
  private boolean active = true;

  @Column(columnDefinition = "text")
  private String description;
  private int inStockNumber;

  private MultipartFile bookImage;
}
