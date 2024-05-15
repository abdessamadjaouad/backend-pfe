package  ma.zs.GestionAgents.dao.criteria.core.DemandeDocumentAdministratif;



import ma.zs.GestionAgents.zynerator.criteria.BaseCriteria;
import java.util.List;

public class DemandeDocumentAdministratifCriteria extends  BaseCriteria  {

    private String ref;
    private String refLike;
    private String type;
    private String typeLike;
    private String libelle;
    private String libelleLike;



    public DemandeDocumentAdministratifCriteria(){}

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

    public String getType(){
        return this.type;
    }
    public void setType(String type){
        this.type = type;
    }
    public String getTypeLike(){
        return this.typeLike;
    }
    public void setTypeLike(String typeLike){
        this.typeLike = typeLike;
    }

    public String getLibelle(){
        return this.libelle;
    }
    public void setLibelle(String libelle){
        this.libelle = libelle;
    }
    public String getLibelleLike(){
        return this.libelleLike;
    }
    public void setLibelleLike(String libelleLike){
        this.libelleLike = libelleLike;
    }


}
