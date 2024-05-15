package ma.zs.GestionAgents.dao.facade.core.plageHoraire;

import org.springframework.data.jpa.repository.Query;
import ma.zs.GestionAgents.zynerator.repository.AbstractRepository;
import ma.zs.GestionAgents.bean.core.plageHoraire.PlageHoraire;
import org.springframework.stereotype.Repository;
import ma.zs.GestionAgents.bean.core.plageHoraire.PlageHoraire;
import java.util.List;


@Repository
public interface PlageHoraireDao extends AbstractRepository<PlageHoraire,Long>  {
    PlageHoraire findByRef(String ref);
    int deleteByRef(String ref);


    @Query("SELECT NEW PlageHoraire(item.id,item.ref) FROM PlageHoraire item")
    List<PlageHoraire> findAllOptimized();

}
