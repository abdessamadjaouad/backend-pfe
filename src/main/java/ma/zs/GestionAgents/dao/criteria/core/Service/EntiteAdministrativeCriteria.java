package  ma.zs.GestionAgents.dao.criteria.core.Service;


import ma.zs.GestionAgents.dao.criteria.core.Agent.AgentCriteria;

import ma.zs.GestionAgents.zynerator.criteria.BaseCriteria;
import java.util.List;

public class EntiteAdministrativeCriteria extends  BaseCriteria  {

    private String ref;
    private String refLike;
    private String libelle;
    private String libelleLike;

    private AgentCriteria chefService ;
    private List<AgentCriteria> chefServices ;


    public EntiteAdministrativeCriteria(){}

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


    public AgentCriteria getChefService(){
        return this.chefService;
    }

    public void setChefService(AgentCriteria chefService){
        this.chefService = chefService;
    }
    public List<AgentCriteria> getChefServices(){
        return this.chefServices;
    }

    public void setChefServices(List<AgentCriteria> chefServices){
        this.chefServices = chefServices;
    }
}
