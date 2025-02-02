package servicos;

import entidades.Medico;
import entidades.Paciente;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.ArrayList;
import java.util.Scanner;

public class Clinica {
    private ArrayList<Paciente> paciente;
    private ArrayList<Medico> medicos;

    private static final DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public static void interfaceAdicionarPaciente() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o nome do paciente: "); // TODO: Nao Liberar nomes em branco
        String nomePaciente = sc.nextLine();
        System.out.println("Digite o cpf: "); // TODO: Aplicar um formatador (cpf)
        String cpfPaciente = sc.nextLine();
        Boolean exit = false;
        while (!Paciente.isCpfValido(cpfPaciente)) {
            System.out.println("Cpf ja cadastrado! Digite (s) para sair ou entre ou cpf valido");
            cpfPaciente = sc.nextLine();
            if (cpfPaciente.equalsIgnoreCase("s")) {
                return;
            }
        }

        LocalDate dataNascimentoPaciente = null;
        while (dataNascimentoPaciente == null) {
            try {
                System.out.print("Digite a data de nascimento no formato (dd-mm-yyyy): ");
                String input = sc.nextLine();

                // Tenta converter a string para LocalDate
                dataNascimentoPaciente = LocalDate.parse(input, formato);

                // Checa se a data nao esta no futuro
                if (dataNascimentoPaciente.isAfter(LocalDate.now())) {
                    System.out.println("A data de nascimento nao pode estar no futuro.");
                    dataNascimentoPaciente = null;
                }
            } catch (DateTimeParseException e) {
                System.out.println("Formato invalido! Use o formato dd-mm-yyyy.");
            }
        }
        Paciente.adicionarPaciente(nomePaciente, cpfPaciente, dataNascimentoPaciente);

        System.out.println(Medico.buscarPorCpf(cpfPaciente).toString());
    }
    public static void interfaceAdicionarMedico() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o nome do medico: "); // TODO: Nao Liberar nomes em branco
        String nomeMedico = sc.nextLine();
        System.out.println("Digite o cpf: "); // TODO: Aplicar um formatador (cpf)
        String cpfMedico = sc.nextLine();
        Boolean exit = false;
        while (!Paciente.isCpfValido(cpfMedico)) {
            System.out.println("Cpf ja cadastrado! Digite (s) para sair ou entre ou cpf valido");
            cpfMedico = sc.nextLine();
            if (cpfMedico.equalsIgnoreCase("s")) {
                return;
            }
        }

        LocalDate dataNascimentoMedico = null;
        while (dataNascimentoMedico == null) {
            try {
                System.out.print("Digite a data de nascimento no formato (dd-mm-yyyy): ");
                String input = sc.nextLine();

                // Tenta converter a string para LocalDate
                dataNascimentoMedico = LocalDate.parse(input, formato);

                // Checa se a data nao esta no futuro
                if (dataNascimentoMedico.isAfter(LocalDate.now())) {
                    System.out.println("A data de nascimento nao pode estar no futuro.");
                    dataNascimentoMedico = null;
                }
            } catch (DateTimeParseException e) {
                System.out.println("Formato invalido! Use o formato dd-mm-yyyy.");
            }
        }
        System.out.println("Digite o crm: "); // TODO: Nao Liberar nomes em branco
        Integer crmMedico = (Integer) sc.nextInt();
        System.out.println("Digite o crm: "); // TODO: Nao Liberar nomes em branco
        String especialidadeMedico = sc.nextLine();
        Medico.adicionarMedico(nomeMedico, cpfMedico, dataNascimentoMedico, crmMedico, especialidadeMedico);
        System.out.println(Medico.buscarPorCpf(cpfMedico).toString());
    }

}
