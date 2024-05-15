package ma.zs.GestionAgents.service.facade.utilisateur.BioManager;

import java.util.List;
import ma.zs.GestionAgents.bean.core.BioManager.BadgeNfc;
import ma.zs.GestionAgents.dao.criteria.core.BioManager.BadgeNfcCriteria;
import ma.zs.GestionAgents.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface BadgeNfcUtilisateurService {







	BadgeNfc create(BadgeNfc t);

    BadgeNfc update(BadgeNfc t);

    List<BadgeNfc> update(List<BadgeNfc> ts,boolean createIfNotExist);

    BadgeNfc findById(Long id);

    BadgeNfc findOrSave(BadgeNfc t);

    BadgeNfc findByReferenceEntity(BadgeNfc t);

    BadgeNfc findWithAssociatedLists(Long id);

    List<BadgeNfc> findAllOptimized();

    List<BadgeNfc> findAll();

    List<BadgeNfc> findByCriteria(BadgeNfcCriteria criteria);

    List<BadgeNfc> findPaginatedByCriteria(BadgeNfcCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(BadgeNfcCriteria criteria);

    List<BadgeNfc> delete(List<BadgeNfc> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<BadgeNfc>> getToBeSavedAndToBeDeleted(List<BadgeNfc> oldList, List<BadgeNfc> newList);

    List<BadgeNfc> importData(List<BadgeNfc> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<BadgeNfc> importExcel(MultipartFile file);

}
