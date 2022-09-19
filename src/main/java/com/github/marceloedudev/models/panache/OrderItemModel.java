package com.github.marceloedudev.models.panache;

import com.github.marceloedudev.pkg.crypto.UuidAdapter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "order_item")
@Getter
@Setter
public class OrderItemModel {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "order_id", insertable = false, updatable = false)
    @ManyToOne(targetEntity = OrderModel.class, fetch = FetchType.EAGER)
    private OrderModel order;

    @Column(name = "order_id")
    private Long orderId;

    @JoinColumn(name = "item_id", insertable = false, updatable = false)
    @ManyToOne(targetEntity = ItemModel.class, fetch = FetchType.EAGER)
    private ItemModel item;

    @Column(name = "item_id")
    private Long itemId;

    @Column(name = "price")
    private Double price;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name="uuid", updatable = false, nullable = false, unique = true)
    private String uuid;

    @Column(name = "created_at")
    private LocalDateTime createAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        setCreateAt(LocalDateTime.now());
        setUpdatedAt(LocalDateTime.now());
        setUuid(new UuidAdapter().randomUUID());
    }

    @PreUpdate
    public void preUpdate() {
        setUpdatedAt(LocalDateTime.now());
    }
}
