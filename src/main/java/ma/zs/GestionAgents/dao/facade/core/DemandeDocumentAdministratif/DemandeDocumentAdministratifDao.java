package ma.zs.GestionAgents.dao.facade.core.DemandeDocumentAdministratif;

import org.springframework.data.jpa.repository.Query;
import ma.zs.GestionAgents.zynerator.repository.AbstractRepository;
import ma.zs.GestionAgents.bean.core.DemandeDocumentAdministratif.DemandeDocumentAdministratif;
import org.springframework.stereotype.Repository;
import ma.zs.GestionAgents.bean.core.DemandeDocumentAdministratif.DemandeDocumentAdministratif;
import java.util.List;


@Repository
public interface DemandeDocumentAdministratifDao extends AbstractRepository<DemandeDocumentAdministratif,Long>  {
    DemandeDocumentAdministratif findByRef(String ref);
    int deleteByRef(String ref);


    @Query("SELECT NEW DemandeDocumentAdministratif(item.id,item.libelle) FROM DemandeDocumentAdministratif item")
    List<DemandeDocumentAdministratif> findAllOptimized();

}
