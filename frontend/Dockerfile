# Use uma imagem do Node.js como base
FROM node:18

# Define o diretório de trabalho no container
WORKDIR /app

# Copia os arquivos de configuração do projeto
COPY package*.json ./

# Instala as dependências do projeto
RUN npm install

# Copia o código do frontend para o diretório de trabalho
COPY . .

# Gera a build do frontend, se necessário
RUN npm run build

# Expõe a porta 3000 (ou outra porta configurada no seu frontend)
EXPOSE 3000

# Inicia o servidor do frontend
CMD ["npm", "start"]
