package ma.zs.GestionAgents.dao.facade.core.BioManager;

import org.springframework.data.jpa.repository.Query;
import ma.zs.GestionAgents.zynerator.repository.AbstractRepository;
import ma.zs.GestionAgents.bean.core.BioManager.BadgeNfc;
import org.springframework.stereotype.Repository;
import ma.zs.GestionAgents.bean.core.BioManager.BadgeNfc;
import java.util.List;


@Repository
public interface BadgeNfcDao extends AbstractRepository<BadgeNfc,Long>  {
    BadgeNfc findByRef(String ref);
    int deleteByRef(String ref);


    @Query("SELECT NEW BadgeNfc(item.id,item.ref) FROM BadgeNfc item")
    List<BadgeNfc> findAllOptimized();

}
