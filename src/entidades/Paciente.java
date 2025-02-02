package entidades;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Paciente extends Pessoa {
    private static int proximoId = 0;

    private static final Map<Integer, Paciente> pacientesPorId = new HashMap<>();
    private static final Map<String, Paciente> pacientesPorCpf = new HashMap<>();
    private static final List<Paciente> pacientesPorNome = new ArrayList<>();

    private Paciente(String nome, String cpf, LocalDate dataNascimento) {
        super(nome, cpf, dataNascimento);
    }
    public static void adicionarPaciente(String nome, String cpf, LocalDate dataNascimento) {
        Paciente p = new Paciente(nome, cpf, dataNascimento);

        pacientesPorId.put(p.getId(), p);
        pacientesPorCpf.put(p.getCpf(), p);
        pacientesPorNome.add(p);
    }
    public static Paciente buscarPorId(int id) {
        return pacientesPorId.get(id);
    }

    public static Paciente buscarPorCpf(String cpf) {
        return pacientesPorCpf.get(cpf);
    }

    // TODO: Faca isso ser um pesquisa por semelhanca inicial
    public static List<Paciente> buscarPorNome(String nome) {
        List<Paciente> resultado = new ArrayList<>();
        for (Paciente m : pacientesPorNome) {
            if (m.getNome().equalsIgnoreCase(nome)) {
                resultado.add(m);
            }
        }
        return resultado;
    }

}
