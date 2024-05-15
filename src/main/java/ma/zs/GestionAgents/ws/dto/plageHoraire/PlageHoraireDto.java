package  ma.zs.GestionAgents.ws.dto.plageHoraire;

import ma.zs.GestionAgents.zynerator.audit.Log;
import ma.zs.GestionAgents.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonFormat;




@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlageHoraireDto  extends AuditBaseDto {

    private String ref  ;
    private String typeHoraire  ;
    private String heureDebut ;
    private String heureFin ;




    public PlageHoraireDto(){
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
    public String getTypeHoraire(){
        return this.typeHoraire;
    }
    public void setTypeHoraire(String typeHoraire){
        this.typeHoraire = typeHoraire;
    }

    @Log
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getHeureDebut(){
        return this.heureDebut;
    }
    public void setHeureDebut(String heureDebut){
        this.heureDebut = heureDebut;
    }

    @Log
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getHeureFin(){
        return this.heureFin;
    }
    public void setHeureFin(String heureFin){
        this.heureFin = heureFin;
    }








}
