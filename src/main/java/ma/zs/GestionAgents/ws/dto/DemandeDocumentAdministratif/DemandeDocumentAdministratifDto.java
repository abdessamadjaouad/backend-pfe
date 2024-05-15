package  ma.zs.GestionAgents.ws.dto.DemandeDocumentAdministratif;

import ma.zs.GestionAgents.zynerator.audit.Log;
import ma.zs.GestionAgents.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;





@JsonInclude(JsonInclude.Include.NON_NULL)
public class DemandeDocumentAdministratifDto  extends AuditBaseDto {

    private String ref  ;
    private String type  ;
    private String libelle  ;




    public DemandeDocumentAdministratifDto(){
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
    public String getType(){
        return this.type;
    }
    public void setType(String type){
        this.type = type;
    }

    @Log
    public String getLibelle(){
        return this.libelle;
    }
    public void setLibelle(String libelle){
        this.libelle = libelle;
    }








}
