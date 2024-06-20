package com.project.bloquera.exceptions.handler;

import java.time.Instant;

public record ErrorMessage(String error, int statusCode, Instant date) {
}
