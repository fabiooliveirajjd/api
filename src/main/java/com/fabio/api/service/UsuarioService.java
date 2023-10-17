package com.fabio.api.service;

import com.fabio.api.entity.Usuario;
import com.fabio.api.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Transactional
    public Usuario editarSenha(Long id, String senhaAtual, String novaSenha, String confirmaSenha) {
         if (!novaSenha.equals(confirmaSenha)){
             throw  new RuntimeException("Nova seha não confere com confirmação  de senha.");
         }
        Usuario user = buscarPorId(id);
         if (!user.getPassword().equals(senhaAtual)){
             throw  new RuntimeException("Sua senha não confere.");
         }
        user.setPassword(novaSenha);
        return user;
    }

    @Transactional(readOnly = true)
    public List<Usuario> buscarTodos() {
        return usuarioRepository.findAll();
    }

    public void deletar (Long id){
        usuarioRepository.deleteById(id);
    }


}
