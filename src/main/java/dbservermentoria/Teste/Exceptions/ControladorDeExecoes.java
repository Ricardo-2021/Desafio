package dbservermentoria.Teste.Exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestControllerAdvice
public class ControladorDeExecoes extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IdNaoEncontradoNoBancoDeDadosException.class)
    public ResponseEntity<Object> handleAnyException(IdNaoEncontradoNoBancoDeDadosException e, WebRequest request) {
        String mensagem = "id não encontrado";
        return handleExceptionInternal(e, mensagem, HttpHeaders.EMPTY, HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String userMessage = "Mensagem Inválida";
        String devMessage = ex.getMostSpecificCause().toString();
        List<Error> errors = Collections.singletonList(new Error(userMessage, devMessage));
        return handleExceptionInternal(ex, errors, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<Error> errors = createErrorList(ex.getBindingResult());
        return handleExceptionInternal(ex, errors, headers, status, request);
    }

    private List<Error> createErrorList(BindingResult bindingResult) {
        List<Error> errors = new ArrayList<>();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            String userMessage = fieldError.getField();
            String devMessage = fieldError.toString();
            errors.add(new Error(userMessage, devMessage));
        }
        return errors;
    }

    public static class Error {
        private String userMessage;
        private String devMessage;

        public Error(String userMessage, String devMessage) {
            this.userMessage = userMessage;
            this.devMessage = devMessage;
        }

        public String getUserMessage() {
            return userMessage;
        }

        public void setUserMessage(String userMessage) {
            this.userMessage = userMessage;
        }

        public String getDevMessage() {
            return devMessage;
        }

        public void setDevMessage(String devMessage) {
            this.devMessage = devMessage;
        }
    }
}


