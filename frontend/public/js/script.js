document.getElementById('closeInstruction').addEventListener('click', function() {
    document.getElementById('instructionBox').style.display = 'none';
});

function searchPokemon() {
    const pokemonName = document.getElementById('pokemonName').value.toLowerCase();
    const resultDiv = document.getElementById('result');
    const errorDiv = document.getElementById('error');

    resultDiv.classList.add('hidden');
    errorDiv.classList.add('hidden');

    if (pokemonName === "") {
        errorDiv.textContent = "Por favor, insira o nome de um Pokémon.";
        errorDiv.classList.remove('hidden');
        return;
    }

    fetch(`http://localhost:8080/pokemon?name=${pokemonName}`)
    .then(response => {
        if (!response.ok) {
            throw new Error('Pokémon não encontrado.');
        }
        return response.json();
    })
    .then(data => {
        document.getElementById('pokemon').textContent = data.name;

        const abilitiesList = data.abilities.map(ability => {
            const abilityInfo = ability.is_hidden ? ' (Oculta)' : '';
            const hiddenClass = ability.is_hidden ? 'hidden-ability' : '';
            return `
                <li class="${hiddenClass}">
                    ${ability.name} ${abilityInfo} - Slot: ${ability.slot}
                </li>
            `;
        }).join('');

        document.getElementById('abilities').innerHTML = `<ul>${abilitiesList}</ul>`;
        resultDiv.classList.remove('hidden');
    })
    .catch(error => {
        errorDiv.textContent = error.message;
        errorDiv.classList.remove('hidden');
    });
}

document.getElementById('searchBtn').addEventListener('click', searchPokemon);
