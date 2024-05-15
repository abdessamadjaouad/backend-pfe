package ma.zs.GestionAgents.dao.facade.core.Service;

import org.springframework.data.jpa.repository.Query;
import ma.zs.GestionAgents.zynerator.repository.AbstractRepository;
import ma.zs.GestionAgents.bean.core.Service.EntiteAdministrative;
import org.springframework.stereotype.Repository;
import ma.zs.GestionAgents.bean.core.Service.EntiteAdministrative;
import java.util.List;


@Repository
public interface EntiteAdministrativeDao extends AbstractRepository<EntiteAdministrative,Long>  {
    EntiteAdministrative findByRef(String ref);
    int deleteByRef(String ref);

    List<EntiteAdministrative> findByChefServiceId(Long id);
    int deleteByChefServiceId(Long id);
    long countByChefServiceRef(String ref);

    @Query("SELECT NEW EntiteAdministrative(item.id,item.libelle) FROM EntiteAdministrative item")
    List<EntiteAdministrative> findAllOptimized();

}
