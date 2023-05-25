package br.com.solidarityconnect.models;

public record Token(
    String token,
    String type,
    String prefix
) {}

