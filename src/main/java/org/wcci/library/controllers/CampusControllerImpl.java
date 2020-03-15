package org.wcci.library.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.wcci.library.model.Campus;
import org.wcci.library.storage.CampusStorage;

import java.util.Collection;
@RequestMapping("/api/campuses")
public class CampusControllerImpl implements CampusController {
    private final CampusStorage campusStorage;

    public CampusControllerImpl(CampusStorage campusStorage) {
        this.campusStorage = campusStorage;
    }

    @Override
    public Collection<Campus> retrieveAll() {
        return campusStorage.fetchAll();
    }

    @Override
    public Campus retrieveById(Long id) {
        return campusStorage.fetchById(id);
    }
}
