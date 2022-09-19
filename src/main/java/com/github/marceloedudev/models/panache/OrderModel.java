package com.github.marceloedudev.models.panache;

import com.github.marceloedudev.pkg.crypto.UuidAdapter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "order_tbl")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@Setter
public class OrderModel {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "status")
    private Integer status;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "user_id")
    private Long user;

    @Column(name="uuid", updatable = false, nullable = false, unique = true)
    private String uuid;

    @Column(name = "created_at")
    private LocalDateTime createAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "orderId", fetch = FetchType.LAZY)
    private List<OrderItemModel> orderItems;

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

    @Override
    public String toString() {
        return "OrderModel{" +
                "id=" + id +
                ", status=" + status +
                ", cpf='" + cpf + '\'' +
                ", user=" + user +
                ", uuid='" + uuid + '\'' +
                ", createAt=" + createAt +
                ", updatedAt=" + updatedAt +
                ", orderItems=" + orderItems +
                '}';
    }
}
