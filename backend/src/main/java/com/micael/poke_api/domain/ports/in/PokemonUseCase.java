package com.micael.poke_api.domain.ports.in;

import com.micael.poke_api.domain.model.Pokemon;

public interface PokemonUseCase {
    Pokemon getPokemonByName(String name);
}
