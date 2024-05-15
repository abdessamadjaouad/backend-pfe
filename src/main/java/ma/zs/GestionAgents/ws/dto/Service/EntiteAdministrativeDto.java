package  ma.zs.GestionAgents.ws.dto.Service;

import ma.zs.GestionAgents.zynerator.audit.Log;
import ma.zs.GestionAgents.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;



import ma.zs.GestionAgents.ws.dto.Agent.AgentDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class EntiteAdministrativeDto  extends AuditBaseDto {

    private String ref  ;
    private String libelle  ;

    private AgentDto chefService ;



    public EntiteAdministrativeDto(){
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
    public String getLibelle(){
        return this.libelle;
    }
    public void setLibelle(String libelle){
        this.libelle = libelle;
    }


    public AgentDto getChefService(){
        return this.chefService;
    }

    public void setChefService(AgentDto chefService){
        this.chefService = chefService;
    }






}
