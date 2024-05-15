package ma.zs.GestionAgents.bean.core.DemandeAbsence;

import java.util.Objects;





import ma.zs.GestionAgents.bean.core.Poste.RaisonDemandeAbsence;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.GestionAgents.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "demande_absence")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="demande_absence_seq",sequenceName="demande_absence_seq",allocationSize=1, initialValue = 1)
public class DemandeAbsence  extends BaseEntity     {

    private Long id;

    @Column(length = 500)
    private String ref;
    @Column(length = 500)
    private String contenu;

    private RaisonDemandeAbsence raisonDemandeAbsence ;


    public DemandeAbsence(){
        super();
    }

    public DemandeAbsence(Long id,String ref){
        this.id = id;
        this.ref = ref ;
    }
    public DemandeAbsence(String ref){
        this.ref = ref ;
    }




    @Id
    @Column(name = "id")
        @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="demande_absence_seq")
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
    public String getContenu(){
        return this.contenu;
    }
    public void setContenu(String contenu){
        this.contenu = contenu;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "raison_demande_absence")
    public RaisonDemandeAbsence getRaisonDemandeAbsence(){
        return this.raisonDemandeAbsence;
    }
    public void setRaisonDemandeAbsence(RaisonDemandeAbsence raisonDemandeAbsence){
        this.raisonDemandeAbsence = raisonDemandeAbsence;
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
        DemandeAbsence demandeAbsence = (DemandeAbsence) o;
        return id != null && id.equals(demandeAbsence.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

