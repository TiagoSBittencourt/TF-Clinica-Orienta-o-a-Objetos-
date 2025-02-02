package entidades;

import servicos.Consulta;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Medico extends Pessoa {
    private static int proximoId = 0;
    private Integer crm;
    private String especialidade;

    private static final Map<Integer, Medico> medicosPorId = new HashMap<>();
    private static final Map<String, Medico> medicosPorCpf = new HashMap<>();
    private static final Map<Integer, Medico> medicosPorCrm = new HashMap<>();
    private static final Map<String, Medico> medicosPorNome = new HashMap<>();

    private static final Map<Medico, List<Integer>> medicoPorConsulta = new HashMap<>(); // medico -> idConsulta



    private Medico(String nome, String cpf, LocalDate dataNascimento, Integer crm, String especialidade) {
        super(nome, cpf, dataNascimento);
        this.crm = crm;
        this.especialidade = especialidade;
    }
    public static void adicionarMedico(String nome, String cpf, LocalDate dataNascimento, Integer crm, String especialidade) {
        Medico m = new Medico(nome, cpf, dataNascimento, crm, especialidade);

        medicosPorId.put(m.getId(), m);
        medicosPorCpf.put(m.getCpf(), m);
        medicosPorCrm.put(m.getCrm(), m);
        medicosPorNome.put(m.getNome(), m);
        medicoPorConsulta.put(m, new ArrayList<>());
    }
    @Override
    public String toString() {
        return super.toString() + "\nCRM: " + this.crm
                                + "\nEspecialidade: " + this.especialidade;
    }

    public static void relacionarMedicoConsulta(Medico m, Integer idConsulta) {
        if (m == null || Consulta.buscarConsultaPorId(idConsulta) == null) {
            throw new IllegalArgumentException("Medico ou ID da consulta incorreto.");
        }

        medicoPorConsulta.putIfAbsent(m, new ArrayList<>()); // Evita nullPointer
        medicoPorConsulta.get(m).add(idConsulta); // Adiciona o id da consulta ao objeto medico respectivo
    }



    public static Medico buscarPorId(int id) {
        return medicosPorId.get(id);
    }

    public static Medico buscarPorCpf(String cpf) {
        return medicosPorCpf.get(cpf);
    }

    public static Medico buscarPorCrm(int crm) {
        return medicosPorCrm.get(crm);
    }
    // TODO: Faca isso ser um pesquisa por semelhanca inicial
    public static List<Medico> buscarPorNome(String nome) {
        return new ArrayList<>(medicosPorNome.values());
    }


    public Integer getCrm() {
        return crm;
    }
    public void setCrm(Integer crm) {
        this.crm = crm;
    }

    public String getEspecialidade() {
        return especialidade;
    }
    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }
}