package com.github.marceloedudev.domain.dao;

import com.github.marceloedudev.domain.entity.Item;

import java.util.Optional;

public interface ItemDAO {
    Optional<Item> findById(Long id);
}
