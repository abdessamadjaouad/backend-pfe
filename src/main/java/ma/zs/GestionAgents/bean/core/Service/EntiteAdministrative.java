package ma.zs.GestionAgents.bean.core.Service;

import java.util.Objects;





import ma.zs.GestionAgents.bean.core.Agent.Agent;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.GestionAgents.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "entite_administrative")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="entite_administrative_seq",sequenceName="entite_administrative_seq",allocationSize=1, initialValue = 1)
public class EntiteAdministrative  extends BaseEntity     {

    private Long id;

    @Column(length = 500)
    private String ref;
    @Column(length = 500)
    private String libelle;

    private Agent chefService ;


    public EntiteAdministrative(){
        super();
    }

    public EntiteAdministrative(Long id,String libelle){
        this.id = id;
        this.libelle = libelle ;
    }
    public EntiteAdministrative(String libelle){
        this.libelle = libelle ;
    }




    @Id
    @Column(name = "id")
        @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="entite_administrative_seq")
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chef_service")
    public Agent getChefService(){
        return this.chefService;
    }
    public void setChefService(Agent chefService){
        this.chefService = chefService;
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
        EntiteAdministrative entiteAdministrative = (EntiteAdministrative) o;
        return id != null && id.equals(entiteAdministrative.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

