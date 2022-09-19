package com.github.marceloedudev.domain.dao;

import com.github.marceloedudev.domain.entity.Pagination;
import com.github.marceloedudev.domain.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderDAO {
    Optional<Order> findById(Long id);

    List<Order> findAll(Long userId, Pagination pagination);

    Optional<Order> findByIdAndUser(Long orderId, Long userId);
}
