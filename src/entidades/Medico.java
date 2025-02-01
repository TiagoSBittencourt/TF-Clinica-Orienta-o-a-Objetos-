package entidades;

import java.time.LocalDate;

public class Medico extends Pessoa{
    private Integer crm;
    private String especialidade;
    public Medico(String nome, String cpf, LocalDate dataNascimento, Integer crm, String especialidade) {
        super(nome, cpf, dataNascimento);
        this.crm = crm;
        this.especialidade = especialidade;
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
