import entidades.Medico;
import entidades.Paciente;
import servicos.Consulta;
import servicos.Exame;
import servicos.Prescricao;

import javax.swing.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class Clinica {
    private static final DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private static final DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm");

    public static void main(String[] args) {
        exibirMenuPrincipal();
    }

    private static void exibirMenuPrincipal() {
        String[] opcoes = {"Nova Pessoa", "Nova Consulta", "Pesquisar", "Sair"};
        int escolha;
        do {
            escolha = JOptionPane.showOptionDialog(null, "Escolha uma opção:", "Clínica",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[0]);

            switch (escolha) {
                case 0 -> adicionarEntidade();
                case 1 -> criarConsulta();
                case 2 -> pesquisarEntidade();
                case 3 -> System.exit(0);
            }
        } while (true);
    }

    private static void adicionarEntidade() {
        String[] opcoes = {"Paciente", "Médico", "Voltar"};
        int escolha = JOptionPane.showOptionDialog(null, "Escolha uma entidade para adicionar:", "Adicionar Entidade",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[0]);

        if (escolha == 0) adicionarPaciente();
        else if (escolha == 1) adicionarMedico();
    }

    private static void adicionarPaciente() {
        String nome = JOptionPane.showInputDialog("Digite o nome do paciente:");
        String cpf = JOptionPane.showInputDialog("Digite o CPF:");
        while (!Paciente.isCpfValido(cpf)) {
            cpf = JOptionPane.showInputDialog("CPF inválido ou já cadastrado! Digite novamente ou 's' para sair:");
            if (cpf.equalsIgnoreCase("s")) return;
        }

        LocalDate dataNascimento = obterDataNascimento();
        if (dataNascimento != null) {
            Paciente.adicionarPaciente(nome, cpf, dataNascimento);
            JOptionPane.showMessageDialog(null, "Paciente cadastrado com sucesso!");
        }
    }

    private static void adicionarMedico() {
        String nome = JOptionPane.showInputDialog("Digite o nome do médico:");
        String cpf = JOptionPane.showInputDialog("Digite o CPF:");
        while (!Medico.isCpfValido(cpf)) {
            cpf = JOptionPane.showInputDialog("CPF inválido ou já cadastrado! Digite novamente ou 's' para sair:");
            if (cpf.equalsIgnoreCase("s")) return;
        }

        LocalDate dataNascimento = obterDataNascimento();
        if (dataNascimento != null) {
            String crm = JOptionPane.showInputDialog("Digite o CRM:");
            String especialidade = JOptionPane.showInputDialog("Digite a especialidade:");
            Medico.adicionarMedico(nome, cpf, dataNascimento, Integer.parseInt(crm), especialidade);
            JOptionPane.showMessageDialog(null, "Médico cadastrado com sucesso!");
        }
    }

    private static LocalDate obterDataNascimento() {
        while (true) {
            try {
                String input = JOptionPane.showInputDialog("Digite a data de nascimento (dd-MM-yyyy):");
                LocalDate data = LocalDate.parse(input, formatoData);
                if (data.isAfter(LocalDate.now())) {
                    JOptionPane.showMessageDialog(null, "A data não pode estar no futuro.", "Erro", JOptionPane.ERROR_MESSAGE);
                } else {
                    return data;
                }
            } catch (DateTimeParseException e) {
                JOptionPane.showMessageDialog(null, "Formato inválido! Use dd-MM-yyyy.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }



    private static void criarConsulta() {
        try {
            // Buscar Paciente
            Paciente paciente = null;
            while (paciente == null) {
                String pacienteCpf = JOptionPane.showInputDialog("Digite o CPF do paciente:");
                paciente = Paciente.buscarPorCpf(pacienteCpf);
                if (paciente == null) {
                    JOptionPane.showMessageDialog(null, "Paciente não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            // Buscar Médico
            Medico medico = null;
            while (medico == null) {
                String medicoCpf = JOptionPane.showInputDialog("Digite o CPF do médico:");
                medico = Medico.buscarPorCpf(medicoCpf);
                if (medico == null) {
                    JOptionPane.showMessageDialog(null, "Médico não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            // Obter Data
            String dataInput = JOptionPane.showInputDialog("Digite a data da consulta (dd-MM-yyyy):");
            LocalDate data = LocalDate.parse(dataInput, formatoData);

            // Obter Hora
            String horaInput = JOptionPane.showInputDialog("Digite o horário de início da consulta (HH:mm):");
            LocalTime horaInicio = LocalTime.parse(horaInput, formatoHora);

            // Obter Duração
            int duracaoMinutos = Integer.parseInt(JOptionPane.showInputDialog("Digite a duração da consulta em minutos:"));

            // Definir Status
            Consulta.StatusConsulta status = Consulta.StatusConsulta.AGENDADA;

            // Criar a consulta (sem prescrição inicialmente)
            Consulta consulta = Consulta.agendarConsulta(data, horaInicio, duracaoMinutos, status, paciente, medico);
            int idConsulta = consulta.getIdConsulta(); // ID gerado para a consulta

            // Perguntar se deseja adicionar prescrição
            int adicionarPrescricao = JOptionPane.showConfirmDialog(null, "Deseja adicionar uma prescrição?", "Opção", JOptionPane.YES_NO_OPTION);
            Prescricao prescricao = null;

            if (adicionarPrescricao == JOptionPane.YES_OPTION) {
                prescricao = criarPrescricao(idConsulta);
            }

            // Adicionar valor da consulta
            BigDecimal valorConsulta = new BigDecimal(JOptionPane.showInputDialog("Digite o valor da consulta:"));
            consulta.setValor(valorConsulta);

            // Atualizar consulta com a prescrição, se existir
            if (prescricao != null) {
                consulta.setPrescricao(prescricao);
            }

            JOptionPane.showMessageDialog(null, "Consulta agendada com sucesso!\n" + consulta);

        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(null, "Erro no formato de data ou hora!", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Erro: entrada numérica inválida!", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro inesperado: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static Prescricao criarPrescricao(Integer idConsulta) {
        ArrayList<Exame> examesPrescritos = new ArrayList<>();
        ArrayList<String> medicamentosPrescritos = new ArrayList<>();

        // Adicionar exames
        int adicionarExames = JOptionPane.showConfirmDialog(null, "Deseja adicionar exames à prescrição?", "Opção", JOptionPane.YES_NO_OPTION);
        if (adicionarExames == JOptionPane.YES_OPTION) {
            while (true) {
                String nomeExame = JOptionPane.showInputDialog("Digite o tipo de exame (ou deixe em branco para terminar):");
                if (nomeExame == null || nomeExame.isBlank()) break;

                String dataPrescricaoInput = JOptionPane.showInputDialog("Digite a data da prescrição (dd-MM-yyyy):");
                LocalDate dataPrescricao = LocalDate.parse(dataPrescricaoInput, formatoData);

                String dataRealizacaoInput = JOptionPane.showInputDialog("Digite a data de realização (dd-MM-yyyy, opcional):");
                LocalDate dataRealizacao = dataRealizacaoInput.isBlank() ? null : LocalDate.parse(dataRealizacaoInput, formatoData);

                BigDecimal custo = new BigDecimal(JOptionPane.showInputDialog("Digite o custo do exame:"));

                examesPrescritos.add(Exame.criarExame(nomeExame, dataPrescricao, dataRealizacao, custo));
            }
        }

        // Adicionar medicamentos
        int adicionarMedicamentos = JOptionPane.showConfirmDialog(null, "Deseja adicionar medicamentos à prescrição?", "Opção", JOptionPane.YES_NO_OPTION);
        if (adicionarMedicamentos == JOptionPane.YES_OPTION) {
            while (true) {
                String medicamento = JOptionPane.showInputDialog("Digite o nome do medicamento (ou deixe em branco para terminar):");
                if (medicamento == null || medicamento.isBlank()) break;
                medicamentosPrescritos.add(medicamento);
            }
        }

        // Adicionar data de validade
        int adicionarValidade = JOptionPane.showConfirmDialog(null, "Deseja definir uma data de validade para a prescrição?", "Opção", JOptionPane.YES_NO_OPTION);
        if (adicionarValidade == JOptionPane.YES_OPTION) {
            String dataValidadeInput = JOptionPane.showInputDialog("Digite a data de validade da prescrição (dd-MM-yyyy):");
            LocalDate dataValidade = LocalDate.parse(dataValidadeInput, formatoData);
            return Prescricao.criarPrescricao(idConsulta, examesPrescritos, medicamentosPrescritos, dataValidade);
        }

        if (!examesPrescritos.isEmpty() || !medicamentosPrescritos.isEmpty()) {
            return Prescricao.criarPrescricao(idConsulta, examesPrescritos, medicamentosPrescritos);
        }

        return Prescricao.criarPrescricao(idConsulta);
    }


    private static void pesquisarEntidade() {
        String[] opcoes = {"Paciente", "Médico", "Consulta", "Voltar"};
        int escolha = JOptionPane.showOptionDialog(null, "Pesquisar por:", "Pesquisa",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[0]);

        if (escolha == 0) pesquisarPaciente();
        else if (escolha == 1) pesquisarMedico();
        else if (escolha == 2) pesquisarConsulta();
    }

    private static void exibirListaPesquisa(List<?> lista, String tipo) {
        if (lista == null || lista.isEmpty()) {
            JOptionPane.showMessageDialog(null, tipo + " não encontrado.", "Resultado da Pesquisa", JOptionPane.INFORMATION_MESSAGE);
        } else {
            StringBuilder mensagem = new StringBuilder(tipo + "(s) encontrado(s):\n");
            for (Object obj : lista) {
                mensagem.append(obj.toString()).append("\n");
            }
            JOptionPane.showMessageDialog(null, mensagem.toString(), "Resultado da Pesquisa", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private static void pesquisarPaciente() {
        String[] opcoes = {"ID", "CPF", "Nome", "Voltar"};
        int escolha = JOptionPane.showOptionDialog(null, "Pesquisar paciente por:", "Pesquisa Paciente",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[0]);

        Object resultado = switch (escolha) {
            case 0 -> Paciente.buscarPorId(Integer.parseInt(JOptionPane.showInputDialog("Digite o ID:")));
            case 1 -> Paciente.buscarPorCpf(JOptionPane.showInputDialog("Digite o CPF:"));
            case 2 -> Paciente.buscarPorNome(JOptionPane.showInputDialog("Digite o Nome:"));
            default -> null;
        };

        if (resultado instanceof List<?> lista) {
            exibirListaPesquisa(lista, "Paciente");
        } else {
            exibirResultadoPesquisa(resultado != null ? resultado.toString() : "Paciente não encontrado.", "Paciente");
        }
    }

    private static void pesquisarMedico() {
        String[] opcoes = {"ID", "CPF", "Nome", "Voltar"};
        int escolha = JOptionPane.showOptionDialog(null, "Pesquisar médico por:", "Pesquisa Médico",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[0]);

        Object resultado = switch (escolha) {
            case 0 -> Medico.buscarPorId(Integer.parseInt(JOptionPane.showInputDialog("Digite o ID:")));
            case 1 -> Medico.buscarPorCpf(JOptionPane.showInputDialog("Digite o CPF:"));
            case 2 -> Medico.buscarPorNome(JOptionPane.showInputDialog("Digite o Nome:"));
            default -> null;
        };

        if (resultado instanceof List<?> lista) {
            exibirListaPesquisa(lista, "Médico");
        } else {
            exibirResultadoPesquisa(resultado != null ? resultado.toString() : "Médico não encontrado.", "Médico");
        }
    }

    private static void pesquisarConsulta() {
        String[] opcoes = {"Listar Todas", "Voltar"};
        int escolha = JOptionPane.showOptionDialog(null, "Pesquisar consulta:", "Pesquisa Consulta",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[0]);

        Object resultado = switch (escolha) {
            case 0 -> Consulta.getConsultas();
            default -> null;
        };

        if (resultado instanceof List<?> lista) {
            exibirListaPesquisa(lista, "Consulta");
        } else {
            exibirResultadoPesquisa("Nenhuma consulta encontrada.", "Consulta");
        }
    }

    private static void exibirResultadoPesquisa(String resultado, String tipo) {
        if (resultado != null && !resultado.isEmpty()) {
            JOptionPane.showMessageDialog(null, tipo + " encontrado:\n" + resultado);
        } else {
            JOptionPane.showMessageDialog(null, tipo + " não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
