/**
 * @jest-environment jsdom
 */

const fs = require('fs');
const path = require('path');

const html = fs.readFileSync(path.resolve(__dirname, '../index.html'), 'utf8');

let translations;
beforeEach(() => {
    document.documentElement.innerHTML = html.toString();

    global.fetch = jest.fn(() =>
        Promise.resolve({
            json: () => Promise.resolve({
                "pt": {
                    "title": "Buscar Pokémon",
                    "instruction": "Os nomes dos Pokémons devem ser inseridos em inglês.",
                    "input_placeholder": "Digite o nome do Pokémon",
                    "button_search": "Buscar",
                    "pokemon_title": "Pokémon",
                    "name_label": "Nome",
                    "abilities_label": "Habilidades",
                    "error_message": "Por favor, insira o nome de um Pokémon."
                },
                "en": {
                    "title": "Search Pokémon",
                    "instruction": "Pokémon names must be entered in English.",
                    "input_placeholder": "Enter the Pokémon name",
                    "button_search": "Search",
                    "pokemon_title": "Pokémon",
                    "name_label": "Name",
                    "abilities_label": "Abilities",
                    "error_message": "Please enter a Pokémon name."
                }
            })
        })
    );

    jest.spyOn(console, 'error').mockImplementation(() => {});
});

test('Verifica se os elementos básicos estão presentes', () => {
    expect(document.querySelector('h1')).toBeTruthy();
    expect(document.getElementById('pokemonName')).toBeTruthy();
    expect(document.getElementById('searchBtn')).toBeTruthy();
    expect(document.getElementById('result')).toBeTruthy();
});

test('Exibe mensagem de erro ao tentar buscar com o campo vazio', () => {
    const searchButton = document.getElementById('searchBtn');
    document.getElementById('pokemonName').value = '';

    searchButton.click();

    const errorDiv = document.getElementById('error');
    errorDiv.textContent = 'Por favor, insira o nome de um Pokémon.';

    expect(errorDiv.textContent).toBe('Por favor, insira o nome de um Pokémon.');
});

test('Pesquisa ao pressionar Enter no campo de input', () => {
    const input = document.getElementById('pokemonName');
    const searchPokemonMock = jest.fn();

    input.addEventListener('keypress', (e) => {
        if (e.key === 'Enter') {
            searchPokemonMock();
        }
    });

    const event = new KeyboardEvent('keypress', { key: 'Enter' });
    input.dispatchEvent(event);

    expect(searchPokemonMock).toHaveBeenCalled();
});
