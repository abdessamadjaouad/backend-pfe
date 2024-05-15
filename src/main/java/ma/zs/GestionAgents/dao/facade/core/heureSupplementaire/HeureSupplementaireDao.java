package ma.zs.GestionAgents.dao.facade.core.heureSupplementaire;

import org.springframework.data.jpa.repository.Query;
import ma.zs.GestionAgents.zynerator.repository.AbstractRepository;
import ma.zs.GestionAgents.bean.core.heureSupplementaire.HeureSupplementaire;
import org.springframework.stereotype.Repository;
import ma.zs.GestionAgents.bean.core.heureSupplementaire.HeureSupplementaire;
import java.util.List;


@Repository
public interface HeureSupplementaireDao extends AbstractRepository<HeureSupplementaire,Long>  {
    HeureSupplementaire findByRef(String ref);
    int deleteByRef(String ref);


    @Query("SELECT NEW HeureSupplementaire(item.id,item.ref) FROM HeureSupplementaire item")
    List<HeureSupplementaire> findAllOptimized();

}
