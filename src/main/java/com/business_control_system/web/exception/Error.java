package com.business_control_system.web.exception;

public record Error(
        String type,
        String message
) {
}
