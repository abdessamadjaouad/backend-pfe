package  ma.zs.GestionAgents.dao.criteria.core.heureSupplementaire;



import ma.zs.GestionAgents.zynerator.criteria.BaseCriteria;
import java.util.List;

public class HeureSupplementaireCriteria extends  BaseCriteria  {

    private String ref;
    private String refLike;
    private String nbrHeure;
    private String nbrHeureMin;
    private String nbrHeureMax;



    public HeureSupplementaireCriteria(){}

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

    public String getNbrHeure(){
        return this.nbrHeure;
    }
    public void setNbrHeure(String nbrHeure){
        this.nbrHeure = nbrHeure;
    }   
    public String getNbrHeureMin(){
        return this.nbrHeureMin;
    }
    public void setNbrHeureMin(String nbrHeureMin){
        this.nbrHeureMin = nbrHeureMin;
    }
    public String getNbrHeureMax(){
        return this.nbrHeureMax;
    }
    public void setNbrHeureMax(String nbrHeureMax){
        this.nbrHeureMax = nbrHeureMax;
    }
      

}
