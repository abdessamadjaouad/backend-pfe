package  ma.zs.GestionAgents.ws.dto.DemandeAbsence;

import ma.zs.GestionAgents.zynerator.audit.Log;
import ma.zs.GestionAgents.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;



import ma.zs.GestionAgents.ws.dto.Poste.RaisonDemandeAbsenceDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class DemandeAbsenceDto  extends AuditBaseDto {

    private String ref  ;
    private String contenu  ;

    private RaisonDemandeAbsenceDto raisonDemandeAbsence ;



    public DemandeAbsenceDto(){
        super();
    }



    @Log
    public String getRef(){
        return this.ref;
    }
    public void setRef(String ref){
        this.ref = ref;
    }

    @Log
    public String getContenu(){
        return this.contenu;
    }
    public void setContenu(String contenu){
        this.contenu = contenu;
    }


    public RaisonDemandeAbsenceDto getRaisonDemandeAbsence(){
        return this.raisonDemandeAbsence;
    }

    public void setRaisonDemandeAbsence(RaisonDemandeAbsenceDto raisonDemandeAbsence){
        this.raisonDemandeAbsence = raisonDemandeAbsence;
    }






}
