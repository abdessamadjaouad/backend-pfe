package ma.zs.GestionAgents.bean.core.Poste;

import java.util.Objects;







import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.GestionAgents.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "poste")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="poste_seq",sequenceName="poste_seq",allocationSize=1, initialValue = 1)
public class Poste  extends BaseEntity     {

    private Long id;

    @Column(length = 500)
    private String ref;
    @Column(length = 500)
    private String libelle;



    public Poste(){
        super();
    }

    public Poste(Long id,String libelle){
        this.id = id;
        this.libelle = libelle ;
    }
    public Poste(String libelle){
        this.libelle = libelle ;
    }




    @Id
    @Column(name = "id")
        @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="poste_seq")
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
        Poste poste = (Poste) o;
        return id != null && id.equals(poste.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

