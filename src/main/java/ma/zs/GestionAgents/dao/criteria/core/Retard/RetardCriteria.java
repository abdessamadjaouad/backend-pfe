package  ma.zs.GestionAgents.dao.criteria.core.Retard;


import ma.zs.GestionAgents.dao.criteria.core.Agent.AgentCriteria;

import ma.zs.GestionAgents.zynerator.criteria.BaseCriteria;
import java.util.List;
import java.time.LocalDateTime;
import java.time.LocalDate;

public class RetardCriteria extends  BaseCriteria  {

    private String ref;
    private String refLike;
    private LocalDateTime dateRetart;
    private LocalDateTime dateRetartFrom;
    private LocalDateTime dateRetartTo;
    private String duree;
    private String dureeMin;
    private String dureeMax;

    private AgentCriteria agent ;
    private List<AgentCriteria> agents ;


    public RetardCriteria(){}

    public String getRef(){
        return this.ref;
    }
    public void setRef(String ref){
        this.ref = ref;
    }
    public String getRefLike(){
        return this.refLike;
    }
    public void setRefLike(String refLike){
        this.refLike = refLike;
    }

    public LocalDateTime getDateRetart(){
        return this.dateRetart;
    }
    public void setDateRetart(LocalDateTime dateRetart){
        this.dateRetart = dateRetart;
    }
    public LocalDateTime getDateRetartFrom(){
        return this.dateRetartFrom;
    }
    public void setDateRetartFrom(LocalDateTime dateRetartFrom){
        this.dateRetartFrom = dateRetartFrom;
    }
    public LocalDateTime getDateRetartTo(){
        return this.dateRetartTo;
    }
    public void setDateRetartTo(LocalDateTime dateRetartTo){
        this.dateRetartTo = dateRetartTo;
    }
    public String getDuree(){
        return this.duree;
    }
    public void setDuree(String duree){
        this.duree = duree;
    }   
    public String getDureeMin(){
        return this.dureeMin;
    }
    public void setDureeMin(String dureeMin){
        this.dureeMin = dureeMin;
    }
    public String getDureeMax(){
        return this.dureeMax;
    }
    public void setDureeMax(String dureeMax){
        this.dureeMax = dureeMax;
    }
      

    public AgentCriteria getAgent(){
        return this.agent;
    }

    public void setAgent(AgentCriteria agent){
        this.agent = agent;
    }
    public List<AgentCriteria> getAgents(){
        return this.agents;
    }

    public void setAgents(List<AgentCriteria> agents){
        this.agents = agents;
    }
}
