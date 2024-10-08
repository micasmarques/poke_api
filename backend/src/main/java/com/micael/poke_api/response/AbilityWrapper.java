package com.micael.poke_api.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AbilityWrapper {
    private AbilityDetail ability;
    private boolean is_hidden;
    private int slot;
}