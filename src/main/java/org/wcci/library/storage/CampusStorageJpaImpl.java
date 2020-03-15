package org.wcci.library.storage;

import org.springframework.stereotype.Service;
import org.wcci.library.model.Campus;
import org.wcci.library.storage.repositories.CampusRepository;

import java.util.Collection;

@Service
public class CampusStorageJpaImpl implements CampusStorage {
    private final CampusRepository campusRepository;

    public CampusStorageJpaImpl(CampusRepository campusRepository) {
        this.campusRepository = campusRepository;
    }

    @Override
    public void store(Campus item) {
        campusRepository.save(item);
    }

    @Override
    public Collection<Campus> fetchAll() {
        return (Collection<Campus>) campusRepository.findAll();
    }

    @Override
    public Campus fetchById(long id) {
        return campusRepository.findById(id).get();
    }
}
