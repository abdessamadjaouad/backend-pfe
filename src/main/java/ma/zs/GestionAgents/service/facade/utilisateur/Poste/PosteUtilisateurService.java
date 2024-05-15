package ma.zs.GestionAgents.service.facade.utilisateur.Poste;

import java.util.List;
import ma.zs.GestionAgents.bean.core.Poste.Poste;
import ma.zs.GestionAgents.dao.criteria.core.Poste.PosteCriteria;
import ma.zs.GestionAgents.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface PosteUtilisateurService {







	Poste create(Poste t);

    Poste update(Poste t);

    List<Poste> update(List<Poste> ts,boolean createIfNotExist);

    Poste findById(Long id);

    Poste findOrSave(Poste t);

    Poste findByReferenceEntity(Poste t);

    Poste findWithAssociatedLists(Long id);

    List<Poste> findAllOptimized();

    List<Poste> findAll();

    List<Poste> findByCriteria(PosteCriteria criteria);

    List<Poste> findPaginatedByCriteria(PosteCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(PosteCriteria criteria);

    List<Poste> delete(List<Poste> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<Poste>> getToBeSavedAndToBeDeleted(List<Poste> oldList, List<Poste> newList);

    List<Poste> importData(List<Poste> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<Poste> importExcel(MultipartFile file);

}
