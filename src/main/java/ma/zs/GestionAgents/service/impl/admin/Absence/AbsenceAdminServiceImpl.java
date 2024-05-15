package ma.zs.GestionAgents.service.impl.admin.Absence;


import ma.zs.GestionAgents.zynerator.exception.EntityNotFoundException;
import ma.zs.GestionAgents.bean.core.Absence.Absence;
import ma.zs.GestionAgents.dao.criteria.core.Absence.AbsenceCriteria;
import ma.zs.GestionAgents.dao.facade.core.Absence.AbsenceDao;
import ma.zs.GestionAgents.dao.specification.core.Absence.AbsenceSpecification;
import ma.zs.GestionAgents.service.facade.admin.Absence.AbsenceAdminService;
import ma.zs.GestionAgents.zynerator.service.AbstractServiceImpl;
import ma.zs.GestionAgents.zynerator.util.ListUtil;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.multipart.MultipartFile;

import ma.zs.GestionAgents.zynerator.util.RefelexivityUtil;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
@Service
public class AbsenceAdminServiceImpl implements AbsenceAdminService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Absence update(Absence t) {
        Absence loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Absence.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public Absence findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Absence findOrSave(Absence t) {
        if (t != null) {
            Absence result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<Absence> importData(List<Absence> items) {
        List<Absence> list = new ArrayList<>();
        for (Absence t : items) {
            Absence founded = findByReferenceEntity(t);
			if (founded == null) {
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<Absence> findAll() {
        return dao.findAll();
    }

    public List<Absence> findByCriteria(AbsenceCriteria criteria) {
        List<Absence> content = null;
        if (criteria != null) {
            AbsenceSpecification mySpecification = constructSpecification(criteria);
            if (criteria.isPeagable()) {
                Pageable pageable = PageRequest.of(0, criteria.getMaxResults());
                content = dao.findAll(mySpecification, pageable).getContent();
            } else {
                content = dao.findAll(mySpecification);
            }
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private AbsenceSpecification constructSpecification(AbsenceCriteria criteria) {
        AbsenceSpecification mySpecification =  (AbsenceSpecification) RefelexivityUtil.constructObjectUsingOneParam(AbsenceSpecification.class, criteria);
        return mySpecification;
    }

    public List<Absence> findPaginatedByCriteria(AbsenceCriteria criteria, int page, int pageSize, String order, String sortField) {
        AbsenceSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(AbsenceCriteria criteria) {
        AbsenceSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }


	public boolean deleteById(Long id) {
        boolean condition = deleteByIdCheckCondition(id);
        if (condition) {
            dao.deleteById(id);
        }
        return condition;
    }

    public boolean deleteByIdCheckCondition(Long id) {
        return true;
    }

    public void deleteByIdIn(List<Long> ids) {
        //dao.deleteByIdIn(ids);
    }
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public int delete(Absence t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Absence> delete(List<Absence> list) {
		List<Absence> result = new ArrayList();
        if (list != null) {
            for (Absence t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Absence create(Absence t) {
        Absence loaded = findByReferenceEntity(t);
        Absence saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Absence> create(List<Absence> ts) {
        List<Absence> result = new ArrayList<>();
        if (ts != null) {
            for (Absence t : ts) {
				Absence created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public Absence findWithAssociatedLists(Long id){
        Absence result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Absence> update(List<Absence> ts, boolean createIfNotExist) {
        List<Absence> result = new ArrayList<>();
        if (ts != null) {
            for (Absence t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Absence loadedItem = dao.findById(t.getId()).orElse(null);
                    if (createIfNotExist && (t.getId() == null || loadedItem == null)) {
                        dao.save(t);
                    } else if (t.getId() != null && loadedItem != null) {
                        dao.save(t);
                    } else {
                        result.add(t);
                    }
                }
            }
        }
        return result;
    }





    public Absence findByReferenceEntity(Absence t){
        return t==null? null : dao.findByRef(t.getRef());
    }



    public List<Absence> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<Absence>> getToBeSavedAndToBeDeleted(List<Absence> oldList, List<Absence> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<Absence> importExcel(MultipartFile file) {
        return null;
    }









    private @Autowired AbsenceDao dao;


}
