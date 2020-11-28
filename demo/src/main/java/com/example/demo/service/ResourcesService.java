package com.example.demo.service;

import com.example.demo.domain.Resources;

import java.util.List;

public interface ResourcesService {
    Resources getResources(Long id);

    List<Resources> getResources();

    void createResources(Resources Resources);

    void deleteResources(Long id);
}
