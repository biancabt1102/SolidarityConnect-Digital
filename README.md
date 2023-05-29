# SolidarityConnect

<i>O SolidarityConnect é um aplicativo voltado para mercados e restaurantes que desejam fazer doações de alimentos para ONGs que trabalham com a distribuição de alimentos para pessoas em situação de insegurança alimentar ou necessidade. 
O objetivo principal do aplicativo é facilitar e agilizar o processo de doação.</i>

<hr>

<br>

## Procedimentos para instalação e execução da API

Primeiro de tudo, você irá fazer o clone do projeto utilizando o `Git` já previamente instalado no seu computador/notebook, logo depois de ter o clone instalado você irá abrir o `Visual Studio Code` ir em "Arquivo" > "Abrir pasta..." e selecionar a pasta que está o seu projeto instalado. Após tudo isso e verificar se possue o `Java` em seu computador e as extensões necessárias no VS Code, você pode rodar o projeto no canto superior direito no símbolo de "Play" e clicar na seta ao lado do símbolo em "Run Java". Agora para rodar os testes JSONs que estam nesse README você vai utilizar o `Postman` ou `Insomnia`.

Quando você for utilizar uma das ferramentas que falamos por último, você tem que fazer o registro do usuário fazendo um http `POST`, com as informações, nome do usuário, email, senha, cnpj e telefone. Após isso, você fazer o login usando um http do tipo `POST` com o email e senha. Na saída você vai pegar o Token para testar os outros endpoints.

<br>

## Endpoints

