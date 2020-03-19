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
    public Campus store(Campus item) {
        return campusRepository.save(item);
    }

    @Override
    public Collection<Campus> fetchAll() {
        return (Collection<Campus>) campusRepository.findAll();
    }

    @Override
    public Campus fetchById(long id) {
        return campusRepository.findById(id).get();
    }

    @Override
    public Collection<Campus> delete(Long id) {
        return null;
    }
}
