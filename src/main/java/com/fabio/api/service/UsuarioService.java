/**
 * Serviço para operações relacionadas a usuários.
 *
 * Este serviço gerencia as operações de criação, leitura, atualização e exclusão de entidades de usuário.
 */
package com.fabio.api.service;

import com.fabio.api.entity.Usuario;
import com.fabio.api.exception.EntityNotFoundExeption;
import com.fabio.api.exception.PasswordInvalidException;
import com.fabio.api.exception.UsernameUniqueViolationException;
import com.fabio.api.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    /**
     * Salva um novo usuário no banco de dados.
     *
     * @param usuario O usuário a ser salvo.
     * @return O usuário salvo no banco de dados.
     */
    @Transactional
    public Usuario salvar(Usuario usuario) {
        try {
            return usuarioRepository.save(usuario);
        }catch (org.springframework.dao.DataIntegrityViolationException ex){
            throw  new UsernameUniqueViolationException(String.format("Username '$s' já cadastrado", usuario.getUsername()));
        }
    }

    /**
     * Busca um usuário por seu ID.
     *
     * @param id O ID do usuário a ser buscado.
     * @return O usuário encontrado com o ID especificado.
     * @throws RuntimeException se o usuário não for encontrado.
     */
    @Transactional(readOnly = true)
    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundExeption(String.format("Usuário não encontrado.", id))
        );
    }

    /**
     * Edita a senha de um usuário com base no seu ID, senha atual e nova senha.
     *
     * @param id            O ID do usuário cuja senha será atualizada.
     * @param senhaAtual    A senha atual do usuário.
     * @param novaSenha     A nova senha desejada.
     * @param confirmaSenha A confirmação da nova senha.
     * @return O usuário com a senha atualizada.
     * @throws RuntimeException se a nova senha não corresponder à confirmação ou se a senha atual estiver incorreta.
     */
    @Transactional
    public Usuario editarSenha(Long id, String senhaAtual, String novaSenha, String confirmaSenha) {
        if (!novaSenha.equals(confirmaSenha)) {
            throw new PasswordInvalidException("Nova senha não confere com confirmação de senha.");
        }

        Usuario user = buscarPorId(id);
        if (!user.getPassword().equals(senhaAtual)) {
            throw new PasswordInvalidException("Sua senha não confere.");
        }

        user.setPassword(novaSenha);
        return user;
    }

    /**
     * Recupera todos os usuários cadastrados.
     *
     * @return Uma lista de todos os usuários no banco de dados.
     */
    @Transactional(readOnly = true)
    public List<Usuario> buscarTodos() {
        return usuarioRepository.findAll();
    }

    /**
     * Exclui um usuário com base no seu ID.
     *
     * @param id O ID do usuário a ser excluído.
     */
    public void deletar(Long id) {
        usuarioRepository.deleteById(id);
    }
}
