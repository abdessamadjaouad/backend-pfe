package ma.zs.GestionAgents.bean.core.heureSupplementaire;

import java.util.Objects;







import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.GestionAgents.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "heure_supplementaire")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="heure_supplementaire_seq",sequenceName="heure_supplementaire_seq",allocationSize=1, initialValue = 1)
public class HeureSupplementaire  extends BaseEntity     {

    private Long id;

    @Column(length = 500)
    private String ref;
    private Long nbrHeure ;



    public HeureSupplementaire(){
        super();
    }

    public HeureSupplementaire(Long id,String ref){
        this.id = id;
        this.ref = ref ;
    }
    public HeureSupplementaire(String ref){
        this.ref = ref ;
    }




    @Id
    @Column(name = "id")
        @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="heure_supplementaire_seq")
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
    public Long getNbrHeure(){
        return this.nbrHeure;
    }
    public void setNbrHeure(Long nbrHeure){
        this.nbrHeure = nbrHeure;
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
        HeureSupplementaire heureSupplementaire = (HeureSupplementaire) o;
        return id != null && id.equals(heureSupplementaire.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

