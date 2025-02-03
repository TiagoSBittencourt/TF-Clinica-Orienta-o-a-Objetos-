package entidades;

import servicos.Consulta;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Paciente extends Pessoa {
    private static int proximoId = 0;

    private static final Map<Integer, Paciente> pacientesPorId = new HashMap<>();
    private static final Map<String, Paciente> pacientesPorCpf = new HashMap<>();
    private static final Map<String, Paciente> pacientesPorNome = new HashMap<>();

    private static final Map<Paciente, List<Consulta>> consultaPorPaciente = new HashMap<>();

    private BigDecimal extratoConsultas;

    private Paciente(String nome, String cpf, LocalDate dataNascimento) {
        super(nome, cpf, dataNascimento);
        this.extratoConsultas = BigDecimal.ZERO;
    }
    public static void adicionarPaciente(String nome, String cpf, LocalDate dataNascimento) {
        Paciente p = new Paciente(nome, cpf, dataNascimento);

        pacientesPorId.put(p.getId(), p);
        pacientesPorCpf.put(p.getCpf(), p);
        pacientesPorNome.put(p.getNome(), p);
    }

    public static void relacionarPacienteConsulta(Paciente p, Consulta c) {
        if (p == null || c == null) {
            throw new IllegalArgumentException("Paciente ou ID da consulta incorreto");
        }

        if (p.getExtratoConsultas().compareTo(BigDecimal.ZERO)>= 0) {
            p.adicionarAoExtrato(c.getValorConsulta()); // Adiciona o valor da Consulta ao Extrato do Paciente
        }
        else {
            throw new IllegalArgumentException("Paciente endividado");
        }
        consultaPorPaciente.putIfAbsent(p, new ArrayList<>()); // Garante lista inicial
        consultaPorPaciente.get(p).add(c); // Adiciona o id da consulta ao objeto paciente respectivo
    }

    public Boolean ifConsultaNoDia(LocalDate data) {
        List<Consulta> consultas = consultaPorPaciente.getOrDefault(this, new ArrayList<Consulta>());
        for (Consulta consulta : consultas) {
            if (consulta.getData().equals(data)) {
                return true;
            }
        }
        return false;
    }

    public BigDecimal getExtratoConsultas() {
        return extratoConsultas;
    }
    public void adicionarAoExtrato(BigDecimal valor) {
        this.extratoConsultas = this.extratoConsultas.add(valor);
    }
    public static Paciente buscarPorId(int id) {
        return pacientesPorId.getOrDefault(id, null);
    }

    public static Paciente buscarPorCpf(String cpf) {
        return pacientesPorCpf.getOrDefault(cpf, null);
    }

    // TODO: Faca isso ser um pesquisa por semelhanca inicial
    public static List<Paciente> buscarPorNome(String nome) {
        return new ArrayList<>(pacientesPorNome.values());
    }

}
