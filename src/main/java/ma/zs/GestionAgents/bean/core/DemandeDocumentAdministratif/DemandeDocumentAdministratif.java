package ma.zs.GestionAgents.bean.core.DemandeDocumentAdministratif;

import java.util.Objects;







import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.GestionAgents.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "demande_document_administratif")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="demande_document_administratif_seq",sequenceName="demande_document_administratif_seq",allocationSize=1, initialValue = 1)
public class DemandeDocumentAdministratif  extends BaseEntity     {

    private Long id;

    @Column(length = 500)
    private String ref;
    @Column(length = 500)
    private String type;
    @Column(length = 500)
    private String libelle;



    public DemandeDocumentAdministratif(){
        super();
    }

    public DemandeDocumentAdministratif(Long id,String libelle){
        this.id = id;
        this.libelle = libelle ;
    }
    public DemandeDocumentAdministratif(String libelle){
        this.libelle = libelle ;
    }




    @Id
    @Column(name = "id")
        @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="demande_document_administratif_seq")
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
    public String getType(){
        return this.type;
    }
    public void setType(String type){
        this.type = type;
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
        DemandeDocumentAdministratif demandeDocumentAdministratif = (DemandeDocumentAdministratif) o;
        return id != null && id.equals(demandeDocumentAdministratif.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

