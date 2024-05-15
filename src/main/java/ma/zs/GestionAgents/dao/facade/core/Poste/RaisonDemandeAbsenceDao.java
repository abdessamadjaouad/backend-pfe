package ma.zs.GestionAgents.dao.facade.core.Poste;

import org.springframework.data.jpa.repository.Query;
import ma.zs.GestionAgents.zynerator.repository.AbstractRepository;
import ma.zs.GestionAgents.bean.core.Poste.RaisonDemandeAbsence;
import org.springframework.stereotype.Repository;
import ma.zs.GestionAgents.bean.core.Poste.RaisonDemandeAbsence;
import java.util.List;


@Repository
public interface RaisonDemandeAbsenceDao extends AbstractRepository<RaisonDemandeAbsence,Long>  {
    RaisonDemandeAbsence findByRef(String ref);
    int deleteByRef(String ref);


    @Query("SELECT NEW RaisonDemandeAbsence(item.id,item.libelle) FROM RaisonDemandeAbsence item")
    List<RaisonDemandeAbsence> findAllOptimized();

}
