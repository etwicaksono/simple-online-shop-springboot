package com.etwicaksono.simpleonlineshop.entity;

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
@Entity
@Table(name = "items")
@EntityListeners(AuditingEntityListener.class)
@Builder
public class ItemEntity {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "items_id")
   private Integer itemsID;

   @Column(name = "items_name")
   private String itemsName;

   @Column(name = "items_code")
   private String itemsCode;

   @Column(name = "stock")
   private Integer stock;

   @Column(name = "price")
   private Integer price;

   @Column(name = "is_available")
   private Boolean isAvailable;

   @Column(name = "last_re_stock")
   private LocalDate lastRestock;

}