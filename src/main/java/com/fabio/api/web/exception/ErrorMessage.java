/**
 * Representação de mensagem de erro para resposta em caso de exceções.
 *
 * Esta classe é usada para representar uma mensagem de erro em resposta a exceções em um formato estruturado.
 * Ela contém informações sobre o caminho da solicitação, o método HTTP, o status HTTP, uma mensagem de erro
 * e, opcionalmente, um mapa de erros detalhados relacionados a campos inválidos.
 */
package com.fabio.api.web.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@Getter
@ToString
public class ErrorMessage {

    private String path; // O caminho da solicitação que causou a exceção.
    private String method; // O método HTTP da solicitação.
    private int status; // O código de status HTTP.
    private String statusText; // O motivo do status HTTP.
    private String message; // A mensagem de erro geral.
    @JsonInclude(JsonInclude.Include.NON_NULL) //não mostra campos nullos
    private Map<String, String> errors; // Um mapa de erros detalhados relacionados a campos inválidos.

    public ErrorMessage() {
    }

    /**
     * Construtor para criar uma mensagem de erro básica sem detalhes de validação.
     *
     * @param request  O objeto HttpServletRequest da solicitação.
     * @param status   O status HTTP da exceção.
     * @param message  A mensagem de erro geral.
     */
    public ErrorMessage(HttpServletRequest request, HttpStatus status, String message) {
        this.path = request.getRequestURI();
        this.method = request.getMethod();
        this.status = status.value();
        this.statusText = status.getReasonPhrase();
        this.message = message;
    }

    /**
     * Construtor para criar uma mensagem de erro com detalhes de validação.
     *
     * @param request  O objeto HttpServletRequest da solicitação.
     * @param status   O status HTTP da exceção.
     * @param message  A mensagem de erro geral.
     * @param result   O objeto BindingResult contendo detalhes de validação.
     */
    public ErrorMessage(HttpServletRequest request, HttpStatus status, String message, BindingResult result) {
        this.path = request.getRequestURI();
        this.method = request.getMethod();
        this.status = status.value();
        this.statusText = status.getReasonPhrase();
        this.message = message;
        addErrors(result);
    }

    private void addErrors(BindingResult result) {
        this.errors = new HashMap<>();
        for (FieldError fieldError : result.getFieldErrors()) {
            this.errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
    }
}
