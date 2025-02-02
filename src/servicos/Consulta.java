package servicos;

import entidades.Medico;
import entidades.Paciente;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class Consulta {

    private static Integer totalConsultas = 0;
    private static Map<Integer, Consulta> consultasPorId = new HashMap<>(); // HashMap para armazenar as consultas com o idConsulta como chave
    private final Integer idConsulta;
    private LocalDate data;
    private LocalTime horaInicio;
    private Integer duracaoMinutos;

    // Restringe Possibilidades de status
    public enum StatusConsulta {
        AGENDADA, REALIZADA, CANCELADA, PENDENTE;
    }

    private StatusConsulta status;
    private Paciente pacienteAssociado;
    private Medico medicoAssociado;
    private Prescricao prescricao;
    private BigDecimal valorConsulta;

    // Construtor privado
    private Consulta(LocalDate data, LocalTime horaInicio, Integer duracaoMinutos, StatusConsulta status, Paciente p, Medico m) {
        this.idConsulta = totalConsultas++;
        this.data = data;
        this.horaInicio = horaInicio;
        this.duracaoMinutos = duracaoMinutos;
        this.status = status;
        this.pacienteAssociado = p;
        this.medicoAssociado = m;
        this.prescricao = Prescricao.criarPrescricao(this.idConsulta);
        this.valorConsulta = BigDecimal.valueOf(100);

    }
    private Consulta(LocalDate data, LocalTime horaInicio, Integer duracaoMinutos, StatusConsulta status, Paciente p, Medico m, Prescricao ps, BigDecimal valorConsulta) {
        this.idConsulta = totalConsultas++;
        this.data = data;
        this.horaInicio = horaInicio;
        this.duracaoMinutos = duracaoMinutos;
        this.status = status;
        this.pacienteAssociado = p;
        this.medicoAssociado = m;
        this.prescricao = ps;
        this.valorConsulta = valorConsulta;
    }

    // Metodo para agendar consulta
    public static Consulta agendarConsulta(LocalDate data, LocalTime horaInicio, Integer duracaoMinutos, StatusConsulta status, Paciente p, Medico m, Prescricao ps, BigDecimal valorConsulta) {
        Consulta c = new Consulta(data, horaInicio, duracaoMinutos, status, p, m, ps, valorConsulta);
        consultasPorId.put(c.getIdConsulta(), c);  // Adiciona a consulta ao HashMap
        relacionarConsultaMedicoEPaciente(c.getIdConsulta(), p, m); // Relaciona individualmente o paciente e o medico a consulta TODO: Testar
        return c;
    }
    public static Consulta agendarConsulta(LocalDate data, LocalTime horaInicio, Integer duracaoMinutos, StatusConsulta status, Paciente p, Medico m) {
        Consulta c = new Consulta(data, horaInicio, duracaoMinutos, status, p, m);
        consultasPorId.put(c.getIdConsulta(), c);  // Adiciona a consulta ao HashMap
        relacionarConsultaMedicoEPaciente(c.getIdConsulta(), p, m); // Relaciona individualmente o paciente e o medico a consulta TODO: Testar
        return c;
    }

    private static void relacionarConsultaMedicoEPaciente(Integer idConsutlta, Paciente p, Medico m) {
        Paciente.relacionarPacienteConsulta(p, idConsutlta);
        Medico.relacionarMedicoConsulta(m, idConsutlta);
    }

    // Metodo para buscar consulta pelo id
    public static Consulta buscarConsultaPorId(Integer idConsulta) {
        return consultasPorId.getOrDefault(idConsulta, null);  // Retorna a consulta associada ao id
    }


    public String toString() {
        return "\nID: " + this.idConsulta
                + "\nData: " + this.data
                + "\nHora: " + this.horaInicio
                + "\nDuracao: " + this.duracaoMinutos
                + "\nStatus: " + this.status
                + "\nPaciente: " + this.pacienteAssociado.getNome()
                + "\nMedico: " + this.medicoAssociado.getNome()
                + "\nPrescricao: " + this.prescricao.toString()
                + "\nValor: " + this.valorConsulta;
    }

    public Integer getIdConsulta() {
        return idConsulta;
    }

    public LocalDate getData() {
        return data;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public Integer getDuracaoMinutos() {
        return duracaoMinutos;
    }

    public StatusConsulta getStatus() {
        return status;
    }

    public Paciente getPacienteAssociado() {
        return pacienteAssociado;
    }

    public Medico getMedicoAssociado() {
        return medicoAssociado;
    }

    public Prescricao getPrescricao() {
        return prescricao;
    }

    public BigDecimal getValorConsulta() {
        return valorConsulta;
    }

    public void setStatus(StatusConsulta status) {
        this.status = status;
    }
}