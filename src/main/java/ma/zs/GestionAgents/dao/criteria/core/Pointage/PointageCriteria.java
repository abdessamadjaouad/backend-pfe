package  ma.zs.GestionAgents.dao.criteria.core.Pointage;


import ma.zs.GestionAgents.dao.criteria.core.Agent.AgentCriteria;
import ma.zs.GestionAgents.dao.criteria.core.BioManager.BadgeNfcCriteria;

import ma.zs.GestionAgents.zynerator.criteria.BaseCriteria;
import java.util.List;
import java.time.LocalDateTime;
import java.time.LocalDate;

public class PointageCriteria extends  BaseCriteria  {

    private String ref;
    private String refLike;
    private LocalDateTime heureEntree;
    private LocalDateTime heureEntreeFrom;
    private LocalDateTime heureEntreeTo;
    private LocalDateTime heureSortie;
    private LocalDateTime heureSortieFrom;
    private LocalDateTime heureSortieTo;
    private Boolean presence;
    private String pointageSens;
    private String pointageSensMin;
    private String pointageSensMax;

    private AgentCriteria agent ;
    private List<AgentCriteria> agents ;
    private BadgeNfcCriteria badgeNfc ;
    private List<BadgeNfcCriteria> badgeNfcs ;


    public PointageCriteria(){}

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

    public LocalDateTime getHeureEntree(){
        return this.heureEntree;
    }
    public void setHeureEntree(LocalDateTime heureEntree){
        this.heureEntree = heureEntree;
    }
    public LocalDateTime getHeureEntreeFrom(){
        return this.heureEntreeFrom;
    }
    public void setHeureEntreeFrom(LocalDateTime heureEntreeFrom){
        this.heureEntreeFrom = heureEntreeFrom;
    }
    public LocalDateTime getHeureEntreeTo(){
        return this.heureEntreeTo;
    }
    public void setHeureEntreeTo(LocalDateTime heureEntreeTo){
        this.heureEntreeTo = heureEntreeTo;
    }
    public LocalDateTime getHeureSortie(){
        return this.heureSortie;
    }
    public void setHeureSortie(LocalDateTime heureSortie){
        this.heureSortie = heureSortie;
    }
    public LocalDateTime getHeureSortieFrom(){
        return this.heureSortieFrom;
    }
    public void setHeureSortieFrom(LocalDateTime heureSortieFrom){
        this.heureSortieFrom = heureSortieFrom;
    }
    public LocalDateTime getHeureSortieTo(){
        return this.heureSortieTo;
    }
    public void setHeureSortieTo(LocalDateTime heureSortieTo){
        this.heureSortieTo = heureSortieTo;
    }
    public Boolean getPresence(){
        return this.presence;
    }
    public void setPresence(Boolean presence){
        this.presence = presence;
    }
    public String getPointageSens(){
        return this.pointageSens;
    }
    public void setPointageSens(String pointageSens){
        this.pointageSens = pointageSens;
    }   
    public String getPointageSensMin(){
        return this.pointageSensMin;
    }
    public void setPointageSensMin(String pointageSensMin){
        this.pointageSensMin = pointageSensMin;
    }
    public String getPointageSensMax(){
        return this.pointageSensMax;
    }
    public void setPointageSensMax(String pointageSensMax){
        this.pointageSensMax = pointageSensMax;
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
    public BadgeNfcCriteria getBadgeNfc(){
        return this.badgeNfc;
    }

    public void setBadgeNfc(BadgeNfcCriteria badgeNfc){
        this.badgeNfc = badgeNfc;
    }
    public List<BadgeNfcCriteria> getBadgeNfcs(){
        return this.badgeNfcs;
    }

    public void setBadgeNfcs(List<BadgeNfcCriteria> badgeNfcs){
        this.badgeNfcs = badgeNfcs;
    }
}
