package com.examen.inventario;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.examen.inventario.dto.ErrorDTO;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ManejadorError {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> manejarValidaciones(MethodArgumentNotValidException ex, HttpServletRequest request) {
        
        Map<String, String> erroresCampos = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            erroresCampos.put(error.getField(), error.getDefaultMessage());
        }

        ErrorDTO errorDTO = new ErrorDTO(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Validacion Fallida en los campos",
                erroresCampos,
                request.getRequestURI()
        );

        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> manejarErroresGenericos(Exception ex, HttpServletRequest request) {
        
        Map<String, String> errorGenericoMap = new HashMap<>();
        errorGenericoMap.put("error", ex.getMessage());

        ErrorDTO errorDTO = new ErrorDTO(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Error Interno del Servidor",
                errorGenericoMap,
                request.getRequestURI()
        );

        return new ResponseEntity<>(errorDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}