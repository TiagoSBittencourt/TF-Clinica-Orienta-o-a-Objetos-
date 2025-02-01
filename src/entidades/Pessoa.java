package entidades;

import java.time.LocalDate;
import java.util.ArrayList;

class Pessoa {
    protected String nome;
    protected String cpf;
    protected LocalDate dataNascimento;
    protected ArrayList<String> historicoMedico; // TODO: "Consulta" Implementation
    private static ArrayList<String> cpfsRegistrados = new ArrayList<>();

    public Pessoa(String nome, String cpf, LocalDate dataNascimento) {
        this.nome = nome;
        // TODO: Better Error Handling and Better Error Customization
        if (isCpfValido(cpf)) {
            this.cpf = cpf;
            cpfsRegistrados.add(cpf); // Adicionando o CPF a lista de CPFs registrados
        } else {
            System.out.println("CPF ja registrado");
        }
        this.dataNascimento = dataNascimento;
        historicoMedico = new ArrayList<>();
    }

    // Checa se o cpf esta no Array statico da classe
    private boolean isCpfValido(String cpf) {
        return !cpfsRegistrados.contains(cpf);
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
