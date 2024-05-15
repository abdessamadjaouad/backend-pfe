package ma.zs.GestionAgents.service.facade.admin.Retard;

import java.util.List;
import ma.zs.GestionAgents.bean.core.Retard.Retard;
import ma.zs.GestionAgents.dao.criteria.core.Retard.RetardCriteria;
import ma.zs.GestionAgents.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface RetardAdminService {



    List<Retard> findByAgentId(Long id);
    int deleteByAgentId(Long id);
    long countByAgentRef(String ref);




	Retard create(Retard t);

    Retard update(Retard t);

    List<Retard> update(List<Retard> ts,boolean createIfNotExist);

    Retard findById(Long id);

    Retard findOrSave(Retard t);

    Retard findByReferenceEntity(Retard t);

    Retard findWithAssociatedLists(Long id);

    List<Retard> findAllOptimized();

    List<Retard> findAll();

    List<Retard> findByCriteria(RetardCriteria criteria);

    List<Retard> findPaginatedByCriteria(RetardCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(RetardCriteria criteria);

    List<Retard> delete(List<Retard> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<Retard>> getToBeSavedAndToBeDeleted(List<Retard> oldList, List<Retard> newList);

    List<Retard> importData(List<Retard> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<Retard> importExcel(MultipartFile file);

}
