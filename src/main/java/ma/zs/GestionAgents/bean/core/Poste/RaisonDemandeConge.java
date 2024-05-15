package ma.zs.GestionAgents.bean.core.Poste;

import java.util.Objects;







import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.GestionAgents.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "raison_demande_conge")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="raison_demande_conge_seq",sequenceName="raison_demande_conge_seq",allocationSize=1, initialValue = 1)
public class RaisonDemandeConge  extends BaseEntity     {

    private Long id;

    @Column(length = 500)
    private String ref;
    @Column(length = 500)
    private String libelle;



    public RaisonDemandeConge(){
        super();
    }

    public RaisonDemandeConge(Long id,String libelle){
        this.id = id;
        this.libelle = libelle ;
    }
    public RaisonDemandeConge(String libelle){
        this.libelle = libelle ;
    }




    @Id
    @Column(name = "id")
        @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="raison_demande_conge_seq")
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
    public String getLibelle(){
        return this.libelle;
    }
    public void setLibelle(String libelle){
        this.libelle = libelle;
    }

    @Transient
    public String getLabel() {
        label = libelle;
        return label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RaisonDemandeConge raisonDemandeConge = (RaisonDemandeConge) o;
        return id != null && id.equals(raisonDemandeConge.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

