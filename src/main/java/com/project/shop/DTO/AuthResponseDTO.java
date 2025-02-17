package com.project.shop.DTO;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"username", "role", "message", "jwt", "status"})
public record AuthResponseDTO(String username, String role, String message, String jwt, boolean status) {
}
