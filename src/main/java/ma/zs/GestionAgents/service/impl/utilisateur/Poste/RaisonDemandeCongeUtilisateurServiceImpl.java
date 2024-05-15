package ma.zs.GestionAgents.service.impl.utilisateur.Poste;


import ma.zs.GestionAgents.zynerator.exception.EntityNotFoundException;
import ma.zs.GestionAgents.bean.core.Poste.RaisonDemandeConge;
import ma.zs.GestionAgents.dao.criteria.core.Poste.RaisonDemandeCongeCriteria;
import ma.zs.GestionAgents.dao.facade.core.Poste.RaisonDemandeCongeDao;
import ma.zs.GestionAgents.dao.specification.core.Poste.RaisonDemandeCongeSpecification;
import ma.zs.GestionAgents.service.facade.utilisateur.Poste.RaisonDemandeCongeUtilisateurService;
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
public class RaisonDemandeCongeUtilisateurServiceImpl implements RaisonDemandeCongeUtilisateurService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public RaisonDemandeConge update(RaisonDemandeConge t) {
        RaisonDemandeConge loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{RaisonDemandeConge.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public RaisonDemandeConge findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public RaisonDemandeConge findOrSave(RaisonDemandeConge t) {
        if (t != null) {
            RaisonDemandeConge result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<RaisonDemandeConge> importData(List<RaisonDemandeConge> items) {
        List<RaisonDemandeConge> list = new ArrayList<>();
        for (RaisonDemandeConge t : items) {
            RaisonDemandeConge founded = findByReferenceEntity(t);
			if (founded == null) {
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<RaisonDemandeConge> findAll() {
        return dao.findAll();
    }

    public List<RaisonDemandeConge> findByCriteria(RaisonDemandeCongeCriteria criteria) {
        List<RaisonDemandeConge> content = null;
        if (criteria != null) {
            RaisonDemandeCongeSpecification mySpecification = constructSpecification(criteria);
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


    private RaisonDemandeCongeSpecification constructSpecification(RaisonDemandeCongeCriteria criteria) {
        RaisonDemandeCongeSpecification mySpecification =  (RaisonDemandeCongeSpecification) RefelexivityUtil.constructObjectUsingOneParam(RaisonDemandeCongeSpecification.class, criteria);
        return mySpecification;
    }

    public List<RaisonDemandeConge> findPaginatedByCriteria(RaisonDemandeCongeCriteria criteria, int page, int pageSize, String order, String sortField) {
        RaisonDemandeCongeSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(RaisonDemandeCongeCriteria criteria) {
        RaisonDemandeCongeSpecification mySpecification = constructSpecification(criteria);
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
    public int delete(RaisonDemandeConge t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<RaisonDemandeConge> delete(List<RaisonDemandeConge> list) {
		List<RaisonDemandeConge> result = new ArrayList();
        if (list != null) {
            for (RaisonDemandeConge t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public RaisonDemandeConge create(RaisonDemandeConge t) {
        RaisonDemandeConge loaded = findByReferenceEntity(t);
        RaisonDemandeConge saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<RaisonDemandeConge> create(List<RaisonDemandeConge> ts) {
        List<RaisonDemandeConge> result = new ArrayList<>();
        if (ts != null) {
            for (RaisonDemandeConge t : ts) {
				RaisonDemandeConge created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public RaisonDemandeConge findWithAssociatedLists(Long id){
        RaisonDemandeConge result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<RaisonDemandeConge> update(List<RaisonDemandeConge> ts, boolean createIfNotExist) {
        List<RaisonDemandeConge> result = new ArrayList<>();
        if (ts != null) {
            for (RaisonDemandeConge t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    RaisonDemandeConge loadedItem = dao.findById(t.getId()).orElse(null);
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





    public RaisonDemandeConge findByReferenceEntity(RaisonDemandeConge t){
        return t==null? null : dao.findByRef(t.getRef());
    }



    public List<RaisonDemandeConge> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<RaisonDemandeConge>> getToBeSavedAndToBeDeleted(List<RaisonDemandeConge> oldList, List<RaisonDemandeConge> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<RaisonDemandeConge> importExcel(MultipartFile file) {
        return null;
    }









    private @Autowired RaisonDemandeCongeDao dao;


}
