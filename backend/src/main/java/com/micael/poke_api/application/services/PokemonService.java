package com.micael.poke_api.application.services;

import com.micael.poke_api.domain.model.Ability;
import com.micael.poke_api.domain.model.Pokemon;
import com.micael.poke_api.domain.ports.in.PokemonUseCase;
import com.micael.poke_api.domain.ports.out.PokemonRepository;
import com.micael.poke_api.exceptions.PokemonNotFoundException;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.server.ResponseStatusException;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PokemonService implements PokemonUseCase {
    private final PokemonRepository pokemonRepository;

    @Override
    public Pokemon getPokemonByName(String name) {
        try {
            Pokemon pokemon = pokemonRepository.fetchPokemonByName(name);
            if (pokemon == null) {
                throw new PokemonNotFoundException("Pokémon não encontrado: " + name);
            }

            List<Ability> sortedAbilities = pokemon.getAbilities()
                    .stream()
                    .sorted(Comparator.comparing(Ability::getName))
                    .collect(Collectors.toList());

            return new Pokemon(pokemon.getName(), sortedAbilities);
        } catch (ResourceAccessException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao acessar a PokeAPI.");
        }
    }
}
