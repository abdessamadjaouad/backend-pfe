package ma.zs.GestionAgents.service.facade.utilisateur.Pointage;

import java.util.List;
import ma.zs.GestionAgents.bean.core.Pointage.Pointage;
import ma.zs.GestionAgents.dao.criteria.core.Pointage.PointageCriteria;
import ma.zs.GestionAgents.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface PointageUtilisateurService {



    List<Pointage> findByAgentId(Long id);
    int deleteByAgentId(Long id);
    long countByAgentRef(String ref);
    List<Pointage> findByBadgeNfcId(Long id);
    int deleteByBadgeNfcId(Long id);
    long countByBadgeNfcRef(String ref);




	Pointage create(Pointage t);

    Pointage update(Pointage t);

    List<Pointage> update(List<Pointage> ts,boolean createIfNotExist);

    Pointage findById(Long id);

    Pointage findOrSave(Pointage t);

    Pointage findByReferenceEntity(Pointage t);

    Pointage findWithAssociatedLists(Long id);

    List<Pointage> findAllOptimized();

    List<Pointage> findAll();

    List<Pointage> findByCriteria(PointageCriteria criteria);

    List<Pointage> findPaginatedByCriteria(PointageCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(PointageCriteria criteria);

    List<Pointage> delete(List<Pointage> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<Pointage>> getToBeSavedAndToBeDeleted(List<Pointage> oldList, List<Pointage> newList);

    List<Pointage> importData(List<Pointage> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<Pointage> importExcel(MultipartFile file);

}
