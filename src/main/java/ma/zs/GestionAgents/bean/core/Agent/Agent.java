package ma.zs.GestionAgents.bean.core.Agent;

import java.util.Objects;





import ma.zs.GestionAgents.bean.core.plageHoraire.PlageHoraire;
import ma.zs.GestionAgents.bean.core.Service.EntiteAdministrative;
import ma.zs.GestionAgents.bean.core.Poste.Poste;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.GestionAgents.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "agent")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="agent_seq",sequenceName="agent_seq",allocationSize=1, initialValue = 1)
public class Agent  extends BaseEntity     {

    private Long id;

    @Column(length = 500)
    private String ref;
    @Column(length = 500)
    private String nomUtilisateur;
    @Column(length = 500)
    private String motDePasse;
    @Column(length = 500)
    private String nom;
    @Column(length = 500)
    private String prenom;
    @Column(length = 500)
    private String adresse;

    private EntiteAdministrative entiteAdministrative ;
    private Poste poste ;
    private PlageHoraire plageHoraire ;


    public Agent(){
        super();
    }

    public Agent(Long id,String ref){
        this.id = id;
        this.ref = ref ;
    }
    public Agent(String ref){
        this.ref = ref ;
    }




    @Id
    @Column(name = "id")
        @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="agent_seq")
    public Long getId(){
        return this.id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public String getRef(){
        return this.ref;
    }
    public void setRef(String ref){
        this.ref = ref;
    }
    public String getNomUtilisateur(){
        return this.nomUtilisateur;
    }
    public void setNomUtilisateur(String nomUtilisateur){
        this.nomUtilisateur = nomUtilisateur;
    }
    public String getMotDePasse(){
        return this.motDePasse;
    }
    public void setMotDePasse(String motDePasse){
        this.motDePasse = motDePasse;
    }
    public String getNom(){
        return this.nom;
    }
    public void setNom(String nom){
        this.nom = nom;
    }
    public String getPrenom(){
        return this.prenom;
    }
    public void setPrenom(String prenom){
        this.prenom = prenom;
    }
    public String getAdresse(){
        return this.adresse;
    }
    public void setAdresse(String adresse){
        this.adresse = adresse;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entite_administrative")
    public EntiteAdministrative getEntiteAdministrative(){
        return this.entiteAdministrative;
    }
    public void setEntiteAdministrative(EntiteAdministrative entiteAdministrative){
        this.entiteAdministrative = entiteAdministrative;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "poste")
    public Poste getPoste(){
        return this.poste;
    }
    public void setPoste(Poste poste){
        this.poste = poste;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plage_horaire")
    public PlageHoraire getPlageHoraire(){
        return this.plageHoraire;
    }
    public void setPlageHoraire(PlageHoraire plageHoraire){
        this.plageHoraire = plageHoraire;
    }

    @Transient
    public String getLabel() {
        label = ref;
        return label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Agent agent = (Agent) o;
        return id != null && id.equals(agent.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

