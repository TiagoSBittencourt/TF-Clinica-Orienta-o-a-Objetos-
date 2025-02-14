package entidades;

import servicos.Consulta;
import java.time.LocalDate;
import java.util.List;

public interface PessoaInterface {
    Integer getId();
    String getNome();
    void setNome(String nome);
    String getCpf();
    LocalDate getDataNascimento();
    void setDataNascimento(LocalDate dataNascimento);
    List<Consulta> getHistoricoMedico();
    void adicionaHistoricoMedico(Consulta historicoMedico);
}