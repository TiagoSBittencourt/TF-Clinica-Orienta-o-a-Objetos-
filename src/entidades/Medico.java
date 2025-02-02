package entidades;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Medico extends Pessoa {
    private static int proximoId = 0;
    private Integer id;
    private Integer crm;
    private String especialidade;

    private static final Map<Integer, Medico> medicosPorId = new HashMap<>();
    private static final Map<String, Medico> medicosPorCpf = new HashMap<>();
    private static final Map<Integer, Medico> medicosPorCrm = new HashMap<>();
    private static final List<Medico> medicosPorNome = new ArrayList<>();


    private Medico(String nome, String cpf, LocalDate dataNascimento, Integer crm, String especialidade) {
        super(nome, cpf, dataNascimento);
        this.crm = crm;
        this.especialidade = especialidade;
    }
    public static void adicionarMedico(String nome, String cpf, LocalDate dataNascimento, Integer crm, String especialidade) {
        Medico m = new Medico(nome, cpf, dataNascimento, crm, especialidade);
        m.setId(proximoId++); // ID unico

        medicosPorId.put(m.getId(), m);
        medicosPorCpf.put(m.getCpf(), m);
        medicosPorCrm.put(m.getCrm(), m);
        medicosPorNome.add(m);
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
        List<Medico> resultado = new ArrayList<>();
        for (Medico m : medicosPorNome) {
            if (m.getNome().equalsIgnoreCase(nome)) {
                resultado.add(m);
            }
        }
        return resultado;
    }


    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
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