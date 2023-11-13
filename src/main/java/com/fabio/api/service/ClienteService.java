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

    private final ClienteRepository clienteRepository;

    @Transactional
    public Cliente salvar(Cliente cliente) {
        try {
            return clienteRepository.save(cliente);
        } catch (DataIntegrityViolationException ex) { //verificar
            throw new CpfUniqueViolationException(
                    String.format("CPF '%s' não pode ser cadastrado, já existe no sistema", cliente.getCpf())
            );
        }
    }
}
