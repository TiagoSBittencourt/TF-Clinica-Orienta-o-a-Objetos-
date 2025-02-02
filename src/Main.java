import entidades.*;
import servicos.Clinica;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        // Criar Medico
        Medico.adicionarMedico("Jorge", "123-123-123-00", LocalDate.of(1999, 12, 31), 1234, "especialidade");
        System.out.println(Medico.buscarPorCpf("123-123-123-00").toString());

        System.out.println("\n-------------\n");
        Paciente.adicionarPaciente("Rodrygo", "123-123-123-01", LocalDate.of(1999, 12, 31));

        System.out.println(Paciente.buscarPorCpf("123-123-123-01").toString());
    }
}