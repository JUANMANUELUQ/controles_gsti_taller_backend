package com.controles.taller.dto;

/**
 * DTO usado para enviar mensajes en las solicitudes HTTP
 * @param error
 * @param reply
 * @param <T>
 */
public record MessageDTO<T>(
        boolean error,
        T reply
) {
}
