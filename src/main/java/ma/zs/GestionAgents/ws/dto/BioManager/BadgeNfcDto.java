package  ma.zs.GestionAgents.ws.dto.BioManager;

import ma.zs.GestionAgents.zynerator.audit.Log;
import ma.zs.GestionAgents.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;





@JsonInclude(JsonInclude.Include.NON_NULL)
public class BadgeNfcDto  extends AuditBaseDto {

    private String ref  ;
    private String matricule  ;
    private String location  ;
    private String status  ;




    public BadgeNfcDto(){
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
    public String getMatricule(){
        return this.matricule;
    }
    public void setMatricule(String matricule){
        this.matricule = matricule;
    }

    @Log
    public String getLocation(){
        return this.location;
    }
    public void setLocation(String location){
        this.location = location;
    }

    @Log
    public String getStatus(){
        return this.status;
    }
    public void setStatus(String status){
        this.status = status;
    }








}
