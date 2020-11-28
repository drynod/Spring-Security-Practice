package com.example.demo.service.impl;

import com.example.demo.domain.Resources;
import com.example.demo.repository.ResourcesRepository;
import com.example.demo.service.ResourcesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ResourcesServiceImpl implements ResourcesService {

    private final ResourcesRepository resourcesRepository;

    @Override
    @Transactional
    public Resources getResources(Long id) {
        return resourcesRepository.findById(id).orElse(new Resources());
    }

    @Override
    @Transactional
    public List<Resources> getResources() {
        return resourcesRepository.findAllResources();
    }

    @Override
    @Transactional
    public void createResources(Resources Resources) {
        resourcesRepository.save(Resources);
    }

    @Override
    @Transactional
    public void deleteResources(Long id) {
        resourcesRepository.deleteById(id);
    }
}
