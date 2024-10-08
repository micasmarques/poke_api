package com.micael.poke_api.application.services;

import com.micael.poke_api.domain.model.Ability;
import com.micael.poke_api.domain.model.Pokemon;
import com.micael.poke_api.domain.ports.out.PokemonRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class PokemonServiceTest {

    @InjectMocks
    private PokemonService pokemonService;

    @Mock
    private PokemonRepository pokemonRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetPokemonByName() {
        String pokemonName = "pikachu";

        Ability ability1 = new Ability("static", false);
        Ability ability2 = new Ability("lightning-rod", true);
        List<Ability> abilities = Arrays.asList(ability1, ability2);

        Pokemon mockPokemon = new Pokemon(pokemonName, abilities);
        when(pokemonRepository.fetchPokemonByName(pokemonName)).thenReturn(mockPokemon);

        Pokemon response = pokemonService.getPokemonByName(pokemonName);

        assertEquals(pokemonName, response.getName());

        assertEquals(2, response.getAbilities().size());

        Ability resultAbility1 = response.getAbilities().getFirst();
        assertEquals("static", resultAbility1.getName());
        assertFalse(resultAbility1.isHidden());

        Ability resultAbility2 = response.getAbilities().get(1);
        assertEquals("lightning-rod", resultAbility2.getName());
        assertTrue(resultAbility2.isHidden());
    }
}
