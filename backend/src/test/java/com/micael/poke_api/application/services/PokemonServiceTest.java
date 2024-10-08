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
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class PokemonServiceTest {

    @InjectMocks
    private PokemonService pokemonService;

    @Mock
    private PokemonRepository pokemonRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetPokemonByName() {
        String pokemonName = "pikachu";

        Ability ability1 = new Ability("static", false);
        Ability ability2 = new Ability("lightning-rod", true);
        List<Ability> abilities = Arrays.asList(ability1, ability2);

        Pokemon mockPokemon = new Pokemon(pokemonName, abilities);
        when(pokemonRepository.fetchPokemonByName(pokemonName)).thenReturn(mockPokemon);

        Pokemon response = pokemonService.getPokemonByName(pokemonName);

        assertEquals(pokemonName, response.getName());
        assertEquals(2, response.getAbilities().size());

        Set<String> abilityNames = response.getAbilities().stream()
                .map(Ability::getName)
                .collect(Collectors.toSet());
        assertTrue(abilityNames.contains("static"));
        assertTrue(abilityNames.contains("lightning-rod"));

        // Verificar detalhes de cada habilidade independentemente da ordem
        Ability resultAbility1 = response.getAbilities().stream()
                .filter(a -> a.getName().equals("static"))
                .findFirst().orElse(null);
        assertNotNull(resultAbility1);
        assertFalse(resultAbility1.isHidden());

        Ability resultAbility2 = response.getAbilities().stream()
                .filter(a -> a.getName().equals("lightning-rod"))
                .findFirst().orElse(null);
        assertNotNull(resultAbility2);
        assertTrue(resultAbility2.isHidden());
    }
}
