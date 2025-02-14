package entidades;

import servicos.Consulta;
import java.util.List;


public interface MedicoInterface {
    Integer getCrm();
    void setCrm(int crm);
    String getEspecialidade();
    void setEspecialidade(String especialidade);

}
