package servicos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Prescricao {
    private static Integer totalPrescricao = 0;
    private static Map<Integer, Prescricao> prescricaoPorId = new HashMap<>();
    private final Integer idPrescricao;
    private Integer idConsultaAssociada;
    private ArrayList<Exame> examesPrescritos;
    private ArrayList<String> medicamentosPrescritos;
    private LocalDate dataValidade;
    private Prescricao(int idConsultaAssociada, ArrayList<Exame> examesPrescritos, ArrayList<String> medicamentosPrescritos, LocalDate dataValidade) {
        this.idPrescricao = totalPrescricao++;
        this.idConsultaAssociada = idConsultaAssociada;
        this.examesPrescritos = examesPrescritos;
        this.medicamentosPrescritos = medicamentosPrescritos;
        this.dataValidade = dataValidade;
    }
    private Prescricao(int idConsultaAssociada, ArrayList<Exame> examesPrescritos, ArrayList<String> medicamentosPrescritos) {
        this.idPrescricao = totalPrescricao++;
        this.idConsultaAssociada = idConsultaAssociada;
        this.examesPrescritos = examesPrescritos;
        this.medicamentosPrescritos = medicamentosPrescritos;
        this.dataValidade = LocalDate.now().plusDays(30);
    }
    private Prescricao(int idConsultaAssociada) {
        this.idPrescricao = totalPrescricao++;
        this.idConsultaAssociada = idConsultaAssociada;
        this.examesPrescritos = new ArrayList<>();
        this.medicamentosPrescritos = new ArrayList<>();
        this.dataValidade = LocalDate.now().plusDays(30);
    }
    public static Prescricao criarPrescricao(Integer idConsultaAssociada) {
        Prescricao p = new Prescricao(idConsultaAssociada);
        prescricaoPorId.put(p.getIdPrescricao(), p);  // Adiciona a consulta ao HashMap
        return p;
    }
    public static Prescricao criarPrescricao(Integer idConsultaAssociada, ArrayList<Exame> examesPrescritos, ArrayList<String> medicamentosPrescritos) {
        Prescricao p = new Prescricao(idConsultaAssociada, examesPrescritos, medicamentosPrescritos);
        prescricaoPorId.put(p.getIdPrescricao(), p);  // Adiciona a consulta ao HashMap
        return p;
    }
    public static Prescricao criarPrescricao(Integer idConsultaAssociada,ArrayList<Exame> examesPrescritos, ArrayList<String> medicamentosPrescritos, LocalDate dataValidade) {
        Prescricao p = new Prescricao(idConsultaAssociada, examesPrescritos, medicamentosPrescritos, dataValidade);
        prescricaoPorId.put(p.getIdPrescricao(), p);  // Adiciona a consulta ao HashMap
        return p;
    }

    public static Prescricao buscarPrescricaoPorId(Integer idConsultaAssociada) {
        return prescricaoPorId.get(idConsultaAssociada);
    }

    public void adicionarExame(String tipo, LocalDate dataPrescricao, LocalDate dataRealizacao, BigDecimal custo) {
        this.examesPrescritos.add(Exame.criarExame(tipo, dataPrescricao, dataRealizacao, custo));
    }
    public void adicionarExame(Exame exame) {
        this.examesPrescritos.add(exame);
    }
    public void adicionarMedicamento(String medicamento) {
        this.medicamentosPrescritos.add(medicamento);
    }

    public String toString() {
        StringBuilder examesStr = new StringBuilder();
        if (!examesPrescritos.isEmpty()) {
            for (Exame exame : examesPrescritos) {
                examesStr.append(exame.toString()).append("\n\t");
            }
        } else {
            examesStr.append("Nenhum exame prescrito");
        }

        return "\n\t(ID: " + idPrescricao + ")"
                + "\n\t(IDConsulta: " + idConsultaAssociada + ")"
                + "\n\t(Exames: " + examesStr.toString() + ")"
                + "\n\t(Medicamentos: " + medicamentosPrescritos + ")";
    }

    public int getIdPrescricao() {
        return idPrescricao;
    }
    public int getIdConsultaAssociada() {
        return idConsultaAssociada;
    }
    public ArrayList<Exame> getExamesPrescritos() {
        return examesPrescritos;
    }
    public ArrayList<String> getMedicamentosPrescritos() {
        return medicamentosPrescritos;
    }
    public LocalDate getDataValidade() {
        return dataValidade;
    }
    public void adicionarExamesPrescrito(Exame exame) {
        this.examesPrescritos.add(exame);
    }
    public void adicionarMedicamentosPrescrito(String medicamento) {
        this.medicamentosPrescritos.add(medicamento);
    }

}
