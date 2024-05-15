package ma.zs.GestionAgents.bean.core.DemandeConge;

import java.util.Objects;

import java.time.LocalDateTime;


import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;


import ma.zs.GestionAgents.bean.core.Poste.RaisonDemandeConge;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.GestionAgents.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "demande_conge")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="demande_conge_seq",sequenceName="demande_conge_seq",allocationSize=1, initialValue = 1)
public class DemandeConge  extends BaseEntity     {

    private Long id;

    @Column(length = 500)
    private String ref;
    private LocalDateTime dateDebut ;
    private LocalDateTime dateFin ;

    private RaisonDemandeConge raisonDemandeConge ;


    public DemandeConge(){
        super();
    }

    public DemandeConge(Long id,String ref){
        this.id = id;
        this.ref = ref ;
    }
    public DemandeConge(String ref){
        this.ref = ref ;
    }




    @Id
    @Column(name = "id")
        @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="demande_conge_seq")
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
    public LocalDateTime getDateDebut(){
        return this.dateDebut;
    }
    public void setDateDebut(LocalDateTime dateDebut){
        this.dateDebut = dateDebut;
    }
    public LocalDateTime getDateFin(){
        return this.dateFin;
    }
    public void setDateFin(LocalDateTime dateFin){
        this.dateFin = dateFin;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "raison_demande_conge")
    public RaisonDemandeConge getRaisonDemandeConge(){
        return this.raisonDemandeConge;
    }
    public void setRaisonDemandeConge(RaisonDemandeConge raisonDemandeConge){
        this.raisonDemandeConge = raisonDemandeConge;
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
        DemandeConge demandeConge = (DemandeConge) o;
        return id != null && id.equals(demandeConge.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

