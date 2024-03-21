# Criptografia

O desafio foi implementar criptografia em um serviço de forma transparente para a API e para as camadas de serviço da aplicação.
O objetivo foi garantir que os dados sensíveis não sejam visíveis diretamente, 
realizando a criptografia em tempo de execução durante a conversão da entidade para a coluna correspondente.
A criptografia utilizada foi AES256, que é um tipo de criptografia simétrica,
onde a chave utilizada para criptografia é a mesma para a descriptografia.

## Exemplo

Considere os campos `userDocument` e `creditCardToken` como campos sensíveis que devem ser criptografados. A tabela de
exemplo seria a seguinte:

| id | userDocument     | creditCardToken | value |
|:---|:-----------------|:----------------|:------|
| 1  | eNwVL6SXWlIdTWwUIwEG9FqLhtaru9b0+GL6dhyvLIUbdIJutKmiVHYHGT3w6Gqx | 2gqwqffQvGAFxvCEUDMsbzE6cAru9VB7f5OyaE9S1Xc4Dxp8TWFqHYwGhixbAlfn | 5999  |

A estrutura da entidade correspondente seria a seguinte:

| Campo           | Tipo   |
|:----------------|:-------|
| id              | Long   |
| userDocument    | String |
| creditCardToken | String |
| value           | Long   |


## Creditos
https://github.com/backend-br/desafios
