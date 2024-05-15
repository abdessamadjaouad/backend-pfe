package ma.zs.GestionAgents.dao.facade.core.Poste;

import org.springframework.data.jpa.repository.Query;
import ma.zs.GestionAgents.zynerator.repository.AbstractRepository;
import ma.zs.GestionAgents.bean.core.Poste.Poste;
import org.springframework.stereotype.Repository;
import ma.zs.GestionAgents.bean.core.Poste.Poste;
import java.util.List;


@Repository
public interface PosteDao extends AbstractRepository<Poste,Long>  {
    Poste findByRef(String ref);
    int deleteByRef(String ref);


    @Query("SELECT NEW Poste(item.id,item.libelle) FROM Poste item")
    List<Poste> findAllOptimized();

}
