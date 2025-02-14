package entidades;

import servicos.Consulta;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface PacienteInterface {
    BigDecimal getExtratoConsultas();
    void adicionarAoExtrato(BigDecimal valor);

    Boolean ifConsultaNoDia(LocalDate data);
}