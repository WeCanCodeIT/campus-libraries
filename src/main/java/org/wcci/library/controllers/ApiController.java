package org.wcci.library.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;


public interface ApiController<T> {
    @RequestMapping("/")
    Collection<T> retrieveAll();

    @RequestMapping("/{id}")
    T retrieveById(@PathVariable Long id);
}
