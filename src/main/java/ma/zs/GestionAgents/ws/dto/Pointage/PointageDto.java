package  ma.zs.GestionAgents.ws.dto.Pointage;

import ma.zs.GestionAgents.zynerator.audit.Log;
import ma.zs.GestionAgents.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonFormat;


import ma.zs.GestionAgents.ws.dto.Agent.AgentDto;
import ma.zs.GestionAgents.ws.dto.BioManager.BadgeNfcDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class PointageDto  extends AuditBaseDto {

    private String ref  ;
    private String heureEntree ;
    private String heureSortie ;
    private Boolean presence  ;
    private String pointageSens ;

    private AgentDto agent ;
    private BadgeNfcDto badgeNfc ;



    public PointageDto(){
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
    public String getHeureEntree(){
        return this.heureEntree;
    }
    public void setHeureEntree(String heureEntree){
        this.heureEntree = heureEntree;
    }

    @Log
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getHeureSortie(){
        return this.heureSortie;
    }
    public void setHeureSortie(String heureSortie){
        this.heureSortie = heureSortie;
    }

    @Log
    public Boolean getPresence(){
        return this.presence;
    }
    public void setPresence(Boolean presence){
        this.presence = presence;
    }

    @Log
    public String getPointageSens(){
        return this.pointageSens;
    }
    public void setPointageSens(String pointageSens){
        this.pointageSens = pointageSens;
    }


    public AgentDto getAgent(){
        return this.agent;
    }

    public void setAgent(AgentDto agent){
        this.agent = agent;
    }
    public BadgeNfcDto getBadgeNfc(){
        return this.badgeNfc;
    }

    public void setBadgeNfc(BadgeNfcDto badgeNfc){
        this.badgeNfc = badgeNfc;
    }






}
