package ma.zs.GestionAgents.service.facade.admin.Absence;

import java.util.List;
import ma.zs.GestionAgents.bean.core.Absence.Absence;
import ma.zs.GestionAgents.dao.criteria.core.Absence.AbsenceCriteria;
import ma.zs.GestionAgents.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface AbsenceAdminService {







	Absence create(Absence t);

    Absence update(Absence t);

    List<Absence> update(List<Absence> ts,boolean createIfNotExist);

    Absence findById(Long id);

    Absence findOrSave(Absence t);

    Absence findByReferenceEntity(Absence t);

    Absence findWithAssociatedLists(Long id);

    List<Absence> findAllOptimized();

    List<Absence> findAll();

    List<Absence> findByCriteria(AbsenceCriteria criteria);

    List<Absence> findPaginatedByCriteria(AbsenceCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(AbsenceCriteria criteria);

    List<Absence> delete(List<Absence> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<Absence>> getToBeSavedAndToBeDeleted(List<Absence> oldList, List<Absence> newList);

    List<Absence> importData(List<Absence> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<Absence> importExcel(MultipartFile file);

}
