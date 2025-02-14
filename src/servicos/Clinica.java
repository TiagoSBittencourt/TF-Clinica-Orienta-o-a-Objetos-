package servicos;

import entidades.Medico;
import entidades.Paciente;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Clinica {
    private static final DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public static void exibirMenuPrincipal() {
        Object[] opcoes = {"Nova Pessoa", "Nova Consulta", "Sair"};
        while (true) {
            int escolha = JOptionPane.showOptionDialog(null, "Escolha uma opção:", "Clínica",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[0]);

            if (escolha == 0) {
                adicionarEntidade();
            } else if (escolha == 1) {
                adicionarConsulta();
            } else {
                break;
            }
        }
    }

    public static void adicionarEntidade() {
        Object[] opcoes = {"Adicionar Paciente", "Adicionar Médico", "Voltar"};
        while (true) {
            int escolha = JOptionPane.showOptionDialog(null, "Escolha uma opção:", "Adicionar Pessoa",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[0]);

            if (escolha == 0) {
                interfaceAdicionarPaciente();
            } else if (escolha == 1) {
                interfaceAdicionarMedico();
            } else {
                break;
            }
        }
    }

    public static void adicionarConsulta() {
        JOptionPane.showMessageDialog(null, "Funcionalidade de consulta ainda em desenvolvimento.");
    }

    public static void interfaceAdicionarPaciente() {
        String nomePaciente;
        do {
            nomePaciente = JOptionPane.showInputDialog("Digite o nome do paciente:");
        } while (nomePaciente == null || nomePaciente.trim().isEmpty());

        String cpfPaciente;
        do {
            cpfPaciente = JOptionPane.showInputDialog("Digite o CPF do paciente:");
            if (!Paciente.isCpfValido(cpfPaciente)) {
                int opcao = JOptionPane.showConfirmDialog(null, "CPF já cadastrado! Deseja sair?", "Erro", JOptionPane.YES_NO_OPTION);
                if (opcao == JOptionPane.YES_OPTION) return;
            }
        } while (!Paciente.isCpfValido(cpfPaciente));

        LocalDate dataNascimentoPaciente = null;
        while (dataNascimentoPaciente == null) {
            try {
                String input = JOptionPane.showInputDialog("Digite a data de nascimento (dd-MM-yyyy):");
                dataNascimentoPaciente = LocalDate.parse(input, formato);
                if (dataNascimentoPaciente.isAfter(LocalDate.now())) {
                    JOptionPane.showMessageDialog(null, "A data de nascimento não pode estar no futuro.");
                    dataNascimentoPaciente = null;
                }
            } catch (DateTimeParseException e) {
                JOptionPane.showMessageDialog(null, "Formato inválido! Use dd-MM-yyyy.");
            }
        }

        Paciente.adicionarPaciente(nomePaciente, cpfPaciente, dataNascimentoPaciente);
        JOptionPane.showMessageDialog(null, "Paciente cadastrado com sucesso!");
    }

    public static void interfaceAdicionarMedico() {
        String nomeMedico;
        do {
            nomeMedico = JOptionPane.showInputDialog("Digite o nome do médico:");
        } while (nomeMedico == null || nomeMedico.trim().isEmpty());

        String cpfMedico;
        do {
            cpfMedico = JOptionPane.showInputDialog("Digite o CPF do médico:");
            if (!Paciente.isCpfValido(cpfMedico)) {
                int opcao = JOptionPane.showConfirmDialog(null, "CPF já cadastrado! Deseja sair?", "Erro", JOptionPane.YES_NO_OPTION);
                if (opcao == JOptionPane.YES_OPTION) return;
            }
        } while (!Paciente.isCpfValido(cpfMedico));

        LocalDate dataNascimentoMedico = null;
        while (dataNascimentoMedico == null) {
            try {
                String input = JOptionPane.showInputDialog("Digite a data de nascimento (dd-MM-yyyy):");
                dataNascimentoMedico = LocalDate.parse(input, formato);
                if (dataNascimentoMedico.isAfter(LocalDate.now())) {
                    JOptionPane.showMessageDialog(null, "A data de nascimento não pode estar no futuro.");
                    dataNascimentoMedico = null;
                }
            } catch (DateTimeParseException e) {
                JOptionPane.showMessageDialog(null, "Formato inválido! Use dd-MM-yyyy.");
            }
        }

        String crmMedico = JOptionPane.showInputDialog("Digite o CRM do médico:");
        String especialidadeMedico;
        do {
            especialidadeMedico = JOptionPane.showInputDialog("Digite a especialidade do médico:");
        } while (especialidadeMedico == null || especialidadeMedico.trim().isEmpty());

        Medico.adicionarMedico(nomeMedico, cpfMedico, dataNascimentoMedico, Integer.parseInt(crmMedico), especialidadeMedico);
        JOptionPane.showMessageDialog(null, "Médico cadastrado com sucesso!");
    }
}
