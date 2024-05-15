package ma.zs.GestionAgents.service.impl.admin.Conge;


import ma.zs.GestionAgents.zynerator.exception.EntityNotFoundException;
import ma.zs.GestionAgents.bean.core.Conge.Conge;
import ma.zs.GestionAgents.dao.criteria.core.Conge.CongeCriteria;
import ma.zs.GestionAgents.dao.facade.core.Conge.CongeDao;
import ma.zs.GestionAgents.dao.specification.core.Conge.CongeSpecification;
import ma.zs.GestionAgents.service.facade.admin.Conge.CongeAdminService;
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
public class CongeAdminServiceImpl implements CongeAdminService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Conge update(Conge t) {
        Conge loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Conge.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public Conge findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Conge findOrSave(Conge t) {
        if (t != null) {
            Conge result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<Conge> importData(List<Conge> items) {
        List<Conge> list = new ArrayList<>();
        for (Conge t : items) {
            Conge founded = findByReferenceEntity(t);
			if (founded == null) {
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<Conge> findAll() {
        return dao.findAll();
    }

    public List<Conge> findByCriteria(CongeCriteria criteria) {
        List<Conge> content = null;
        if (criteria != null) {
            CongeSpecification mySpecification = constructSpecification(criteria);
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


    private CongeSpecification constructSpecification(CongeCriteria criteria) {
        CongeSpecification mySpecification =  (CongeSpecification) RefelexivityUtil.constructObjectUsingOneParam(CongeSpecification.class, criteria);
        return mySpecification;
    }

    public List<Conge> findPaginatedByCriteria(CongeCriteria criteria, int page, int pageSize, String order, String sortField) {
        CongeSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(CongeCriteria criteria) {
        CongeSpecification mySpecification = constructSpecification(criteria);
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
    public int delete(Conge t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Conge> delete(List<Conge> list) {
		List<Conge> result = new ArrayList();
        if (list != null) {
            for (Conge t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Conge create(Conge t) {
        Conge loaded = findByReferenceEntity(t);
        Conge saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Conge> create(List<Conge> ts) {
        List<Conge> result = new ArrayList<>();
        if (ts != null) {
            for (Conge t : ts) {
				Conge created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public Conge findWithAssociatedLists(Long id){
        Conge result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Conge> update(List<Conge> ts, boolean createIfNotExist) {
        List<Conge> result = new ArrayList<>();
        if (ts != null) {
            for (Conge t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Conge loadedItem = dao.findById(t.getId()).orElse(null);
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





    public Conge findByReferenceEntity(Conge t){
        return t==null? null : dao.findByRef(t.getRef());
    }



    public List<Conge> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<Conge>> getToBeSavedAndToBeDeleted(List<Conge> oldList, List<Conge> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<Conge> importExcel(MultipartFile file) {
        return null;
    }









    private @Autowired CongeDao dao;


}
