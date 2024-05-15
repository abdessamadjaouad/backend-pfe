package  ma.zs.GestionAgents.ws.dto.heureSupplementaire;

import ma.zs.GestionAgents.zynerator.audit.Log;
import ma.zs.GestionAgents.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;





@JsonInclude(JsonInclude.Include.NON_NULL)
public class HeureSupplementaireDto  extends AuditBaseDto {

    private String ref  ;
    private Long nbrHeure  ;




    public HeureSupplementaireDto(){
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
    public Long getNbrHeure(){
        return this.nbrHeure;
    }
    public void setNbrHeure(Long nbrHeure){
        this.nbrHeure = nbrHeure;
    }








}
