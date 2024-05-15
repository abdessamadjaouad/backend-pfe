package  ma.zs.GestionAgents.dao.criteria.core.Agent;


import ma.zs.GestionAgents.dao.criteria.core.plageHoraire.PlageHoraireCriteria;
import ma.zs.GestionAgents.dao.criteria.core.Service.EntiteAdministrativeCriteria;
import ma.zs.GestionAgents.dao.criteria.core.Poste.PosteCriteria;

import ma.zs.GestionAgents.zynerator.criteria.BaseCriteria;
import java.util.List;

public class AgentCriteria extends  BaseCriteria  {

    private String ref;
    private String refLike;
    private String nomUtilisateur;
    private String nomUtilisateurLike;
    private String motDePasse;
    private String motDePasseLike;
    private String nom;
    private String nomLike;
    private String prenom;
    private String prenomLike;
    private String adresse;
    private String adresseLike;

    private EntiteAdministrativeCriteria entiteAdministrative ;
    private List<EntiteAdministrativeCriteria> entiteAdministratives ;
    private PosteCriteria poste ;
    private List<PosteCriteria> postes ;
    private PlageHoraireCriteria plageHoraire ;
    private List<PlageHoraireCriteria> plageHoraires ;


    public AgentCriteria(){}

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

    public String getNomUtilisateur(){
        return this.nomUtilisateur;
    }
    public void setNomUtilisateur(String nomUtilisateur){
        this.nomUtilisateur = nomUtilisateur;
    }
    public String getNomUtilisateurLike(){
        return this.nomUtilisateurLike;
    }
    public void setNomUtilisateurLike(String nomUtilisateurLike){
        this.nomUtilisateurLike = nomUtilisateurLike;
    }

    public String getMotDePasse(){
        return this.motDePasse;
    }
    public void setMotDePasse(String motDePasse){
        this.motDePasse = motDePasse;
    }
    public String getMotDePasseLike(){
        return this.motDePasseLike;
    }
    public void setMotDePasseLike(String motDePasseLike){
        this.motDePasseLike = motDePasseLike;
    }

    public String getNom(){
        return this.nom;
    }
    public void setNom(String nom){
        this.nom = nom;
    }
    public String getNomLike(){
        return this.nomLike;
    }
    public void setNomLike(String nomLike){
        this.nomLike = nomLike;
    }

    public String getPrenom(){
        return this.prenom;
    }
    public void setPrenom(String prenom){
        this.prenom = prenom;
    }
    public String getPrenomLike(){
        return this.prenomLike;
    }
    public void setPrenomLike(String prenomLike){
        this.prenomLike = prenomLike;
    }

    public String getAdresse(){
        return this.adresse;
    }
    public void setAdresse(String adresse){
        this.adresse = adresse;
    }
    public String getAdresseLike(){
        return this.adresseLike;
    }
    public void setAdresseLike(String adresseLike){
        this.adresseLike = adresseLike;
    }


    public EntiteAdministrativeCriteria getEntiteAdministrative(){
        return this.entiteAdministrative;
    }

    public void setEntiteAdministrative(EntiteAdministrativeCriteria entiteAdministrative){
        this.entiteAdministrative = entiteAdministrative;
    }
    public List<EntiteAdministrativeCriteria> getEntiteAdministratives(){
        return this.entiteAdministratives;
    }

    public void setEntiteAdministratives(List<EntiteAdministrativeCriteria> entiteAdministratives){
        this.entiteAdministratives = entiteAdministratives;
    }
    public PosteCriteria getPoste(){
        return this.poste;
    }

    public void setPoste(PosteCriteria poste){
        this.poste = poste;
    }
    public List<PosteCriteria> getPostes(){
        return this.postes;
    }

    public void setPostes(List<PosteCriteria> postes){
        this.postes = postes;
    }
    public PlageHoraireCriteria getPlageHoraire(){
        return this.plageHoraire;
    }

    public void setPlageHoraire(PlageHoraireCriteria plageHoraire){
        this.plageHoraire = plageHoraire;
    }
    public List<PlageHoraireCriteria> getPlageHoraires(){
        return this.plageHoraires;
    }

    public void setPlageHoraires(List<PlageHoraireCriteria> plageHoraires){
        this.plageHoraires = plageHoraires;
    }
}
