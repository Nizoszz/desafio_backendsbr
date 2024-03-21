package com.desafio.backendsbr.services.impl;

import com.desafio.backendsbr.domain.Operation;
import com.desafio.backendsbr.domain.exceptions.OperationNotFoundException;
import com.desafio.backendsbr.dtos.OperationRequestDTO;
import com.desafio.backendsbr.dtos.OperationResponseDTO;
import com.desafio.backendsbr.repositories.OperationRepository;
import com.desafio.backendsbr.services.EncryptService;
import com.desafio.backendsbr.services.OperationService;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OperationServiceImpl implements OperationService {
    private final OperationRepository repository;
    private final EncryptService encryptService;

    public OperationServiceImpl(OperationRepository repository, EncryptService encryptService) {
        this.repository = repository;
        this.encryptService = encryptService;
    }

    @Override
    public Operation insert(OperationRequestDTO operationRequestDTO) throws NullPointerException  {
        if (operationRequestDTO == null || operationRequestDTO.userDocument() == null || operationRequestDTO.creditCardToken() == null || operationRequestDTO.value() == null) {
            throw new NullPointerException("Não foi possível criar o objeto, verifique os dados enviados");
        }
        Operation operation = new Operation();
        String userDocumentHash = encryptService.encrypt(operationRequestDTO.userDocument());
        String creditCardTokenHash = encryptService.encrypt(operationRequestDTO.creditCardToken());
        operation.setUserDocument(userDocumentHash);
        operation.setCreditCardToken(creditCardTokenHash);
        operation.setValue(operationRequestDTO.value());
        return this.repository.save(operation);
    }

    @Override
    public List<OperationResponseDTO> read() {
        List<Operation> operationsList = this.repository.findAll();
        List<OperationResponseDTO> expectedResponse = new ArrayList<>();
        for (Operation operation : operationsList) {
            String userDocumentDecrypt = encryptService.decrypt(operation.getUserDocument());
            String creditCardTokenDecrypt = encryptService.decrypt(operation.getCreditCardToken());

            OperationResponseDTO operationResponseDTO = new OperationResponseDTO(operation.getId(), userDocumentDecrypt, creditCardTokenDecrypt, operation.getValue());

            expectedResponse.add(operationResponseDTO);
        }
        return expectedResponse;

    }

    @Override
    public OperationResponseDTO readById(Long id) throws OperationNotFoundException {
        Operation operation = this.repository.findById(id).orElseThrow(() -> new OperationNotFoundException("Operation not found"));

        String userDocumentDecrypt = encryptService.decrypt(operation.getUserDocument());
        String creditCardTokenDecrypt = encryptService.decrypt(operation.getCreditCardToken());

        OperationResponseDTO dto = new OperationResponseDTO(operation.getId(), userDocumentDecrypt, creditCardTokenDecrypt,operation.getValue());
        return dto;
    }

    @Override
    public Operation update(Long id, OperationRequestDTO operationDTO) {
        Operation operationFound = this.repository.findById(id).orElseThrow(() -> new OperationNotFoundException("Operation not found"));

        if(operationDTO.userDocument() != null && !operationDTO.userDocument().isEmpty()){
            operationFound.setUserDocument(encryptService.encrypt(operationDTO.userDocument()));
        }
        if(operationDTO.creditCardToken() != null && !operationDTO.creditCardToken().isEmpty()){
            operationFound.setCreditCardToken(encryptService.encrypt(operationDTO.creditCardToken()));
        }
        if(operationDTO.value() != null){
            operationFound.setValue(operationDTO.value());
        }
        return this.repository.save(operationFound);
    }

    @Override
    public void delete(Long id) {
        boolean operation = this.repository.existsById(id);
        if(!operation) {
            throw new OperationNotFoundException("Operation not found");
        }
        this.repository.deleteById(id);
    }
}
