package com.etwicaksono.simpleonlineshop.service.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;

import com.etwicaksono.simpleonlineshop.dto.order.GetListOrderRequest;
import com.etwicaksono.simpleonlineshop.entity.OrderEntity;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderSpecification implements Specification<OrderEntity> {
   private transient GetListOrderRequest request;

   @Override
   @Nullable
   public Predicate toPredicate(Root<OrderEntity> root, CriteriaQuery<?> criteriaQuery,
         CriteriaBuilder criteriaBuilder) {
      List<Predicate> predicates = new ArrayList<>();

      String orderCode = request.getOrderCode();

      if (orderCode != null && !orderCode.isEmpty()) {
         Predicate orderCodeFilter = criteriaBuilder.like(criteriaBuilder.upper(root.get("orderCode")),
               String.format("%%%s%%", orderCode.toUpperCase()));
         predicates.add(orderCodeFilter);
      }

      return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
   }

}