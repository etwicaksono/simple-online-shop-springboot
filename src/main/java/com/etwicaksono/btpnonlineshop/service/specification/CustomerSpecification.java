package com.etwicaksono.btpnonlineshop.service.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;

import com.etwicaksono.btpnonlineshop.dto.customer.GetListCustomerRequest;
import com.etwicaksono.btpnonlineshop.entity.CustomerEntity;

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
public class CustomerSpecification implements Specification<CustomerEntity> {
   private GetListCustomerRequest request;

   @Override
   @Nullable
   public Predicate toPredicate(Root<CustomerEntity> root, CriteriaQuery<?> criteriaQuery,
         CriteriaBuilder criteriaBuilder) {
      List<Predicate> predicates = new ArrayList<>();

      String customerName = request.getCustomerName();
      String customerAddress = request.getCustomerAddress();
      String customerCode = request.getCustomerCode();
      String customerPhone = request.getCustomerPhone();
      boolean isActive = request.getIsActive();

      if (customerName != null && !customerName.isEmpty()) {
         Predicate customerNameFilter = criteriaBuilder.like(root.get("customerName"),
               String.format("%%%s%%", customerName));
         predicates.add(customerNameFilter);
      }

      if (customerAddress != null && !customerAddress.isEmpty()) {
         Predicate customerAddressFilter = criteriaBuilder.like(root.get("customerAddress"),
               String.format("%%%s%%", customerAddress));
         predicates.add(customerAddressFilter);
      }

      if (customerCode != null && !customerCode.isEmpty()) {
         Predicate customerCodeFilter = criteriaBuilder.like(root.get("customerCode"),
               String.format("%%%s%%", customerCode));
         predicates.add(customerCodeFilter);
      }

      if (customerPhone != null && !customerPhone.isEmpty()) {
         Predicate customerPhoneFilter = criteriaBuilder.like(root.get("customerPhone"),
               String.format("%%%s%%", customerPhone));
         predicates.add(customerPhoneFilter);
      }

      if (isActive) {
         Predicate isActiveFilter = criteriaBuilder.equal(root.get("isActive"), isActive);
         predicates.add(isActiveFilter);
      }

      return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
   }

}