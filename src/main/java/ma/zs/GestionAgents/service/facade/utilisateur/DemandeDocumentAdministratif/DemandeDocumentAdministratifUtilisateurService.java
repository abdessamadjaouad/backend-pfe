package ma.zs.GestionAgents.service.facade.utilisateur.DemandeDocumentAdministratif;

import java.util.List;
import ma.zs.GestionAgents.bean.core.DemandeDocumentAdministratif.DemandeDocumentAdministratif;
import ma.zs.GestionAgents.dao.criteria.core.DemandeDocumentAdministratif.DemandeDocumentAdministratifCriteria;
import ma.zs.GestionAgents.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface DemandeDocumentAdministratifUtilisateurService {







	DemandeDocumentAdministratif create(DemandeDocumentAdministratif t);

    DemandeDocumentAdministratif update(DemandeDocumentAdministratif t);

    List<DemandeDocumentAdministratif> update(List<DemandeDocumentAdministratif> ts,boolean createIfNotExist);

    DemandeDocumentAdministratif findById(Long id);

    DemandeDocumentAdministratif findOrSave(DemandeDocumentAdministratif t);

    DemandeDocumentAdministratif findByReferenceEntity(DemandeDocumentAdministratif t);

    DemandeDocumentAdministratif findWithAssociatedLists(Long id);

    List<DemandeDocumentAdministratif> findAllOptimized();

    List<DemandeDocumentAdministratif> findAll();

    List<DemandeDocumentAdministratif> findByCriteria(DemandeDocumentAdministratifCriteria criteria);

    List<DemandeDocumentAdministratif> findPaginatedByCriteria(DemandeDocumentAdministratifCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(DemandeDocumentAdministratifCriteria criteria);

    List<DemandeDocumentAdministratif> delete(List<DemandeDocumentAdministratif> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<DemandeDocumentAdministratif>> getToBeSavedAndToBeDeleted(List<DemandeDocumentAdministratif> oldList, List<DemandeDocumentAdministratif> newList);

    List<DemandeDocumentAdministratif> importData(List<DemandeDocumentAdministratif> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<DemandeDocumentAdministratif> importExcel(MultipartFile file);

}
