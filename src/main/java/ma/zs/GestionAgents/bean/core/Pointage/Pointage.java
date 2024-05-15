package ma.zs.GestionAgents.bean.core.Pointage;

import java.util.Objects;

import java.time.LocalDateTime;


import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;


import ma.zs.GestionAgents.bean.core.Agent.Agent;
import ma.zs.GestionAgents.bean.core.BioManager.BadgeNfc;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.GestionAgents.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "pointage")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="pointage_seq",sequenceName="pointage_seq",allocationSize=1, initialValue = 1)
public class Pointage  extends BaseEntity     {

    private Long id;

    @Column(length = 500)
    private String ref;
    private LocalDateTime heureEntree ;
    private LocalDateTime heureSortie ;
    @Column(columnDefinition = "boolean default false")
    private Boolean presence = false;
    private String pointageSens;

    private Agent agent ;
    private BadgeNfc badgeNfc ;


    public Pointage(){
        super();
    }

    public Pointage(Long id,String ref){
        this.id = id;
        this.ref = ref ;
    }
    public Pointage(String ref){
        this.ref = ref ;
    }




    @Id
    @Column(name = "id")
        @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="pointage_seq")
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agent")
    public Agent getAgent(){
        return this.agent;
    }
    public void setAgent(Agent agent){
        this.agent = agent;
    }
    public LocalDateTime getHeureEntree(){
        return this.heureEntree;
    }
    public void setHeureEntree(LocalDateTime heureEntree){
        this.heureEntree = heureEntree;
    }
    public LocalDateTime getHeureSortie(){
        return this.heureSortie;
    }
    public void setHeureSortie(LocalDateTime heureSortie){
        this.heureSortie = heureSortie;
    }
    public Boolean  getPresence(){
        return this.presence;
    }
    public void setPresence(Boolean presence){
        this.presence = presence;
    }
    public String getPointageSens(){
        return this.pointageSens;
    }
    public void setPointageSens(String pointageSens){
        this.pointageSens = pointageSens;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "badge_nfc")
    public BadgeNfc getBadgeNfc(){
        return this.badgeNfc;
    }
    public void setBadgeNfc(BadgeNfc badgeNfc){
        this.badgeNfc = badgeNfc;
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
        Pointage pointage = (Pointage) o;
        return id != null && id.equals(pointage.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

