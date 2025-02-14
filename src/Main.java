import entidades.*;
import servicos.*;

public class Main {
    public static void main(String[] args) {
        // Testar Interface JoptionPane
        System.out.println(Medico.getMedicos());
        System.out.println(Paciente.getPacientes());

        System.out.println(Consulta.getConsultas());

        //Clinica.interfaceAdicionarPaciente();
        //Clinica.interfaceAdicionarMedico();




//        // Criar Medico
//        Medico.adicionarMedico("Jorge", "123-123-123-00", LocalDate.of(1999, 12, 31), 1234, "especialidade");
//        System.out.println(Medico.buscarPorCpf("123-123-123-00").toString());
//
//        System.out.println("\n-------------\n");
//
//        // Criar Paciente
//        Paciente.adicionarPaciente("Rodrygo", "123-123-123-01", LocalDate.of(1999, 12, 31));
//        System.out.println(Paciente.buscarPorCpf("123-123-123-01").toString());
//
//        System.out.println("\n-------------\n");
//        // Criar Consulta
//        Consulta.agendarConsulta(LocalDate.of(2023, 12, 31),
//                                 LocalTime.of(14,30), 30,
//                                 AGENDADA,
//                                 Paciente.buscarPorCpf("123-123-123-01"),
//                                 Medico.buscarPorCpf("123-123-123-00")
//        );
//
//
//        System.out.println(Consulta.buscarConsultaPorId(0).toString());
//
    }
}