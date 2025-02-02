package entidades;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;

class Pessoa {
    private static final HashSet<String> cpfsRegistrados = new HashSet<>();
    private static int contadorIds = 1;

    private final Integer id;
    private final String cpf;
    protected String nome;
    protected LocalDate dataNascimento;
    protected ArrayList<String> historicoMedico;

    public Pessoa(String nome, String cpf, LocalDate dataNascimento) {
        if (!isCpfValido(cpf)) {
            throw new IllegalArgumentException("CPF ja registrado");
        }

        this.cpf = cpf;
        cpfsRegistrados.add(cpf);

        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.id = contadorIds++;
        this.historicoMedico = new ArrayList<>();
    }

    @Override
    public String toString() {
        return  "\nID: " + this.id +
                "\nNome: " + this.nome +
                "\nCPF: " + this.cpf +
                "\nData de nascimento: " + this.dataNascimento +
                "\nHistporico Medico: " + this.historicoMedico;
    }

    public static boolean isCpfValido(String cpf) {
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


}