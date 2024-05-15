package  ma.zs.GestionAgents.dao.criteria.core.BioManager;



import ma.zs.GestionAgents.zynerator.criteria.BaseCriteria;
import java.util.List;

public class BadgeNfcCriteria extends  BaseCriteria  {

    private String ref;
    private String refLike;
    private String matricule;
    private String matriculeLike;
    private String location;
    private String locationLike;
    private String status;
    private String statusLike;



    public BadgeNfcCriteria(){}

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

    public String getMatricule(){
        return this.matricule;
    }
    public void setMatricule(String matricule){
        this.matricule = matricule;
    }
    public String getMatriculeLike(){
        return this.matriculeLike;
    }
    public void setMatriculeLike(String matriculeLike){
        this.matriculeLike = matriculeLike;
    }

    public String getLocation(){
        return this.location;
    }
    public void setLocation(String location){
        this.location = location;
    }
    public String getLocationLike(){
        return this.locationLike;
    }
    public void setLocationLike(String locationLike){
        this.locationLike = locationLike;
    }

    public String getStatus(){
        return this.status;
    }
    public void setStatus(String status){
        this.status = status;
    }
    public String getStatusLike(){
        return this.statusLike;
    }
    public void setStatusLike(String statusLike){
        this.statusLike = statusLike;
    }


}
