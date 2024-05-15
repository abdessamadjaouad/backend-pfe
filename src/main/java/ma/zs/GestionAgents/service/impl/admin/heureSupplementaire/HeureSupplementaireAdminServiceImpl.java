package ma.zs.GestionAgents.service.impl.admin.heureSupplementaire;


import ma.zs.GestionAgents.zynerator.exception.EntityNotFoundException;
import ma.zs.GestionAgents.bean.core.heureSupplementaire.HeureSupplementaire;
import ma.zs.GestionAgents.dao.criteria.core.heureSupplementaire.HeureSupplementaireCriteria;
import ma.zs.GestionAgents.dao.facade.core.heureSupplementaire.HeureSupplementaireDao;
import ma.zs.GestionAgents.dao.specification.core.heureSupplementaire.HeureSupplementaireSpecification;
import ma.zs.GestionAgents.service.facade.admin.heureSupplementaire.HeureSupplementaireAdminService;
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
public class HeureSupplementaireAdminServiceImpl implements HeureSupplementaireAdminService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public HeureSupplementaire update(HeureSupplementaire t) {
        HeureSupplementaire loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{HeureSupplementaire.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public HeureSupplementaire findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public HeureSupplementaire findOrSave(HeureSupplementaire t) {
        if (t != null) {
            HeureSupplementaire result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<HeureSupplementaire> importData(List<HeureSupplementaire> items) {
        List<HeureSupplementaire> list = new ArrayList<>();
        for (HeureSupplementaire t : items) {
            HeureSupplementaire founded = findByReferenceEntity(t);
			if (founded == null) {
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<HeureSupplementaire> findAll() {
        return dao.findAll();
    }

    public List<HeureSupplementaire> findByCriteria(HeureSupplementaireCriteria criteria) {
        List<HeureSupplementaire> content = null;
        if (criteria != null) {
            HeureSupplementaireSpecification mySpecification = constructSpecification(criteria);
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


    private HeureSupplementaireSpecification constructSpecification(HeureSupplementaireCriteria criteria) {
        HeureSupplementaireSpecification mySpecification =  (HeureSupplementaireSpecification) RefelexivityUtil.constructObjectUsingOneParam(HeureSupplementaireSpecification.class, criteria);
        return mySpecification;
    }

    public List<HeureSupplementaire> findPaginatedByCriteria(HeureSupplementaireCriteria criteria, int page, int pageSize, String order, String sortField) {
        HeureSupplementaireSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(HeureSupplementaireCriteria criteria) {
        HeureSupplementaireSpecification mySpecification = constructSpecification(criteria);
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
    public int delete(HeureSupplementaire t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<HeureSupplementaire> delete(List<HeureSupplementaire> list) {
		List<HeureSupplementaire> result = new ArrayList();
        if (list != null) {
            for (HeureSupplementaire t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public HeureSupplementaire create(HeureSupplementaire t) {
        HeureSupplementaire loaded = findByReferenceEntity(t);
        HeureSupplementaire saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<HeureSupplementaire> create(List<HeureSupplementaire> ts) {
        List<HeureSupplementaire> result = new ArrayList<>();
        if (ts != null) {
            for (HeureSupplementaire t : ts) {
				HeureSupplementaire created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public HeureSupplementaire findWithAssociatedLists(Long id){
        HeureSupplementaire result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<HeureSupplementaire> update(List<HeureSupplementaire> ts, boolean createIfNotExist) {
        List<HeureSupplementaire> result = new ArrayList<>();
        if (ts != null) {
            for (HeureSupplementaire t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    HeureSupplementaire loadedItem = dao.findById(t.getId()).orElse(null);
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





    public HeureSupplementaire findByReferenceEntity(HeureSupplementaire t){
        return t==null? null : dao.findByRef(t.getRef());
    }



    public List<HeureSupplementaire> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<HeureSupplementaire>> getToBeSavedAndToBeDeleted(List<HeureSupplementaire> oldList, List<HeureSupplementaire> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<HeureSupplementaire> importExcel(MultipartFile file) {
        return null;
    }









    private @Autowired HeureSupplementaireDao dao;


}
