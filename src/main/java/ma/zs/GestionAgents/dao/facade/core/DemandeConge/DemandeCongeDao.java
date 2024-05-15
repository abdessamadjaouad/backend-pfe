package ma.zs.GestionAgents.dao.facade.core.DemandeConge;

import org.springframework.data.jpa.repository.Query;
import ma.zs.GestionAgents.zynerator.repository.AbstractRepository;
import ma.zs.GestionAgents.bean.core.DemandeConge.DemandeConge;
import org.springframework.stereotype.Repository;
import ma.zs.GestionAgents.bean.core.DemandeConge.DemandeConge;
import java.util.List;


@Repository
public interface DemandeCongeDao extends AbstractRepository<DemandeConge,Long>  {
    DemandeConge findByRef(String ref);
    int deleteByRef(String ref);

    List<DemandeConge> findByRaisonDemandeCongeId(Long id);
    int deleteByRaisonDemandeCongeId(Long id);
    long countByRaisonDemandeCongeRef(String ref);

    @Query("SELECT NEW DemandeConge(item.id,item.ref) FROM DemandeConge item")
    List<DemandeConge> findAllOptimized();

}
