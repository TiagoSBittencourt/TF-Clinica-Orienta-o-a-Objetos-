package entidades;

import servicos.Consulta;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Medico extends Pessoa implements MedicoInterface{
    private static int proximoId = 0;
    private Integer crm;
    private String especialidade;

    private static final Map<Integer, Medico> medicosPorId = new HashMap<>();
    private static final Map<String, Medico> medicosPorCpf = new HashMap<>();
    private static final Map<Integer, Medico> medicosPorCrm = new HashMap<>();
    private static final Map<String, Medico> medicosPorNome = new HashMap<>();

    private static final Map<Medico, List<Consulta>> consultaPormedico = new HashMap<>(); // medico -> Consulta



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
        consultaPormedico.put(m, new ArrayList<>());
    }
    @Override
    public String toString() {
        return super.toString() + "\nCRM: " + this.crm
                                + "\nEspecialidade: " + this.especialidade;
    }


    public static void relacionarMedicoConsulta(Medico m, Consulta c) {
        if (m == null || c == null) {
            throw new IllegalArgumentException("Medico ou ID da consulta incorreto.");
        }
        if (c.getMedicoAssociado() != null) {
            throw  new IllegalArgumentException("Consulta ja possui medico relacionado");
        }
        if (c.getPacienteAssociado() != null) {
            throw new IllegalArgumentException("Consulta ja possui paciente relacionado");
        }

        consultaPormedico.putIfAbsent(m, new ArrayList<>()); // Evita nullPointer
        consultaPormedico.get(m).add(c); // Adiciona o id da consulta ao objeto medico respectivo
    }



    public static Medico buscarPorId(int id) {
        return medicosPorId.getOrDefault(id, null);
    }

    public static Medico buscarPorCpf(String cpf) {
        return medicosPorCpf.getOrDefault(cpf, null);
    }

    public static Medico buscarPorCrm(int crm) {
        return medicosPorCrm.getOrDefault(crm, null);
    }

    public static List<Medico> buscarPorNome(String nome) {
        List<Medico> resultado = new ArrayList<>();

        for (String key : medicosPorNome.keySet()) {
            if (key.toLowerCase().startsWith(nome.toLowerCase())) {
                resultado.add(medicosPorNome.get(key));
            }
        }
        return resultado;
    }


    public Integer getCrm() {
        return this.crm;
    }
    public void setCrm(int crm) {
        this.crm = crm;
    }

    public String getEspecialidade() {
        return this.especialidade;
    }
    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }
}