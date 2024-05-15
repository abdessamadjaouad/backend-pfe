package ma.zs.GestionAgents.dao.facade.core.Absence;

import org.springframework.data.jpa.repository.Query;
import ma.zs.GestionAgents.zynerator.repository.AbstractRepository;
import ma.zs.GestionAgents.bean.core.Absence.Absence;
import org.springframework.stereotype.Repository;
import ma.zs.GestionAgents.bean.core.Absence.Absence;
import java.util.List;


@Repository
public interface AbsenceDao extends AbstractRepository<Absence,Long>  {
    Absence findByRef(String ref);
    int deleteByRef(String ref);


    @Query("SELECT NEW Absence(item.id,item.ref) FROM Absence item")
    List<Absence> findAllOptimized();

}
