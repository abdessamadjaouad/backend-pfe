package  ma.zs.GestionAgents.ws.dto.Retard;

import ma.zs.GestionAgents.zynerator.audit.Log;
import ma.zs.GestionAgents.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonFormat;


import ma.zs.GestionAgents.ws.dto.Agent.AgentDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class RetardDto  extends AuditBaseDto {

    private String ref  ;
    private String dateRetart ;
    private Long duree  ;

    private AgentDto agent ;



    public RetardDto(){
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
    public String getDateRetart(){
        return this.dateRetart;
    }
    public void setDateRetart(String dateRetart){
        this.dateRetart = dateRetart;
    }

    @Log
    public Long getDuree(){
        return this.duree;
    }
    public void setDuree(Long duree){
        this.duree = duree;
    }


    public AgentDto getAgent(){
        return this.agent;
    }

    public void setAgent(AgentDto agent){
        this.agent = agent;
    }






}
