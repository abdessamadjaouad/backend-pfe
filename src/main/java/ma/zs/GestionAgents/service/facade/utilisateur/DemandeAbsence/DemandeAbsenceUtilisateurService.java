package ma.zs.GestionAgents.service.facade.utilisateur.DemandeAbsence;

import java.util.List;
import ma.zs.GestionAgents.bean.core.DemandeAbsence.DemandeAbsence;
import ma.zs.GestionAgents.dao.criteria.core.DemandeAbsence.DemandeAbsenceCriteria;
import ma.zs.GestionAgents.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface DemandeAbsenceUtilisateurService {



    List<DemandeAbsence> findByRaisonDemandeAbsenceId(Long id);
    int deleteByRaisonDemandeAbsenceId(Long id);
    long countByRaisonDemandeAbsenceRef(String ref);




	DemandeAbsence create(DemandeAbsence t);

    DemandeAbsence update(DemandeAbsence t);

    List<DemandeAbsence> update(List<DemandeAbsence> ts,boolean createIfNotExist);

    DemandeAbsence findById(Long id);

    DemandeAbsence findOrSave(DemandeAbsence t);

    DemandeAbsence findByReferenceEntity(DemandeAbsence t);

    DemandeAbsence findWithAssociatedLists(Long id);

    List<DemandeAbsence> findAllOptimized();

    List<DemandeAbsence> findAll();

    List<DemandeAbsence> findByCriteria(DemandeAbsenceCriteria criteria);

    List<DemandeAbsence> findPaginatedByCriteria(DemandeAbsenceCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(DemandeAbsenceCriteria criteria);

    List<DemandeAbsence> delete(List<DemandeAbsence> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<DemandeAbsence>> getToBeSavedAndToBeDeleted(List<DemandeAbsence> oldList, List<DemandeAbsence> newList);

    List<DemandeAbsence> importData(List<DemandeAbsence> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<DemandeAbsence> importExcel(MultipartFile file);

}
