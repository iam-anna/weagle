package com.weagle.dto.auth;

public record LoginResponse(
        String token,
        String id,
        String name,
        String email,
        String role
) { }
