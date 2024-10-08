package com.micael.poke_api.infrastructure.adapters.in;

import com.micael.poke_api.domain.model.Pokemon;
import com.micael.poke_api.domain.ports.in.PokemonUseCase;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@Slf4j
public class RestPokemonController {
    private PokemonUseCase pokemonUseCase;

    @GetMapping("/pokemon")
    public Map<String, Object> getPokemonByName(@RequestParam String name) {
        Pokemon pokemon = pokemonUseCase.getPokemonByName(name);

        List<Map<String, Object>> abilities = pokemon.getAbilities().stream()
                .map(ability -> {
                    Map<String, Object> abilityMap = new HashMap<>();
                    abilityMap.put("name", ability.getName());
                    abilityMap.put("is_hidden", ability.isHidden());
                    return abilityMap;
                })
                .collect(Collectors.toList());

        log.info("passando pelo endpoint com pokemon com nome {}", name);

        Map<String, Object> response = new HashMap<>();
        response.put("name", pokemon.getName());
        response.put("abilities", abilities);

        return response;
    }
}
