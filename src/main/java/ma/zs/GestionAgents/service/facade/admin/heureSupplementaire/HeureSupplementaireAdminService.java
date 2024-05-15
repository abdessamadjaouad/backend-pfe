package ma.zs.GestionAgents.service.facade.admin.heureSupplementaire;

import java.util.List;
import ma.zs.GestionAgents.bean.core.heureSupplementaire.HeureSupplementaire;
import ma.zs.GestionAgents.dao.criteria.core.heureSupplementaire.HeureSupplementaireCriteria;
import ma.zs.GestionAgents.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface HeureSupplementaireAdminService {







	HeureSupplementaire create(HeureSupplementaire t);

    HeureSupplementaire update(HeureSupplementaire t);

    List<HeureSupplementaire> update(List<HeureSupplementaire> ts,boolean createIfNotExist);

    HeureSupplementaire findById(Long id);

    HeureSupplementaire findOrSave(HeureSupplementaire t);

    HeureSupplementaire findByReferenceEntity(HeureSupplementaire t);

    HeureSupplementaire findWithAssociatedLists(Long id);

    List<HeureSupplementaire> findAllOptimized();

    List<HeureSupplementaire> findAll();

    List<HeureSupplementaire> findByCriteria(HeureSupplementaireCriteria criteria);

    List<HeureSupplementaire> findPaginatedByCriteria(HeureSupplementaireCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(HeureSupplementaireCriteria criteria);

    List<HeureSupplementaire> delete(List<HeureSupplementaire> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<HeureSupplementaire>> getToBeSavedAndToBeDeleted(List<HeureSupplementaire> oldList, List<HeureSupplementaire> newList);

    List<HeureSupplementaire> importData(List<HeureSupplementaire> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<HeureSupplementaire> importExcel(MultipartFile file);

}
