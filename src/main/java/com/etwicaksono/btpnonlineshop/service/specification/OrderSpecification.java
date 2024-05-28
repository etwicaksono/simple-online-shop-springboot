package com.etwicaksono.btpnonlineshop.service.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;

import com.etwicaksono.btpnonlineshop.dto.item.GetListItemRequest;
import com.etwicaksono.btpnonlineshop.dto.order.GetListOrderRequest;
import com.etwicaksono.btpnonlineshop.entity.OrderEntity;

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
   private GetListOrderRequest request;

   @Override
   @Nullable
   public Predicate toPredicate(Root<OrderEntity> root, CriteriaQuery<?> criteriaQuery,
         CriteriaBuilder criteriaBuilder) {
      List<Predicate> predicates = new ArrayList<>();

      String orderCode = request.getOrderCode();

      if (orderCode != null && !orderCode.isEmpty()) {
         Predicate orderCodeFilter = criteriaBuilder.like(root.get("orderCode"),
               String.format("%%%s%%", orderCode));
         predicates.add(orderCodeFilter);
      }

      return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
   }

}