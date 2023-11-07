package com.fabio.api.service;

import com.fabio.api.entity.Cliente;
import com.fabio.api.exception.CpfUniqueViolationException;
import com.fabio.api.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository repository;

    @Transactional
    public Cliente salvar(Cliente cliente) {
        try {
            return repository.save(cliente);
        } catch (DataIntegrityViolationException e) {
            throw new CpfUniqueViolationException(String.format("CPF %s já cadastrado", cliente.getCpf()));
        }
    }
}
