package br.com.solidarityconnect.exceptions;

public record RestError(int cod, String message) {
}