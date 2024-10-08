package com.micael.poke_api.response;

import lombok.Getter;

import java.util.List;

@Getter
public class PokemonApiResponse {
    private String name;
    private List<AbilityWrapper> abilities;
}