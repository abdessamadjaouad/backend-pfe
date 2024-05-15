package ma.zs.GestionAgents.dao.facade.core.Pointage;

import org.springframework.data.jpa.repository.Query;
import ma.zs.GestionAgents.zynerator.repository.AbstractRepository;
import ma.zs.GestionAgents.bean.core.Pointage.Pointage;
import org.springframework.stereotype.Repository;
import ma.zs.GestionAgents.bean.core.Pointage.Pointage;
import java.util.List;


@Repository
public interface PointageDao extends AbstractRepository<Pointage,Long>  {
    Pointage findByRef(String ref);
    int deleteByRef(String ref);

    List<Pointage> findByAgentId(Long id);
    int deleteByAgentId(Long id);
    long countByAgentRef(String ref);
    List<Pointage> findByBadgeNfcId(Long id);
    int deleteByBadgeNfcId(Long id);
    long countByBadgeNfcRef(String ref);

    @Query("SELECT NEW Pointage(item.id,item.ref) FROM Pointage item")
    List<Pointage> findAllOptimized();

}
