package com.training.java.grandmassfood.delivery.api.dao.customers.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerListResponse {
    String orderBy;
    String direction;
    List<CustomerList> customerList;
}
