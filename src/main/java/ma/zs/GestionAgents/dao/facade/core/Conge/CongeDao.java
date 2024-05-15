package ma.zs.GestionAgents.dao.facade.core.Conge;

import org.springframework.data.jpa.repository.Query;
import ma.zs.GestionAgents.zynerator.repository.AbstractRepository;
import ma.zs.GestionAgents.bean.core.Conge.Conge;
import org.springframework.stereotype.Repository;
import ma.zs.GestionAgents.bean.core.Conge.Conge;
import java.util.List;


@Repository
public interface CongeDao extends AbstractRepository<Conge,Long>  {
    Conge findByRef(String ref);
    int deleteByRef(String ref);


    @Query("SELECT NEW Conge(item.id,item.ref) FROM Conge item")
    List<Conge> findAllOptimized();

}
