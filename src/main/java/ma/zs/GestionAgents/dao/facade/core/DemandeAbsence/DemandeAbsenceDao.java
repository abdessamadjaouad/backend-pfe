package ma.zs.GestionAgents.dao.facade.core.DemandeAbsence;

import org.springframework.data.jpa.repository.Query;
import ma.zs.GestionAgents.zynerator.repository.AbstractRepository;
import ma.zs.GestionAgents.bean.core.DemandeAbsence.DemandeAbsence;
import org.springframework.stereotype.Repository;
import ma.zs.GestionAgents.bean.core.DemandeAbsence.DemandeAbsence;
import java.util.List;


@Repository
public interface DemandeAbsenceDao extends AbstractRepository<DemandeAbsence,Long>  {
    DemandeAbsence findByRef(String ref);
    int deleteByRef(String ref);

    List<DemandeAbsence> findByRaisonDemandeAbsenceId(Long id);
    int deleteByRaisonDemandeAbsenceId(Long id);
    long countByRaisonDemandeAbsenceRef(String ref);

    @Query("SELECT NEW DemandeAbsence(item.id,item.ref) FROM DemandeAbsence item")
    List<DemandeAbsence> findAllOptimized();

}
