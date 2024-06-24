package com.etwicaksono.simpleonlineshop.service.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;

import com.etwicaksono.simpleonlineshop.dto.customer.GetListCustomerRequest;
import com.etwicaksono.simpleonlineshop.entity.CustomerEntity;

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
      Boolean isActive = request.getIsActive();

      if (customerName != null && !customerName.isEmpty()) {
         Predicate customerNameFilter = criteriaBuilder.like(criteriaBuilder.upper(root.get("customerName")),
               String.format("%%%s%%", customerName.toUpperCase()));
         predicates.add(customerNameFilter);
      }

      if (customerAddress != null && !customerAddress.isEmpty()) {
         Predicate customerAddressFilter = criteriaBuilder.like(criteriaBuilder.upper(root.get("customerAddress")),
               String.format("%%%s%%", customerAddress.toUpperCase()));
         predicates.add(customerAddressFilter);
      }

      if (customerCode != null && !customerCode.isEmpty()) {
         Predicate customerCodeFilter = criteriaBuilder.like(criteriaBuilder.upper(root.get("customerCode")),
               String.format("%%%s%%", customerCode.toUpperCase()));
         predicates.add(customerCodeFilter);
      }

      if (customerPhone != null && !customerPhone.isEmpty()) {
         Predicate customerPhoneFilter = criteriaBuilder.like(criteriaBuilder.upper(root.get("customerPhone")),
               String.format("%%%s%%", customerPhone.toUpperCase()));
         predicates.add(customerPhoneFilter);
      }

      if (isActive != null) {
         Predicate isActiveFilter = criteriaBuilder.equal(root.get("isActive"), isActive);
         predicates.add(isActiveFilter);
      }

      return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
   }

}