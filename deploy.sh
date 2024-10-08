#!/bin/bash

# Cria as imagens Docker para o backend e frontend
docker build -t your-backend-image:latest ./backend
docker build -t your-frontend-image:latest ./frontend

# Subindo o cluster do Kubernetes e aplicando os arquivos YAML

kubectl apply -f backend-deployment.yaml
kubectl apply -f frontend-deployment.yaml

# Exibe o status dos pods para verificar se estão rodando
kubectl get pods

# Exibe os services para verificar as portas expostas
kubectl get services
