# Sistema de Gestão Clínica

Este projeto foi desenvolvido como parte da disciplina **Orientação a Objetos (FGA-0158)**, ministrada pelo **Prof. Dr. André Luiz Peron Martins Lanna**. O sistema implementa funcionalidades para gestão de pacientes, médicos e consultas em uma clínica.

## 🚀 Funcionalidades

- **Cadastro de Pacientes e Médicos**: Permite adicionar novos pacientes e médicos ao sistema.
- **Pesquisa de Registros**: Possibilidade de buscar pacientes, médicos e consultas cadastradas.
- **Listagem de Consultas**: Exibe todas as consultas registradas no sistema.
- **Interface Gráfica Simples**: Utiliza `JOptionPane` para interações com o usuário.

## 📂 Estrutura do Projeto

O projeto está estruturado em diferentes classes, sendo a principal delas `Clinica`, que contém o método `main` para inicialização do sistema.

```
├── src  
│   ├── Clinica.java  # Classe principal (main)  
│   ├── entidades  
│   │   ├── Medico.java  
│   │   ├── MedicoInterface.java  
│   │   ├── Paciente.java  
│   │   ├── PacienteInterface.java  
│   │   ├── Pessoa.java  
│   │   ├── PessoaInterface.java  
│   ├── servicos  
│   │   ├── Consulta.java  
│   │   ├── Exame.java  
│   │   ├── Prescricao.java  
└── README.md  
```

## 🛠️ Como Rodar o Projeto

1. **Baixe o código-fonte** ou clone o repositório.
2. **Abra o projeto** em uma IDE compatível com Java (Eclipse, IntelliJ, NetBeans, etc.).
3. **Compile o código** e execute a classe `Clinica.java`, que contém o método `main`.
4. **Interaja com o sistema** através da interface `JOptionPane` para realizar operações de pesquisa e listagem.

## 👨‍💻 Participantes do Projeto

**Discentes:**
- **Tiago Santo Bittencourt** – 241011653
- **Bernardo Broetto Brun** – 241010950

---
Desenvolvido como parte do curso de **Engenharia de Software** da **Universidade de Brasília (UnB) - FGA**.

