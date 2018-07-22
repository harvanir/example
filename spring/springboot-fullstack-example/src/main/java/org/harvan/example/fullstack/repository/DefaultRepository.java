package org.harvan.example.fullstack.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author Harvan Irsyadi
 * @version 1.0.0
 * @since 1.0.0 (23 Jul 2018)
 */
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public interface DefaultRepository<T, ID> extends CrudRepository<T, ID> {
    @Override
    Optional<T> findById(ID aLong);

    @Override
    Iterable<T> findAll();
}