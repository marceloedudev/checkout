package com.github.marceloedudev.infra.database.base;


import com.github.marceloedudev.models.panache.OrderModel;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class OrderPanache implements PanacheRepository<OrderModel> {

    public OrderModel findByIdAndUser(Long orderId, Long userId) {
        PanacheQuery<OrderModel> order = find("id = :order_id and user = :user_id",
                Parameters.with("order_id", orderId)
                        .and("user_id", userId)
        );
        return order.firstResult();
    }

}
