package ma.zs.GestionAgents.service.facade.admin.Poste;

import java.util.List;
import ma.zs.GestionAgents.bean.core.Poste.RaisonDemandeAbsence;
import ma.zs.GestionAgents.dao.criteria.core.Poste.RaisonDemandeAbsenceCriteria;
import ma.zs.GestionAgents.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface RaisonDemandeAbsenceAdminService {







	RaisonDemandeAbsence create(RaisonDemandeAbsence t);

    RaisonDemandeAbsence update(RaisonDemandeAbsence t);

    List<RaisonDemandeAbsence> update(List<RaisonDemandeAbsence> ts,boolean createIfNotExist);

    RaisonDemandeAbsence findById(Long id);

    RaisonDemandeAbsence findOrSave(RaisonDemandeAbsence t);

    RaisonDemandeAbsence findByReferenceEntity(RaisonDemandeAbsence t);

    RaisonDemandeAbsence findWithAssociatedLists(Long id);

    List<RaisonDemandeAbsence> findAllOptimized();

    List<RaisonDemandeAbsence> findAll();

    List<RaisonDemandeAbsence> findByCriteria(RaisonDemandeAbsenceCriteria criteria);

    List<RaisonDemandeAbsence> findPaginatedByCriteria(RaisonDemandeAbsenceCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(RaisonDemandeAbsenceCriteria criteria);

    List<RaisonDemandeAbsence> delete(List<RaisonDemandeAbsence> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<RaisonDemandeAbsence>> getToBeSavedAndToBeDeleted(List<RaisonDemandeAbsence> oldList, List<RaisonDemandeAbsence> newList);

    List<RaisonDemandeAbsence> importData(List<RaisonDemandeAbsence> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<RaisonDemandeAbsence> importExcel(MultipartFile file);

}
