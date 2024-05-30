package com.etwicaksono.btpnonlineshop.entity;

import java.time.LocalDate;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "customers")
@EntityListeners(AuditingEntityListener.class)
public class CustomerEntity {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "customer_id", nullable = false)
   private Integer customerID;

   @Column(name = "customer_name", length = 64)
   private String customerName;

   @Column(name = "customer_address", length = 255)
   private String customerAddress;

   @Column(name = "customer_code", length = 32)
   private String customerCode;

   @Column(name = "customer_phone", length = 32)
   private String customerPhone;

   @Column(name = "is_active")
   private Boolean isActive;

   @Column(name = "last_order_date")
   private LocalDate lastOrderDate;

   @Column(name = "pic", length = 255)
   private String pic;
}