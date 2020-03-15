package org.wcci.library.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wcci.library.model.Book;

import java.util.Collection;

public interface BookController extends ApiController<Book>{
}