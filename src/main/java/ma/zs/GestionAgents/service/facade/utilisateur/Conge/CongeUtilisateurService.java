package ma.zs.GestionAgents.service.facade.utilisateur.Conge;

import java.util.List;
import ma.zs.GestionAgents.bean.core.Conge.Conge;
import ma.zs.GestionAgents.dao.criteria.core.Conge.CongeCriteria;
import ma.zs.GestionAgents.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface CongeUtilisateurService {







	Conge create(Conge t);

    Conge update(Conge t);

    List<Conge> update(List<Conge> ts,boolean createIfNotExist);

    Conge findById(Long id);

    Conge findOrSave(Conge t);

    Conge findByReferenceEntity(Conge t);

    Conge findWithAssociatedLists(Long id);

    List<Conge> findAllOptimized();

    List<Conge> findAll();

    List<Conge> findByCriteria(CongeCriteria criteria);

    List<Conge> findPaginatedByCriteria(CongeCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(CongeCriteria criteria);

    List<Conge> delete(List<Conge> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<Conge>> getToBeSavedAndToBeDeleted(List<Conge> oldList, List<Conge> newList);

    List<Conge> importData(List<Conge> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<Conge> importExcel(MultipartFile file);

}
