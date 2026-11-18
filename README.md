# 🏋️ Sistema de Gerenciamento de Academia

Backend em Java para organizar alunos, planos, matrículas, atividades e pagamentos de uma academia. O projeto carrega e salva os dados em arquivos de texto, mantendo as informações disponíveis entre as execuções.

Desenvolvido como projeto acadêmico com conceitos de programação orientada a objetos, classes, herança, coleções e manipulação de arquivos.

---

## 👥 Integrantes do Grupo

- Lívia Ribeiro
- Iago Oliveira
- Monara Soares
- Ana Julia Inácio

---

## 🔗 Repositório no GitHub

[Sistema-Gerenciamento-Academia](https://github.com/IagoBahia-12/Sistema-Gerenciamento-Academia)

---

## 🗂️ Estrutura do Projeto

```text
.
├── src/
│   ├── Main.java          # Inicialização e leitura/gravação dos dados
│   ├── ADM.java           # Operações de gerenciamento da academia
│   ├── Pessoa.java        # Dados comuns de uma pessoa
│   ├── Aluno.java         # Dados e estado da matrícula do aluno
│   ├── Plano.java         # Plano e período de matrícula
│   ├── Atividade.java     # Atividade e alunos inscritos
│   ├── Pagamento.java     # Informações de pagamento
│   ├── SenhaUtil.java     # Geração e verificação de hash de senha
│   ├── alunos.txt         # Dados dos alunos
│   ├── planos.txt         # Planos disponíveis
│   ├── atividades.txt     # Atividades cadastradas
│   └── pagamentos.txt    # Histórico de pagamentos
└── README.md
```

### Classes principais

- `ADM`: reúne operações para cadastrar, consultar, atualizar e excluir alunos; gerenciar planos e atividades; controlar matrículas e registrar pagamentos.
- `Aluno`: mantém os dados do aluno, o plano associado e o histórico de pagamentos.
- `Plano`: registra benefícios, dados de pagamento e datas de início e término da matrícula.
- `Atividade`: armazena a descrição da atividade e os alunos inscritos.
- `SenhaUtil`: gera e verifica hashes de senha com PBKDF2 e HMAC-SHA256.

---

## ⚙️ Requisitos

- JDK 11 ou superior
- Terminal ou IDE Java, como Visual Studio Code com o Extension Pack for Java

## ⚙️ Como Compilar

No terminal, a partir da pasta raiz do projeto, rode:

```bash
javac -d out src/*.java
```

O comando cria a pasta `out` com os arquivos compilados.

---

## ▶️ Como Executar

Ainda na pasta raiz do projeto, execute:

```bash
java -cp out Main
```

O programa procura os arquivos de dados dentro da pasta `src/`. Por isso, execute o comando a partir da raiz do projeto. Na versão atual, a inicialização carrega os dados e lista os nomes dos alunos no console.

---

## 🕹️ Funcionalidades disponíveis nas classes

- Cadastrar, buscar, consultar, atualizar e excluir alunos.
- Fazer login de aluno por CPF e senha.
- Cadastrar, buscar, consultar e atualizar planos.
- Realizar, renovar e cancelar matrículas.
- Cadastrar e consultar atividades, inscrever alunos e removê-los de atividades.
- Registrar pagamentos de alunos e planos e consultar o histórico de pagamentos.

As operações estão implementadas como métodos das classes, principalmente em `ADM`. O `Main` ainda não apresenta um menu interativo nem solicita dados pelo teclado para acionar essas operações.

---

## 💾 Persistência de Dados

Os dados são armazenados em arquivos `.txt` dentro de `src/`. Cada registro usa `#` para separar os campos:

- `alunos.txt`: `id#nome#cpf#telefone#email#senha#estado#plano#inicio#fim`
- `planos.txt`: `nome#beneficios#valor#forma_pagamento#data`
- `pagamentos.txt`: `cpf#valor#forma_pagamento#data`
- `atividades.txt`: `nome#descricao#cpfs_dos_alunos`

O programa usa `-` para representar campos sem valor em alguns registros. O caractere `#` não deve ser usado nos campos de texto, pois é o separador dos arquivos.

---

## 🧠 Observações Técnicas

- Os dados de alunos, planos e atividades são mantidos em coleções `ArrayList` durante a execução.
- As matrículas registram datas de início e término usando `LocalDate`.
- As senhas são armazenadas como hash com salt, usando PBKDF2 com HMAC-SHA256.
- Os caminhos dos arquivos são relativos (`src/...`), então a execução deve partir da pasta raiz.
- No estado atual, a rotina de gravação de atividades precisa ser revisada para preservar corretamente a lista de alunos inscritos no arquivo.
