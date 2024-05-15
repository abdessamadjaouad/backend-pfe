package  ma.zs.GestionAgents.ws.dto.Agent;

import ma.zs.GestionAgents.zynerator.audit.Log;
import ma.zs.GestionAgents.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;



import ma.zs.GestionAgents.ws.dto.plageHoraire.PlageHoraireDto;
import ma.zs.GestionAgents.ws.dto.Service.EntiteAdministrativeDto;
import ma.zs.GestionAgents.ws.dto.Poste.PosteDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class AgentDto  extends AuditBaseDto {

    private String ref  ;
    private String nomUtilisateur  ;
    private String motDePasse  ;
    private String nom  ;
    private String prenom  ;
    private String adresse  ;

    private EntiteAdministrativeDto entiteAdministrative ;
    private PosteDto poste ;
    private PlageHoraireDto plageHoraire ;



    public AgentDto(){
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
    public String getNomUtilisateur(){
        return this.nomUtilisateur;
    }
    public void setNomUtilisateur(String nomUtilisateur){
        this.nomUtilisateur = nomUtilisateur;
    }

    @Log
    public String getMotDePasse(){
        return this.motDePasse;
    }
    public void setMotDePasse(String motDePasse){
        this.motDePasse = motDePasse;
    }

    @Log
    public String getNom(){
        return this.nom;
    }
    public void setNom(String nom){
        this.nom = nom;
    }

    @Log
    public String getPrenom(){
        return this.prenom;
    }
    public void setPrenom(String prenom){
        this.prenom = prenom;
    }

    @Log
    public String getAdresse(){
        return this.adresse;
    }
    public void setAdresse(String adresse){
        this.adresse = adresse;
    }


    public EntiteAdministrativeDto getEntiteAdministrative(){
        return this.entiteAdministrative;
    }

    public void setEntiteAdministrative(EntiteAdministrativeDto entiteAdministrative){
        this.entiteAdministrative = entiteAdministrative;
    }
    public PosteDto getPoste(){
        return this.poste;
    }

    public void setPoste(PosteDto poste){
        this.poste = poste;
    }
    public PlageHoraireDto getPlageHoraire(){
        return this.plageHoraire;
    }

    public void setPlageHoraire(PlageHoraireDto plageHoraire){
        this.plageHoraire = plageHoraire;
    }






}
