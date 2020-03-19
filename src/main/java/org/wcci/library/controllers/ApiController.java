package org.wcci.library.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collection;


public interface ApiController<T> {
    @GetMapping("/")
    Collection<T> retrieveAll();

    @GetMapping("/{id}")
    T retrieveById(@PathVariable Long id);

    @PostMapping("/")
    T add(T element);

    @DeleteMapping("/{id}")
    Collection<T> remove(@PathVariable Long id);
}
