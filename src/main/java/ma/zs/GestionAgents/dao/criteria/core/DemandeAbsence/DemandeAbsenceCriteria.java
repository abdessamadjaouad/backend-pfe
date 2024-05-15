package  ma.zs.GestionAgents.dao.criteria.core.DemandeAbsence;


import ma.zs.GestionAgents.dao.criteria.core.Poste.RaisonDemandeAbsenceCriteria;

import ma.zs.GestionAgents.zynerator.criteria.BaseCriteria;
import java.util.List;

public class DemandeAbsenceCriteria extends  BaseCriteria  {

    private String ref;
    private String refLike;
    private String contenu;
    private String contenuLike;

    private RaisonDemandeAbsenceCriteria raisonDemandeAbsence ;
    private List<RaisonDemandeAbsenceCriteria> raisonDemandeAbsences ;


    public DemandeAbsenceCriteria(){}

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

    public String getContenu(){
        return this.contenu;
    }
    public void setContenu(String contenu){
        this.contenu = contenu;
    }
    public String getContenuLike(){
        return this.contenuLike;
    }
    public void setContenuLike(String contenuLike){
        this.contenuLike = contenuLike;
    }


    public RaisonDemandeAbsenceCriteria getRaisonDemandeAbsence(){
        return this.raisonDemandeAbsence;
    }

    public void setRaisonDemandeAbsence(RaisonDemandeAbsenceCriteria raisonDemandeAbsence){
        this.raisonDemandeAbsence = raisonDemandeAbsence;
    }
    public List<RaisonDemandeAbsenceCriteria> getRaisonDemandeAbsences(){
        return this.raisonDemandeAbsences;
    }

    public void setRaisonDemandeAbsences(List<RaisonDemandeAbsenceCriteria> raisonDemandeAbsences){
        this.raisonDemandeAbsences = raisonDemandeAbsences;
    }
}
