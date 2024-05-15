package ma.zs.GestionAgents.bean.core.BioManager;

import java.util.Objects;







import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.GestionAgents.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "badge_nfc")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="badge_nfc_seq",sequenceName="badge_nfc_seq",allocationSize=1, initialValue = 1)
public class BadgeNfc  extends BaseEntity     {

    private Long id;

    @Column(length = 500)
    private String ref;
    @Column(length = 500)
    private String matricule;
    @Column(length = 500)
    private String location;
    @Column(length = 500)
    private String status;



    public BadgeNfc(){
        super();
    }

    public BadgeNfc(Long id,String ref){
        this.id = id;
        this.ref = ref ;
    }
    public BadgeNfc(String ref){
        this.ref = ref ;
    }




    @Id
    @Column(name = "id")
        @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="badge_nfc_seq")
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
    public String getMatricule(){
        return this.matricule;
    }
    public void setMatricule(String matricule){
        this.matricule = matricule;
    }
    public String getLocation(){
        return this.location;
    }
    public void setLocation(String location){
        this.location = location;
    }
    public String getStatus(){
        return this.status;
    }
    public void setStatus(String status){
        this.status = status;
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
        BadgeNfc badgeNfc = (BadgeNfc) o;
        return id != null && id.equals(badgeNfc.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

