package ma.zs.GestionAgents.bean.core.Retard;

import java.util.Objects;

import java.time.LocalDateTime;


import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;


import ma.zs.GestionAgents.bean.core.Agent.Agent;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.GestionAgents.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "retard")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="retard_seq",sequenceName="retard_seq",allocationSize=1, initialValue = 1)
public class Retard  extends BaseEntity     {

    private Long id;

    @Column(length = 500)
    private String ref;
    private LocalDateTime dateRetart ;
    private Long duree ;

    private Agent agent ;


    public Retard(){
        super();
    }

    public Retard(Long id,String ref){
        this.id = id;
        this.ref = ref ;
    }
    public Retard(String ref){
        this.ref = ref ;
    }




    @Id
    @Column(name = "id")
        @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="retard_seq")
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
    public LocalDateTime getDateRetart(){
        return this.dateRetart;
    }
    public void setDateRetart(LocalDateTime dateRetart){
        this.dateRetart = dateRetart;
    }
    public Long getDuree(){
        return this.duree;
    }
    public void setDuree(Long duree){
        this.duree = duree;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agent")
    public Agent getAgent(){
        return this.agent;
    }
    public void setAgent(Agent agent){
        this.agent = agent;
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
        Retard retard = (Retard) o;
        return id != null && id.equals(retard.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

