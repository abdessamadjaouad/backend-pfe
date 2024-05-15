package ma.zs.GestionAgents.service.facade.utilisateur.Agent;

import java.util.List;
import ma.zs.GestionAgents.bean.core.Agent.Agent;
import ma.zs.GestionAgents.dao.criteria.core.Agent.AgentCriteria;
import ma.zs.GestionAgents.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface AgentUtilisateurService {



    List<Agent> findByEntiteAdministrativeId(Long id);
    int deleteByEntiteAdministrativeId(Long id);
    long countByEntiteAdministrativeRef(String ref);
    List<Agent> findByPosteId(Long id);
    int deleteByPosteId(Long id);
    long countByPosteRef(String ref);
    List<Agent> findByPlageHoraireId(Long id);
    int deleteByPlageHoraireId(Long id);
    long countByPlageHoraireRef(String ref);




	Agent create(Agent t);

    Agent update(Agent t);

    List<Agent> update(List<Agent> ts,boolean createIfNotExist);

    Agent findById(Long id);

    Agent findOrSave(Agent t);

    Agent findByReferenceEntity(Agent t);

    Agent findWithAssociatedLists(Long id);

    List<Agent> findAllOptimized();

    List<Agent> findAll();

    List<Agent> findByCriteria(AgentCriteria criteria);

    List<Agent> findPaginatedByCriteria(AgentCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(AgentCriteria criteria);

    List<Agent> delete(List<Agent> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<Agent>> getToBeSavedAndToBeDeleted(List<Agent> oldList, List<Agent> newList);

    List<Agent> importData(List<Agent> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<Agent> importExcel(MultipartFile file);

}
