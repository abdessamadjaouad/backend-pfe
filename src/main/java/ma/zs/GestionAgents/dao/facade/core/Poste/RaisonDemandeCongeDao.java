package ma.zs.GestionAgents.dao.facade.core.Poste;

import org.springframework.data.jpa.repository.Query;
import ma.zs.GestionAgents.zynerator.repository.AbstractRepository;
import ma.zs.GestionAgents.bean.core.Poste.RaisonDemandeConge;
import org.springframework.stereotype.Repository;
import ma.zs.GestionAgents.bean.core.Poste.RaisonDemandeConge;
import java.util.List;


@Repository
public interface RaisonDemandeCongeDao extends AbstractRepository<RaisonDemandeConge,Long>  {
    RaisonDemandeConge findByRef(String ref);
    int deleteByRef(String ref);


    @Query("SELECT NEW RaisonDemandeConge(item.id,item.libelle) FROM RaisonDemandeConge item")
    List<RaisonDemandeConge> findAllOptimized();

}
