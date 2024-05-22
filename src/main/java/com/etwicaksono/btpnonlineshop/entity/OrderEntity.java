package com.etwicaksono.btpnonlineshop.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
   private String orderDate;

   @Column(name = "total_price")
   private Integer totalPrice;

   @Column(name = "customer_id")
   private Integer customerId;

   @Column(name = "items_id")
   private Integer itemsId;

   @Column(name = "quantity")
   private Integer quantity;

   @ManyToOne
   private CustomerEntity customer;

   @OneToMany
   private ItemEntity items;
}