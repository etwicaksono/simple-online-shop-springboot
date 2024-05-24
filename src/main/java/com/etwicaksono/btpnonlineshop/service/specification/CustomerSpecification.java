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
      boolean isActive = request.getIsActive() != null &&
            !request.getIsActive().equals("") &&
            Integer.parseInt(request.getIsActive()) != 0;

      if (customerName != null && !customerName.isEmpty()) {
         Predicate customerNameFilter = criteriaBuilder.equal(root.get("customerName"), customerName);
         predicates.add(customerNameFilter);
      }

      if (customerAddress != null && !customerAddress.isEmpty()) {
         Predicate customerAddressFilter = criteriaBuilder.equal(root.get("customerAddress"), customerAddress);
         predicates.add(customerAddressFilter);
      }

      if (customerCode != null && !customerCode.isEmpty()) {
         Predicate customerCodeFilter = criteriaBuilder.equal(root.get("customerCode"), customerCode);
         predicates.add(customerCodeFilter);
      }

      if (customerPhone != null && !customerPhone.isEmpty()) {
         Predicate customerPhoneFilter = criteriaBuilder.equal(root.get("customerPhone"), customerPhone);
         predicates.add(customerPhoneFilter);
      }

      if (isActive) {
         Predicate isActiveFilter = criteriaBuilder.equal(root.get("isActive"), isActive);
         predicates.add(isActiveFilter);
      }

      return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
   }

}