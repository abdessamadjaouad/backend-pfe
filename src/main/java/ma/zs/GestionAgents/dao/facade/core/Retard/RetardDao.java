package ma.zs.GestionAgents.dao.facade.core.Retard;

import org.springframework.data.jpa.repository.Query;
import ma.zs.GestionAgents.zynerator.repository.AbstractRepository;
import ma.zs.GestionAgents.bean.core.Retard.Retard;
import org.springframework.stereotype.Repository;
import ma.zs.GestionAgents.bean.core.Retard.Retard;
import java.util.List;


@Repository
public interface RetardDao extends AbstractRepository<Retard,Long>  {
    Retard findByRef(String ref);
    int deleteByRef(String ref);

    List<Retard> findByAgentId(Long id);
    int deleteByAgentId(Long id);
    long countByAgentRef(String ref);

    @Query("SELECT NEW Retard(item.id,item.ref) FROM Retard item")
    List<Retard> findAllOptimized();

}
