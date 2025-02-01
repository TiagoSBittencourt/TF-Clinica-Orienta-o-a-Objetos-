package entidades;

import java.time.LocalDate;

public class Paciente extends Pessoa {

    public Paciente(String nome, String cpf, LocalDate dataNascimento) {
        super(nome, cpf, dataNascimento);
    }

}
