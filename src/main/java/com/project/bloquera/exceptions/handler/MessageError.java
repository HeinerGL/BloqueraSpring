package com.project.bloquera.exceptions.handler;

import java.time.LocalDateTime;

public record MessageError(
    Object error, Integer statusCode, LocalDateTime timestamp
) {
}
