package servicos;

import entidades.Medico;
import entidades.Paciente;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class Exame {
    private static Integer totalExames = 0;
    private static Map<Integer, Exame> examesPorId = new HashMap<>();
    private final Integer idExame;
    private String tipo;
    private LocalDate dataPrescricao;
    private LocalDate dataRealizacao;
    private String resultado;
    private BigDecimal custo;
    private Exame(String tipo, LocalDate dataPrescricao, LocalDate dataRealizacao, BigDecimal custo) {
        this.idExame = totalExames++;
        this.tipo = tipo;
        this.dataPrescricao = dataPrescricao;
        this.dataRealizacao = dataRealizacao;
        this.custo = custo;
    }
    public static Exame criarExame(String tipo, LocalDate dataPrescricao, LocalDate dataRealizacao, BigDecimal custo) {
        Exame e = new Exame(tipo, dataPrescricao, dataRealizacao, custo);
        examesPorId.put(e.getIdExame(), e);  // Adiciona a consulta ao HashMap
        return e;
    }
    public static Exame buscarExamePorId(Integer idExame) {
        return examesPorId.getOrDefault(idExame, null);  // Retorna a consulta associada ao id
    }

    public String toString() {
        return "\n\t\t[idExame: " + idExame + "]"
                + "\n\t\t[Tipo: " + tipo + "]"
                + "\n\t\t[Data Prescricao: " + dataPrescricao + "]"
                + "\n\t\t[Data Realizacao: " + dataRealizacao + "]"
                + "\n\t\t[Resultado: " + resultado + "]";
    }

    public int getIdExame() {
        return idExame;
    }
    public String getTipo() {
        return tipo;
    }
    public LocalDate getDataPrescricao() {
        return dataPrescricao;
    }
    public LocalDate getDataRealizacao() {
        return dataRealizacao;
    }
    public BigDecimal getCusto() {
        return custo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public void setCusto(BigDecimal custo) {
        this.custo = custo;
    }
    public void setDataRealizacao(LocalDate dataRealizacao) {
        this.dataRealizacao = dataRealizacao;
    }
}
