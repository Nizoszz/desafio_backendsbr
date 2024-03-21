package com.desafio.backendsbr.services;

import com.desafio.backendsbr.domain.Operation;
import com.desafio.backendsbr.domain.exceptions.OperationNotFoundException;
import com.desafio.backendsbr.dtos.OperationRequestDTO;
import com.desafio.backendsbr.dtos.OperationResponseDTO;
import java.util.List;

public interface OperationService {
    Operation insert(OperationRequestDTO operationRequestDTO);
    List<OperationResponseDTO> read();

    OperationResponseDTO readById(Long id) throws OperationNotFoundException;
    Operation update(Long id, OperationRequestDTO operationDTO);
    void delete(Long id);
}
