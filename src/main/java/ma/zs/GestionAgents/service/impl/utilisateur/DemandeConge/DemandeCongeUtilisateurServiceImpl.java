package ma.zs.GestionAgents.service.impl.utilisateur.DemandeConge;


import ma.zs.GestionAgents.zynerator.exception.EntityNotFoundException;
import ma.zs.GestionAgents.bean.core.DemandeConge.DemandeConge;
import ma.zs.GestionAgents.dao.criteria.core.DemandeConge.DemandeCongeCriteria;
import ma.zs.GestionAgents.dao.facade.core.DemandeConge.DemandeCongeDao;
import ma.zs.GestionAgents.dao.specification.core.DemandeConge.DemandeCongeSpecification;
import ma.zs.GestionAgents.service.facade.utilisateur.DemandeConge.DemandeCongeUtilisateurService;
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

import ma.zs.GestionAgents.service.facade.utilisateur.Poste.RaisonDemandeCongeUtilisateurService ;
import ma.zs.GestionAgents.bean.core.Poste.RaisonDemandeConge ;

import java.util.List;
@Service
public class DemandeCongeUtilisateurServiceImpl implements DemandeCongeUtilisateurService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public DemandeConge update(DemandeConge t) {
        DemandeConge loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{DemandeConge.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public DemandeConge findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public DemandeConge findOrSave(DemandeConge t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            DemandeConge result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<DemandeConge> importData(List<DemandeConge> items) {
        List<DemandeConge> list = new ArrayList<>();
        for (DemandeConge t : items) {
            DemandeConge founded = findByReferenceEntity(t);
			if (founded == null) {
				findOrSaveAssociatedObject(t);
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<DemandeConge> findAll() {
        return dao.findAll();
    }

    public List<DemandeConge> findByCriteria(DemandeCongeCriteria criteria) {
        List<DemandeConge> content = null;
        if (criteria != null) {
            DemandeCongeSpecification mySpecification = constructSpecification(criteria);
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


    private DemandeCongeSpecification constructSpecification(DemandeCongeCriteria criteria) {
        DemandeCongeSpecification mySpecification =  (DemandeCongeSpecification) RefelexivityUtil.constructObjectUsingOneParam(DemandeCongeSpecification.class, criteria);
        return mySpecification;
    }

    public List<DemandeConge> findPaginatedByCriteria(DemandeCongeCriteria criteria, int page, int pageSize, String order, String sortField) {
        DemandeCongeSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(DemandeCongeCriteria criteria) {
        DemandeCongeSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<DemandeConge> findByRaisonDemandeCongeId(Long id){
        return dao.findByRaisonDemandeCongeId(id);
    }
    public int deleteByRaisonDemandeCongeId(Long id){
        return dao.deleteByRaisonDemandeCongeId(id);
    }
    public long countByRaisonDemandeCongeRef(String ref){
        return dao.countByRaisonDemandeCongeRef(ref);
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
    public int delete(DemandeConge t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<DemandeConge> delete(List<DemandeConge> list) {
		List<DemandeConge> result = new ArrayList();
        if (list != null) {
            for (DemandeConge t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public DemandeConge create(DemandeConge t) {
        DemandeConge loaded = findByReferenceEntity(t);
        DemandeConge saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<DemandeConge> create(List<DemandeConge> ts) {
        List<DemandeConge> result = new ArrayList<>();
        if (ts != null) {
            for (DemandeConge t : ts) {
				DemandeConge created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public DemandeConge findWithAssociatedLists(Long id){
        DemandeConge result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<DemandeConge> update(List<DemandeConge> ts, boolean createIfNotExist) {
        List<DemandeConge> result = new ArrayList<>();
        if (ts != null) {
            for (DemandeConge t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    DemandeConge loadedItem = dao.findById(t.getId()).orElse(null);
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





    public DemandeConge findByReferenceEntity(DemandeConge t){
        return t==null? null : dao.findByRef(t.getRef());
    }
    public void findOrSaveAssociatedObject(DemandeConge t){
        if( t != null) {
            t.setRaisonDemandeConge(raisonDemandeCongeService.findOrSave(t.getRaisonDemandeConge()));
        }
    }



    public List<DemandeConge> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<DemandeConge>> getToBeSavedAndToBeDeleted(List<DemandeConge> oldList, List<DemandeConge> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<DemandeConge> importExcel(MultipartFile file) {
        return null;
    }








    @Autowired
    private RaisonDemandeCongeUtilisateurService raisonDemandeCongeService ;

    private @Autowired DemandeCongeDao dao;


}