- Alimento
    - [Cadastrar](#cadastrar-alimento)
    - [Listar todos](#listar-alimentos)
    - [Apagar](#apagar-alimento)
    - [Atualizar](#atualizar-alimento)
    - [Detalhes](#detalhes-alimento)
- Endereço
    - [Cadastrar](#cadastrar-endereço)
    - [Listar todos](#listar-endereços)
    - [Apagar](#apagar-endereço)
    - [Atualizar](#atualizar-endereço)
    - [Detalhes](#detalhes-endereço)
- Usuário
    - [Cadastrar](#cadastrar-usuário)
    - [Login](#login-usuário)
    - [Listar todos](#listar-usuários)
    - [Apagar](#apagar-usuário)
    - [Atualizar](#atualizar-usuário)
    - [Detalhes](#detalhes-usuário)

    <hr>

### Cadastrar Alimento

`POST` /solidarityconnect/api/alimento

*Campos da requisição*

| Campo              | Tipo    | Obrigatório | Descrição                           |
|--------------------|---------|:-----------:|-------------------------------------|
| nomeAlimento       | texto   | Sim         | O nome do alimento                  |
| validadeAlimento   | data    | Não         | A data de validade do alimento      |
| quantidadeAlimento | inteiro | Sim         | A quantidade disponível do alimento |
| tipoAlimento       | texto   | Sim         | O tipo do alimento                  |

```
{
    "idAlimento": 1,
    "nomeAlimento": "Maçã",
    "validadeAlimento": "2023-06-01",
    "quantidadeAlimento": 10,
    "tipoAlimento": "perecível"
}
```
*Corpo da resposta*
|código|descrição                   |
|:----:|----------------------------|
|201   | Alimento criado com sucesso|
|400   | Alimento inválido          |

<hr>

### Listar Alimentos

`GET` /solidarityconnect/api/alimento

*Exemplo de resposta*

| Campo              | Tipo    | Descrição                           |
|--------------------|---------|-------------------------------------|
| idAlimento         | Long    | O ID do alimento                    |
| nomeAlimento       | texto   | O nome do alimento                  |
| validadeAlimento   | data    | A data de validade do alimento      |
| quantidadeAlimento | inteiro | A quantidade disponível do alimento |
| tipoAlimento       | texto   | O tipo do alimento                  |

```
[
    {
        "idAlimento": 1,
        "nomeAlimento": "Maçã",
        "validadeAlimento": "2023-06-01",
        "quantidadeAlimento": 10,
        "tipoAlimento": "perecível"
    },
    {
        "idAlimento": 2,
        "nomeAlimento": "Filé Mignon",
        "validadeAlimento": "2023-06-15",
        "quantidadeAlimento": 5,
        "tipoAlimento": "perecível"
    },
    {
        "idAlimento": 3,
        "nomeAlimento": "Milho em conserva",
        "validadeAlimento": "2024-12-31",
        "quantidadeAlimento": 8,
        "tipoAlimento": "enlatado"
    }
]
```

*Corpo da resposta*

| Código | Descrição                   |
|:------:|-----------------------------|
|200     | Listagem feita com sucesso. |
|404     | Lista não encontrada.       |

<hr>

### Apagar Alimento

`DELETE` /solidarityconnect/api/alimento/{id}

*Corpo da resposta*

| Código | Descrição                |
|:------:|--------------------------|
|204     | Requisição bem-sucedida. |
|404     | Conteúdo não encontrado. |

<hr>

### Atualizar Alimento

`PUT` /solidarityconnect/api/alimento/{id}

*Campos da requisição*

| Campo              | Tipo    | Obrigatório | Descrição                           |
|--------------------|---------|:-----------:|-------------------------------------|
| idAlimento         | Long    | Sim         | O ID do alimento                    |
| nomeAlimento       | texto   | Não         | O nome do alimento                  |
| validadeAlimento   | data    | Não         | A data de validade do alimento      |
| quantidadeAlimento | inteiro | Não         | A quantidade disponível do alimento |
| tipoAlimento       | texto   | Não         | O tipo do alimento                  |

```
{
    "idAlimento": 1,
    "nomeAlimento": "Banana",
    "validadeAlimento": "2023-05-30",
    "quantidadeAlimento": 15,
    "tipoAlimento": "perecível"
}
```

*Corpo de resposta*

| Código | Descrição                        |
|:------:|----------------------------------|
|200     | Alteração realizada com sucesso. |
|400     | Alteração inválida.              |
|404     | Alimento não encontrado.         |

<hr>

### Detalhes Alimento

`GET` /solidarityconnect/api/alimento

*Exemplo de resposta*

| Campo              | Tipo    | Descrição                           |
|--------------------|---------|-------------------------------------|
| idAlimento         | Long    | O ID do alimento                    |
| nomeAlimento       | texto   | O nome do alimento                  |
| validadeAlimento   | data    | A data de validade do alimento      |
| quantidadeAlimento | inteiro | A quantidade disponível do alimento |
| tipoAlimento       | texto   | O tipo do alimento                  |

```
[
    {
        "idAlimento": 1,
        "nomeAlimento": "Banana",
        "validadeAlimento": "2023-05-30",
        "quantidadeAlimento": 15,
        "tipoAlimento": "perecível"
    }
]
```

*Corpo da resposta*

| Código | Descrição                                   |
|:------:|---------------------------------------------|
|200     | Os dados foram retornados com sucesso.      |
|404     | Não foi encontrado um alimento com esse ID. |

<hr>

### Cadastrar Endereço

`POST` /solidarityconnect/api/endereco

*Campos da requisição*

| Campo                 | Tipo    | Obrigatório | Descrição                                   |
|-----------------------|---------|:-----------:|---------------------------------------------|
| logradouroEndereco    | texto   | sim         | O logradouro do endereço                    |
| numeroEndereco        | inteiro | sim         | O número do endereço                        |
| cepEndereco           | texto   | sim         | O CEP do endereço no formato XXXXX-XXX      |
| bairroEndereco        | texto   | sim         | O bairro do endereço                        |
| ufEndereco            | texto   | sim         | A UF (Unidade Federativa) do endereço       |
| complementoEndereco   | texto   | não         | O complemento do endereço (opcional)        |
| idUsuario             | Long    | sim         | O ID do usuário ao qual o endereço pertence |

Exemplo de requisição:

```
{
    "idEndereco": 1,
    "logradouroEndereco": "Rua Principal",
    "numeroEndereco": 123,
    "cepEndereco": "12345-678",
    "bairroEndereco": "Centro",
    "ufEndereco": "SP",
    "complementoEndereco": "Apartamento 10",
    "idUsuario": 1
}
```

*Corpo da resposta*
|código|descrição                   |
|:----:|----------------------------|
|201   | Endereço criado com sucesso|
|400   | Endereço inválido          |

<hr>

### Listar Endereços

`GET` /solidarityconnect/api/alimento

*Exemplo de resposta*

| Campo                 | Tipo    | Descrição                                   |
|-----------------------|---------|---------------------------------------------|
| idEndereco            | Long    | O ID do endereço                            |
| logradouroEndereco    | texto   | O logradouro do endereço                    |
| numeroEndereco        | inteiro | O número do endereço                        |
| cepEndereco           | texto   | O CEP do endereço no formato XXXXX-XXX      |
| bairroEndereco        | texto   | O bairro do endereço                        |
| ufEndereco            | texto   | A UF (Unidade Federativa) do endereço       |
| complementoEndereco   | texto   | O complemento do endereço (opcional)        |
| idUsuario             | Long    | O ID do usuário ao qual o endereço pertence |

```
[
    {
        "idEndereco": 1,
        "logradouroEndereco": "Rua Principal",
        "numeroEndereco": 123,
        "cepEndereco": "12345-678",
        "bairroEndereco": "Centro",
        "ufEndereco": "SP",
        "complementoEndereco": "Apartamento 10",
        "idUsuario": 1
    },
    {
        "idEndereco": 2,
        "logradouroEndereco": "Avenida Central",
        "numeroEndereco": 456,
        "cepEndereco": "98765-432",
        "bairroEndereco": "Vila Nova",
        "ufEndereco": "RJ",
        "complementoEndereco": null,
        "idUsuario": 2
    },
    {
        "idEndereco": 3,
        "logradouroEndereco": "Rua das Flores",
        "numeroEndereco": 789,
        "cepEndereco": "54321-876",
        "bairroEndereco": "Jardim Primavera",
        "ufEndereco": "MG",
        "complementoEndereco": "Casa 2",
        "idUsuario": 3
    }
]
```
*Corpo da resposta*

| Código | Descrição                   |
|:------:|-----------------------------|
|200     | Listagem feita com sucesso. |
|404     | Lista não encontrada.       |

<hr>

### Apagar Endereço

`DELETE` /solidarityconnect/api/endereco/{id}

*Corpo da resposta*

| Código | Descrição                |
|:------:|--------------------------|
|204     | Requisição bem-sucedida. |
|404     | Conteúdo não encontrado. |

<hr>

### Atualizar Endereço

`PUT` /solidarityconnect/api/endereco/{id}

*Campos da requisição*

| Campo                 | Tipo    | Obrigatório | Descrição                 |
|-----------------------|---------|:-----------:|---------------------------|
| idEndereco            | Long    | Sim         | O ID do endereço          |
| logradouroEndereco    | texto   | Não         | O logradouro do endereço  |
| numeroEndereco        | inteiro | Não         | O número do endereço      |
| cepEndereco           | texto   | Não         | O CEP do endereço         |
| bairroEndereco        | texto   | Não         | O bairro do endereço      |
| ufEndereco            | texto   | Não         | A UF do endereço          |
| complementoEndereco   | texto   | Não         | O complemento do endereço |
| idUsuario             | Long    | Não         | O ID do usuário associado |

```
{
    "idEndereco": 1,
    "logradouroEndereco": "Rua das Aventuras",
    "numeroEndereco": 777,
    "cepEndereco": "12345-678",
    "bairroEndereco": "Vila da Imaginação",
    "ufEndereco": "FZ",
    "complementoEndereco": "Andar das Maravilhas",
    "idUsuario": 1
}
```

*Corpo de resposta:*

| Código | Descrição                           |
|:------:|-------------------------------------|
| 200    | Alteração realizada com sucesso.    |
| 400    | Alteração inválida.                 |
| 404    | Endereço não encontrado.            |

<hr>

### Detalhes Endereço

`GET` /solidarityconnect/api/endereco/{id}

*Campos da resposta*

| Campo                | Tipo    | Descrição                                 |
|----------------------|---------|-------------------------------------------|
| idEndereco           | Long    | O ID do endereço                          |
| logradouroEndereco   | texto   | O logradouro do endereço                  |
| numeroEndereco       | inteiro | O número do endereço                      |
| cepEndereco          | texto   | O CEP do endereço                         |
| bairroEndereco       | texto   | O bairro do endereço                      |
| ufEndereco           | texto   | A UF (Unidade Federativa) do endereço     |
| complementoEndereco  | texto   | O complemento do endereço (opcional)      |
| idUsuario            | Long    | O ID do usuário associado ao endereço     |

```
    "idEndereco": 1,
    "logradouroEndereco": "Rua das Aventuras",
    "numeroEndereco": 777,
    "cepEndereco": "12345-678",
    "bairroEndereco": "Vila da Imaginação",
    "ufEndereco": "FZ",
    "complementoEndereco": "Andar das Maravilhas",
    "idUsuario": 1
```

*Corpo da resposta*

| Código | Descrição                                   |
|:------:|---------------------------------------------|
| 200    | Os dados foram retornados com sucesso.      |
| 404    | Não foi encontrado um endereço com esse ID. |

<hr>

### Cadastrar Usuário

`POST` /solidarityconnect/api/usuarios/cadastro

*Campos da requisição*

| Campo           | Tipo    | Obrigatório | Descrição                           |
|-----------------|---------|:-----------:|-------------------------------------|
| nomeUsuario     | texto   | sim         | O nome do usuário                   |
| emailUsuario    | texto   | sim         | O email do usuário                  |
| senhaUsuario    | texto   | sim         | A senha do usuário                  |
| cnpjUsuario     | texto   | sim         | O CNPJ do usuário                   |
| telefoneUsuario | texto   | sim         | O telefone do usuário               |

```
{
    "idUsuario":1,
    "nomeUsuario": "Supermercado ABC",
    "emailUsuario": "contato@superabc.com",
    "senhaUsuario": "senha123",
    "cnpjUsuario": "12.345.678/0001-90",
    "telefoneUsuario": "(12) 3456-7890"
}

```

*Corpo da resposta*

| Código | Descrição                           |
|:------:|-------------------------------------|
| 201    | Usuário criado com sucesso.         |
| 400    | Usuário inválido.                   |

<hr>

### Login Usuário

`POST` /solidarityconnect/api/usuarios/login

*Campos da requisição*

| Campo           | Tipo    | Obrigatório | Descrição                           |
|-----------------|---------|:-----------:|-------------------------------------|
| emailUsuario    | texto   | sim         | O email do usuário                  |
| senhaUsuario    | texto   | sim         | A senha do usuário                  |

```
{
    "emailUsuario": "contato@superabc.com",
    "senhaUsuario": "senha123"
}
```

*Corpo da resposta*

```
{
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJqb2huZG9AZXhhbXBsZS5jb20iLCJpc3MiOiJTb2xpZGFyaXR5Q29ubmVjdCIsImV4cCI6MTY4NTEyMjgxOX0.bhzZvQRn0yDtzhqs-EF9uySDQcRNl1k5gJAMqJfoNNk",
    "type": "JWT",
    "prefix": "Bearer"
}
```

| Código | Descrição                           |
|:------:|-------------------------------------|
| 200    | Usuário logado.                     |
| 403    | Usuário não autorizado              |

<hr>

### Listar Usuários

`GET` /solidarityconnect/api/usuario

*Exemplo de resposta*

| Campo           | Tipo    | Descrição                          |
|-----------------|---------|------------------------------------|
| idUsuario       | Long    | O ID do usuário                    |
| nomeUsuario     | texto   | O nome do usuário                  |
| emailUsuario    | texto   | O email do usuário                 |
| senhaUsuario    | texto   | A senha do usuário                 |
| cnpjUsuario     | texto   | O CNPJ do usuário                  |
| telefoneUsuario | texto   | O telefone do usuário              |

```
[
    {
        "idUsuario":1,
        "nomeUsuario": "Supermercado ABC",
        "emailUsuario": "contato@superabc.com",
        "senhaUsuario": "senha123",
        "cnpjUsuario": "12.345.678/0001-90",
        "telefoneUsuario": "(12) 3456-7890"
    },
    {
        "idUsuario": 2,
        "nomeUsuario": "Mercado Delícias",
        "emailUsuario": "mercado@delicias.com",
        "senhaUsuario": "mercado123",
        "cnpjUsuario": "12.345.678/0001-90",
        "telefoneUsuario": "(12) 3456-7890"
    },
    {
        "idUsuario": 3,
        "nomeUsuario": "Restaurante Saboroso",
        "emailUsuario": "contato@saboroso.com",
        "senhaUsuario": "restaurante123",
        "cnpjUsuario": "98.765.432/0001-21",
        "telefoneUsuario": "(21) 9876-5432"
    }
]
```

*Corpo da resposta*

| Código | Descrição                    |
|:------:|------------------------------|
|200     | Listagem feita com sucesso.  |
|404     | Lista não encontrada.        |

<hr>

### Apagar Usuário

`DELETE` /solidarityconnect/api/usuario/{id}

*Corpo da resposta*

| Código | Descrição                |
|:------:|--------------------------|
|204     | Requisição bem-sucedida. |
|404     | Conteúdo não encontrado. |

<hr>

### Atualizar Usuário

`PUT` /solidarityconnect/api/usuario/{id}

*Campos da requisição*

| Campo           | Tipo    | Obrigatório | Descrição             |
|-----------------|---------|:-----------:|-----------------------|
| idUsuario       | Long    | Sim         | O ID do usuário       |
| nomeUsuario     | texto   | Não         | O nome do usuário     |
| emailUsuario    | texto   | Não         | O email do usuário    |
| senhaUsuario    | texto   | Não         | A senha do usuário    |
| cnpjUsuario     | texto   | Não         | O CNPJ do usuário     |
| telefoneUsuario | texto   | Não         | O telefone do usuário |

```
{
    "idUsuario": 1,
    "nomeUsuario": "Restaurante Sabores Exóticos",
    "emailUsuario": "contato@saboresexoticos.com",
    "senhaUsuario": "restaurante123",
    "cnpjUsuario": "12.345.678/0001-90",
    "telefoneUsuario": "(12) 3456-7890"
}
```


*Corpo da resposta:*

| Código | Descrição                            |
|:------:|--------------------------------------|
| 200    | Alteração realizada com sucesso.     |
| 400    | Alteração inválida.                  |
| 404    | Usuário não encontrado.              |

<hr>

### Detalhes Usuário

`GET` /solidarityconnect/api/usuario/{id}

*Campos da resposta*

| Campo           | Tipo    | Descrição             |
|-----------------|---------|-----------------------|
| idUsuario       | Long    | O ID do usuário       |
| nomeUsuario     | texto   | O nome do usuário     |
| emailUsuario    | texto   | O email do usuário    |
| senhaUsuario    | texto   | A senha do usuário    |
| cnpjUsuario     | texto   | O CNPJ do usuário     |
| telefoneUsuario | texto   | O telefone do usuário |

```
{
    "idUsuario": 1,
    "nomeUsuario": "Restaurante Sabores Exóticos",
    "emailUsuario": "contato@saboresexoticos.com",
    "senhaUsuario": "restaurante123",
    "cnpjUsuario": "12.345.678/0001-90",
    "telefoneUsuario": "(12) 3456-7890"
}
```


*Corpo da resposta:*

| Código | Descrição                                 |
|:------:|-------------------------------------------|
| 200    | Os dados foram retornados com sucesso.    |
| 404    | Não foi encontrado um usuário com esse ID.|



