package com.github.marceloedudev.infra.database.base;

import com.github.marceloedudev.models.panache.OrderItemModel;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class OrderItemPanache implements PanacheRepository<OrderItemModel> {
}
