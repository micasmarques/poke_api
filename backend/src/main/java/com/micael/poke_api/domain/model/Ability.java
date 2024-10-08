package com.micael.poke_api.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Ability {
    private String name;
    private boolean isHidden;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ability ability = (Ability) o;
        return isHidden == ability.isHidden && Objects.equals(name, ability.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, isHidden);
    }
}
