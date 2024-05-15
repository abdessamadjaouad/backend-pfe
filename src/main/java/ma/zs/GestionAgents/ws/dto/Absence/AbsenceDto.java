package  ma.zs.GestionAgents.ws.dto.Absence;

import ma.zs.GestionAgents.zynerator.audit.Log;
import ma.zs.GestionAgents.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonFormat;




@JsonInclude(JsonInclude.Include.NON_NULL)
public class AbsenceDto  extends AuditBaseDto {

    private String ref  ;
    private String dateDebut ;
    private String dateFin ;
    private String typeAbsence  ;




    public AbsenceDto(){
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

    @Log
    public String getTypeAbsence(){
        return this.typeAbsence;
    }
    public void setTypeAbsence(String typeAbsence){
        this.typeAbsence = typeAbsence;
    }








}
