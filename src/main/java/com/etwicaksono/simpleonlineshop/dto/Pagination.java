package com.etwicaksono.simpleonlineshop.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Pagination<T> {
   private T data;
   private int totalPage;
   private long totalItems;
}
