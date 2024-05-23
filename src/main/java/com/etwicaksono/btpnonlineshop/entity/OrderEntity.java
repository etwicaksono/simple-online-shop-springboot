package com.etwicaksono.btpnonlineshop.entity;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "orders")
@EntityListeners(AuditingEntityListener.class)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderEntity {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "order_id")
   private Integer orderId;

   @Column(name = "order_code")
   private String orderCode;

   @Column(name = "order_date")
   private LocalDate orderDate;

   @Column(name = "total_price")
   private Integer totalPrice;

   @Column(name = "quantity")
   private Integer quantity;

   @ManyToOne
   @JoinColumn(name = "customer_id")
   private CustomerEntity customer;

   @OneToMany
   @JoinColumn(name = "items_id")
   private List<ItemEntity> items;
}