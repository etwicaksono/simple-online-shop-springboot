package com.etwicaksono.btpnonlineshop.service.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;

import com.etwicaksono.btpnonlineshop.dto.item.GetListItemRequest;
import com.etwicaksono.btpnonlineshop.entity.ItemEntity;

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
public class ItemSpecification implements Specification<ItemEntity> {
   private GetListItemRequest request;

   @Override
   @Nullable
   public Predicate toPredicate(Root<ItemEntity> root, CriteriaQuery<?> criteriaQuery,
         CriteriaBuilder criteriaBuilder) {
      List<Predicate> predicates = new ArrayList<>();

      String itemsName = request.getItemName();
      String itemsCode = request.getItemCode();
      boolean isAvailable = request.getIsAvailable() != null &&
            !request.getIsAvailable().equals("") &&
            Integer.parseInt(request.getIsAvailable()) != 0;

      if (itemsName != null && !itemsName.isEmpty()) {
         Predicate itemsNameFilter = criteriaBuilder.like(root.get("itemsName"),
               String.format("%%%s%%", itemsName));
         predicates.add(itemsNameFilter);
      }

      if (itemsCode != null && !itemsCode.isEmpty()) {
         Predicate itemsCodeFilter = criteriaBuilder.like(root.get("itemsCode"),
               String.format("%%%s%%", itemsCode));
         predicates.add(itemsCodeFilter);
      }

      if (isAvailable) {
         Predicate isAvailableFilter = criteriaBuilder.equal(root.get("isAvailable"), isAvailable);
         predicates.add(isAvailableFilter);
      }

      return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
   }

}