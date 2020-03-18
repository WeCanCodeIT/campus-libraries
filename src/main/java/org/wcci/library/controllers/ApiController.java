package org.wcci.library.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.wcci.library.model.Campus;

import java.util.Collection;


public interface ApiController<T> {
    @GetMapping("/")
    Collection<T> retrieveAll();

    @GetMapping("/{id}")
    T retrieveById(@PathVariable Long id);
    @PostMapping("/")
    T add(T element);
}
