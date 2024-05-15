package ma.zs.GestionAgents.service.facade.utilisateur.Poste;

import java.util.List;
import ma.zs.GestionAgents.bean.core.Poste.RaisonDemandeConge;
import ma.zs.GestionAgents.dao.criteria.core.Poste.RaisonDemandeCongeCriteria;
import ma.zs.GestionAgents.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface RaisonDemandeCongeUtilisateurService {







	RaisonDemandeConge create(RaisonDemandeConge t);

    RaisonDemandeConge update(RaisonDemandeConge t);

    List<RaisonDemandeConge> update(List<RaisonDemandeConge> ts,boolean createIfNotExist);

    RaisonDemandeConge findById(Long id);

    RaisonDemandeConge findOrSave(RaisonDemandeConge t);

    RaisonDemandeConge findByReferenceEntity(RaisonDemandeConge t);

    RaisonDemandeConge findWithAssociatedLists(Long id);

    List<RaisonDemandeConge> findAllOptimized();

    List<RaisonDemandeConge> findAll();

    List<RaisonDemandeConge> findByCriteria(RaisonDemandeCongeCriteria criteria);

    List<RaisonDemandeConge> findPaginatedByCriteria(RaisonDemandeCongeCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(RaisonDemandeCongeCriteria criteria);

    List<RaisonDemandeConge> delete(List<RaisonDemandeConge> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<RaisonDemandeConge>> getToBeSavedAndToBeDeleted(List<RaisonDemandeConge> oldList, List<RaisonDemandeConge> newList);

    List<RaisonDemandeConge> importData(List<RaisonDemandeConge> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<RaisonDemandeConge> importExcel(MultipartFile file);

}
