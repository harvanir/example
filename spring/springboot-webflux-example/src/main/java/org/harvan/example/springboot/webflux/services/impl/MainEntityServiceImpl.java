package org.harvan.example.springboot.webflux.services.impl;

import org.harvan.example.springboot.webflux.entity.MainEntity;
import org.harvan.example.springboot.webflux.repository.MainEntityRepository;
import org.harvan.example.springboot.webflux.services.MainEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MainEntityServiceImpl implements MainEntityService {
    private MainEntityRepository mainEntityRepository;

    @Autowired
    public void setMainEntityRepository(MainEntityRepository mainEntityRepository) {
        this.mainEntityRepository = mainEntityRepository;
    }

    @Override
    public Mono<MainEntity> findById(Integer id) {
        return mainEntityRepository.findById(id);
    }

    @Override
    public Flux<MainEntity> findAll() {
        return mainEntityRepository.findAll();
    }
}