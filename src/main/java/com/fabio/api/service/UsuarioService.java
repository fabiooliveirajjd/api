package com.fabio.api.service;

import com.fabio.api.entity.Usuario;
import com.fabio.api.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;


    @Transactional
    public Usuario salvar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Transactional(readOnly = true)//INDICA PARA O SPRING QUE ESSE MÉTODO É EXCLUSIVO PARA UMA CONSULTA NO BANCO DE DADOS(AJUDA NA PERFORMACE)
    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id).orElseThrow(
                () -> new RuntimeException("usuário não encontrado.")
        );
    }

    @Transactional(readOnly = true)
    public Usuario editarSenha(Long id, String password) {
        Usuario usuario = buscarPorId(id);
        usuario.setPassword(password);
        return usuario;
    }

    public void deletar (Long id){
        usuarioRepository.deleteById(id);
    }
}
