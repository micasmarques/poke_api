package com.micael.poke_api.infrastructure.adapters.out;

import com.micael.poke_api.domain.model.Ability;
import com.micael.poke_api.domain.model.Pokemon;
import com.micael.poke_api.domain.ports.out.PokemonRepository;
import com.micael.poke_api.response.PokemonApiResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PokeApiClient implements PokemonRepository {
    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public Pokemon fetchPokemonByName(String name) {
        String pokeApiUrl = "https://pokeapi.co/api/v2/pokemon";
        var uri = UriComponentsBuilder.fromHttpUrl(pokeApiUrl)
                .pathSegment(name)
                .toUriString();

        try {
            var response = restTemplate.getForObject(uri, PokemonApiResponse.class);
            assert response != null;

            List<Ability> abilities = response.getAbilities().stream()
                    .map(abilityWrapper -> new Ability(
                            abilityWrapper.getAbility().getName(),
                            abilityWrapper.is_hidden()
                    ))
                    .collect(Collectors.toList());

            return new Pokemon(response.getName(), abilities);
        } catch (RestClientException e) {
            throw new RuntimeException("Erro ao buscar Pokémon: " + e.getMessage());
        }
    }
}
