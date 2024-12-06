# Banco Imobiliário Orientado a Objetos

O presente projeto trata-se de um **Banco Imobiliário desenvolvido em linguagem Java com utilização do paradigma de Programação Orientada a Objetos (POO) e padrões de projeto determinados**.

## Padrões de Projeto (Design Patterns)

Foram selecionados quatro padrões de projetos para a aplicação no trabalho, sendo eles: 
- Padrão Model-View-Controller (arquitetura de software)
- Padrões GoF 
    - Abstract Factory (criacional)
    - Mediator, Memento e Observer (comportamentais) 
    - Decorator (estrutural)
- Padrões GRASP 
    - Low Coupling
    - Controller 
    - Polymorphism 
    - Indirection  
Os respectivos padrões e as motivações para as suas aplicações foram descritos nos tópicos seguintes.

### Model-View-Controller (MVC)

A aplicação do padrão em questão se deu através da estrutura de diretórios e organização de arquivos para a devida separação de componentes do projeto e atribuição bem definida de responsabilidades para cada camada do software. A arquitetura do projeto seguindo o MVC resultou em:
```
bancoImobiliario
│   README.md
│   Main.java    
└───src
│   │
│   └───Content
│   │
│   └───Controllers
│   │
│   └───Models
│   │
│   └───Views
```  

### Padrões Gang of Four (GoF)

Os objetivos para as implementações dos padrões GoF selecionados são: 

- **Criacional**
    - **Singleton:** Garantir declaração única e acesso global ao tabuleiro do jogo;
- **Comportamentais**
    - **Mediator:** Seguir as boas práticas de desacoplamento e possibilitar que possíveis alterações em classes sejam feitas sem que as suas relações sejam afetadas;
    - **Memento:** Possibilitar a manutenção (save) e retomada (load) de estado do jogo, garantindo que, ao interromper a partida, não sejam perdidos os dados do momento de parada;
    - **Observer:** Facilitar o intermédio entre a parte operacional do jogo e a parte de interface, estabelecendo princípios para a comunicação entre elas;
- **Estrutural**
    - **Decorator:** Possibilitar a inserção e remoção de responsabilidades e comportamentos em objetos individuais de forma dinamica (enquanto ocorre o fluxo do jogo);

### Padrões General Responsibility Assignment Software Patterns (GRASP)

- **Low Coupling:** Prezar pela redução de dependências e maior especificidades de classes;
- **Controller:** Facilitar a parcela de controle estabelecida pela estrutura de software no formato MVC;
- **Polymorphism:** Possibilitar que classes que dependem de um mesmo método possam usá-lo de acordo com as suas respectivas especificidades;
- **Indirection:** Fazer da parcela de controle a mediadora para atuar entre objetos sem que dependam diretamente um do outro.