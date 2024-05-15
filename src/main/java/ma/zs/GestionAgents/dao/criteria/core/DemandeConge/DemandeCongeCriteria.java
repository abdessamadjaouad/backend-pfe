package  ma.zs.GestionAgents.dao.criteria.core.DemandeConge;


import ma.zs.GestionAgents.dao.criteria.core.Poste.RaisonDemandeCongeCriteria;

import ma.zs.GestionAgents.zynerator.criteria.BaseCriteria;
import java.util.List;
import java.time.LocalDateTime;
import java.time.LocalDate;

public class DemandeCongeCriteria extends  BaseCriteria  {

    private String ref;
    private String refLike;
    private LocalDateTime dateDebut;
    private LocalDateTime dateDebutFrom;
    private LocalDateTime dateDebutTo;
    private LocalDateTime dateFin;
    private LocalDateTime dateFinFrom;
    private LocalDateTime dateFinTo;

    private RaisonDemandeCongeCriteria raisonDemandeConge ;
    private List<RaisonDemandeCongeCriteria> raisonDemandeConges ;


    public DemandeCongeCriteria(){}

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

    public LocalDateTime getDateDebut(){
        return this.dateDebut;
    }
    public void setDateDebut(LocalDateTime dateDebut){
        this.dateDebut = dateDebut;
    }
    public LocalDateTime getDateDebutFrom(){
        return this.dateDebutFrom;
    }
    public void setDateDebutFrom(LocalDateTime dateDebutFrom){
        this.dateDebutFrom = dateDebutFrom;
    }
    public LocalDateTime getDateDebutTo(){
        return this.dateDebutTo;
    }
    public void setDateDebutTo(LocalDateTime dateDebutTo){
        this.dateDebutTo = dateDebutTo;
    }
    public LocalDateTime getDateFin(){
        return this.dateFin;
    }
    public void setDateFin(LocalDateTime dateFin){
        this.dateFin = dateFin;
    }
    public LocalDateTime getDateFinFrom(){
        return this.dateFinFrom;
    }
    public void setDateFinFrom(LocalDateTime dateFinFrom){
        this.dateFinFrom = dateFinFrom;
    }
    public LocalDateTime getDateFinTo(){
        return this.dateFinTo;
    }
    public void setDateFinTo(LocalDateTime dateFinTo){
        this.dateFinTo = dateFinTo;
    }

    public RaisonDemandeCongeCriteria getRaisonDemandeConge(){
        return this.raisonDemandeConge;
    }

    public void setRaisonDemandeConge(RaisonDemandeCongeCriteria raisonDemandeConge){
        this.raisonDemandeConge = raisonDemandeConge;
    }
    public List<RaisonDemandeCongeCriteria> getRaisonDemandeConges(){
        return this.raisonDemandeConges;
    }

    public void setRaisonDemandeConges(List<RaisonDemandeCongeCriteria> raisonDemandeConges){
        this.raisonDemandeConges = raisonDemandeConges;
    }
}
