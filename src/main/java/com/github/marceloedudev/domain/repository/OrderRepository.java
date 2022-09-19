package com.github.marceloedudev.domain.repository;

import com.github.marceloedudev.domain.entity.Order;

public interface OrderRepository {
    void create(Order order);
    void updateStatus(int status);
}
