package entidades;

import java.time.LocalDate;
import java.util.ArrayList;

class Pessoa {
    protected Integer id;
    protected String nome;
    protected String cpf;
    protected LocalDate dataNascimento;
    protected ArrayList<String> historicoMedico;

    // Array de CPF registrado
    private static ArrayList<String> cpfsRegistrados = new ArrayList<>();

    // Contador de ID
    private static Integer contadorIds = 1;

    public Pessoa(String nome, String cpf, LocalDate dataNascimento) {

        // Verifica se o CPF já foi registrado
        if (isCpfValido(cpf)) {
            this.cpf = cpf;
            cpfsRegistrados.add(cpf); // Adiciona o CPF à lista de registrados
        } else {
            System.out.println("CPF já registrado");
            return; // Ou lançar uma exceção se preferir
        }

        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.id = contadorIds++;  // ID unico para a instancia e aumenta o contatdor (+1) depois
        this.historicoMedico = new ArrayList<>();
    }

    public void displayDados() {
        System.out.println("ID: " + this.id);
        System.out.println("Nome: " + this.nome);
        System.out.println("CPF: " + this.cpf);
        System.out.println("Data de nascimento: " + this.dataNascimento);
        System.out.println("Historico Medico: " + this.historicoMedico);
    }

    // Checa se o CPF ja esta registrado
    private boolean isCpfValido(String cpf) {
        return !cpfsRegistrados.contains(cpf);
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public ArrayList<String> getHistoricoMedico() {
        return historicoMedico;
    }

    public void adicionaHistoricoMedico(String historicoMedico) {
        this.historicoMedico.add(historicoMedico);
    }

    public void deletarHistoricoMedico(Integer i) {
        historicoMedico.remove(i);
    }
}