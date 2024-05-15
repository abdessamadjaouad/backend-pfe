package ma.zs.GestionAgents.service.facade.utilisateur.DemandeConge;

import java.util.List;
import ma.zs.GestionAgents.bean.core.DemandeConge.DemandeConge;
import ma.zs.GestionAgents.dao.criteria.core.DemandeConge.DemandeCongeCriteria;
import ma.zs.GestionAgents.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface DemandeCongeUtilisateurService {



    List<DemandeConge> findByRaisonDemandeCongeId(Long id);
    int deleteByRaisonDemandeCongeId(Long id);
    long countByRaisonDemandeCongeRef(String ref);




	DemandeConge create(DemandeConge t);

    DemandeConge update(DemandeConge t);

    List<DemandeConge> update(List<DemandeConge> ts,boolean createIfNotExist);

    DemandeConge findById(Long id);

    DemandeConge findOrSave(DemandeConge t);

    DemandeConge findByReferenceEntity(DemandeConge t);

    DemandeConge findWithAssociatedLists(Long id);

    List<DemandeConge> findAllOptimized();

    List<DemandeConge> findAll();

    List<DemandeConge> findByCriteria(DemandeCongeCriteria criteria);

    List<DemandeConge> findPaginatedByCriteria(DemandeCongeCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(DemandeCongeCriteria criteria);

    List<DemandeConge> delete(List<DemandeConge> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<DemandeConge>> getToBeSavedAndToBeDeleted(List<DemandeConge> oldList, List<DemandeConge> newList);

    List<DemandeConge> importData(List<DemandeConge> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<DemandeConge> importExcel(MultipartFile file);

}
