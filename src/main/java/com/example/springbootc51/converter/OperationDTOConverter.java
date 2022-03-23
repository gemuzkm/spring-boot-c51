package com.example.springbootc51.converter;

import com.example.springbootc51.dto.OperationDTO;
import com.example.springbootc51.entity.Operation;
import org.springframework.stereotype.Component;

@Component
public class OperationDTOConverter {
    public Operation operationDTOtoHistory(OperationDTO operationDTO) {
        Operation operation = new Operation();
        operation.setId(operationDTO.getId());
        operation.setValue1(operationDTO.getValue1());
        operation.setValue2(operationDTO.getValue2());
    return operation;
    }
}
