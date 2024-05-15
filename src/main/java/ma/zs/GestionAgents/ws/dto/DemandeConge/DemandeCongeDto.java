package  ma.zs.GestionAgents.ws.dto.DemandeConge;

import ma.zs.GestionAgents.zynerator.audit.Log;
import ma.zs.GestionAgents.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonFormat;


import ma.zs.GestionAgents.ws.dto.Poste.RaisonDemandeCongeDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class DemandeCongeDto  extends AuditBaseDto {

    private String ref  ;
    private String dateDebut ;
    private String dateFin ;

    private RaisonDemandeCongeDto raisonDemandeConge ;



    public DemandeCongeDto(){
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getDateDebut(){
        return this.dateDebut;
    }
    public void setDateDebut(String dateDebut){
        this.dateDebut = dateDebut;
    }

    @Log
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getDateFin(){
        return this.dateFin;
    }
    public void setDateFin(String dateFin){
        this.dateFin = dateFin;
    }


    public RaisonDemandeCongeDto getRaisonDemandeConge(){
        return this.raisonDemandeConge;
    }

    public void setRaisonDemandeConge(RaisonDemandeCongeDto raisonDemandeConge){
        this.raisonDemandeConge = raisonDemandeConge;
    }






}
