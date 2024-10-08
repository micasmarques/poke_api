package com.micael.poke_api.domain.ports.out;

import com.micael.poke_api.domain.model.Pokemon;

public interface PokemonRepository {
    Pokemon fetchPokemonByName(String name);
}