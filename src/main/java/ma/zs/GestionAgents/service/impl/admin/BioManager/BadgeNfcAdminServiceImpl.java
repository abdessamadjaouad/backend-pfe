package ma.zs.GestionAgents.service.impl.admin.BioManager;


import ma.zs.GestionAgents.zynerator.exception.EntityNotFoundException;
import ma.zs.GestionAgents.bean.core.BioManager.BadgeNfc;
import ma.zs.GestionAgents.dao.criteria.core.BioManager.BadgeNfcCriteria;
import ma.zs.GestionAgents.dao.facade.core.BioManager.BadgeNfcDao;
import ma.zs.GestionAgents.dao.specification.core.BioManager.BadgeNfcSpecification;
import ma.zs.GestionAgents.service.facade.admin.BioManager.BadgeNfcAdminService;
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
public class BadgeNfcAdminServiceImpl implements BadgeNfcAdminService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public BadgeNfc update(BadgeNfc t) {
        BadgeNfc loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{BadgeNfc.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public BadgeNfc findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public BadgeNfc findOrSave(BadgeNfc t) {
        if (t != null) {
            BadgeNfc result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<BadgeNfc> importData(List<BadgeNfc> items) {
        List<BadgeNfc> list = new ArrayList<>();
        for (BadgeNfc t : items) {
            BadgeNfc founded = findByReferenceEntity(t);
			if (founded == null) {
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<BadgeNfc> findAll() {
        return dao.findAll();
    }

    public List<BadgeNfc> findByCriteria(BadgeNfcCriteria criteria) {
        List<BadgeNfc> content = null;
        if (criteria != null) {
            BadgeNfcSpecification mySpecification = constructSpecification(criteria);
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


    private BadgeNfcSpecification constructSpecification(BadgeNfcCriteria criteria) {
        BadgeNfcSpecification mySpecification =  (BadgeNfcSpecification) RefelexivityUtil.constructObjectUsingOneParam(BadgeNfcSpecification.class, criteria);
        return mySpecification;
    }

    public List<BadgeNfc> findPaginatedByCriteria(BadgeNfcCriteria criteria, int page, int pageSize, String order, String sortField) {
        BadgeNfcSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(BadgeNfcCriteria criteria) {
        BadgeNfcSpecification mySpecification = constructSpecification(criteria);
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
    public int delete(BadgeNfc t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<BadgeNfc> delete(List<BadgeNfc> list) {
		List<BadgeNfc> result = new ArrayList();
        if (list != null) {
            for (BadgeNfc t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public BadgeNfc create(BadgeNfc t) {
        BadgeNfc loaded = findByReferenceEntity(t);
        BadgeNfc saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<BadgeNfc> create(List<BadgeNfc> ts) {
        List<BadgeNfc> result = new ArrayList<>();
        if (ts != null) {
            for (BadgeNfc t : ts) {
				BadgeNfc created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public BadgeNfc findWithAssociatedLists(Long id){
        BadgeNfc result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<BadgeNfc> update(List<BadgeNfc> ts, boolean createIfNotExist) {
        List<BadgeNfc> result = new ArrayList<>();
        if (ts != null) {
            for (BadgeNfc t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    BadgeNfc loadedItem = dao.findById(t.getId()).orElse(null);
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





    public BadgeNfc findByReferenceEntity(BadgeNfc t){
        return t==null? null : dao.findByRef(t.getRef());
    }



    public List<BadgeNfc> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<BadgeNfc>> getToBeSavedAndToBeDeleted(List<BadgeNfc> oldList, List<BadgeNfc> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<BadgeNfc> importExcel(MultipartFile file) {
        return null;
    }









    private @Autowired BadgeNfcDao dao;


}
