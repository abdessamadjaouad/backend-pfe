package ma.zs.GestionAgents.bean.core.Absence;

import java.util.Objects;

import java.time.LocalDateTime;


import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;




import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.GestionAgents.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "absence")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="absence_seq",sequenceName="absence_seq",allocationSize=1, initialValue = 1)
public class Absence  extends BaseEntity     {

    private Long id;

    @Column(length = 500)
    private String ref;
    private LocalDateTime dateDebut ;
    private LocalDateTime dateFin ;
    @Column(length = 500)
    private String typeAbsence;



    public Absence(){
        super();
    }

    public Absence(Long id,String ref){
        this.id = id;
        this.ref = ref ;
    }
    public Absence(String ref){
        this.ref = ref ;
    }




    @Id
    @Column(name = "id")
        @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="absence_seq")
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
    public String getTypeAbsence(){
        return this.typeAbsence;
    }
    public void setTypeAbsence(String typeAbsence){
        this.typeAbsence = typeAbsence;
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
        Absence absence = (Absence) o;
        return id != null && id.equals(absence.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

