# Agenda App

## Descrição

O **Agenda App** é um aplicativo de agenda desenvolvido como parte do módulo de Programação Orientada a Objetos (POO) do curso de Backend com Java da AdaTech. Este aplicativo inicialmente foi criado utilizando conceitos básicos e agora foi atualizado com conceitos de POO.

## Objetivo

O objetivo do aplicativo é gerenciar contatos de forma eficiente, permitindo adicionar, editar, listar e excluir contatos. A atualização para POO trouxe melhorias significativas na estrutura do código, tornando-o mais modular, reutilizável e fácil de manter.

## Conceitos Implementados

### 1. **Classes e Objetos**

O aplicativo foi estruturado em várias classes, cada uma responsável por um aspecto específico do sistema. As principais classes são:

- **Contato**: Representa um contato com atributos como nome, telefone e e-mail.
- **Agenda**: Gerencia uma lista de contatos e fornece métodos para adicionar, remover e listar contatos.

### 2. **Encapsulamento**

Os atributos das classes são privados e acessados através de métodos públicos (getters e setters). Isso garante que os dados sejam protegidos e acessados de maneira controlada.

### 3. **Herança**

As classes podem ser estendidas para criar tipos de contatos mais específicos, se necessário. Por exemplo, uma classe `ContatoComEndereco` poderia herdar de `Contato` e adicionar um atributo para o endereço.

### 4. **Polimorfismo**

Métodos com o mesmo nome podem ser usados em diferentes contextos, permitindo que o aplicativo seja mais flexível e adaptável a diferentes tipos de entrada e operação.

## Estrutura do Projeto

O projeto é organizado nas seguintes classes, que interagem entre si para oferecer uma gestão de contatos abrangente:

- **Agenda**: Gerencia uma lista de contatos e oferece métodos para adicionar, remover e listar contatos.
- **Contato**: Representa um contato com atributos básicos como nome, telefone e e-mail.
- **ContatoComGithub**: Extende a classe `Contato` para incluir informações específicas sobre o perfil do GitHub.
- **ContatoComLinkedin**: Extende a classe `Contato` para incluir informações específicas sobre o perfil do LinkedIn.
- **Linkedin**: Representa um perfil do LinkedIn, com atributos como URL e slug (nome de usuário).
- **Github**: Representa um perfil do GitHub, com atributos como URL e slug (nome de usuário).
- **Main**: Classe principal que executa o aplicativo e gerencia a interação entre as diferentes partes do sistema.

A interação entre essas classes é projetada para garantir que o aplicativo funcione de maneira eficiente e coesa, aproveitando o encapsulamento, herança e polimorfismo para oferecer uma gestão robusta e flexível dos contatos.


