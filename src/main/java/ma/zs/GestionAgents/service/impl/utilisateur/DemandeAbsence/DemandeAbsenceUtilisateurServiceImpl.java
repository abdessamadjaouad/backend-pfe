package ma.zs.GestionAgents.service.impl.utilisateur.DemandeAbsence;


import ma.zs.GestionAgents.zynerator.exception.EntityNotFoundException;
import ma.zs.GestionAgents.bean.core.DemandeAbsence.DemandeAbsence;
import ma.zs.GestionAgents.dao.criteria.core.DemandeAbsence.DemandeAbsenceCriteria;
import ma.zs.GestionAgents.dao.facade.core.DemandeAbsence.DemandeAbsenceDao;
import ma.zs.GestionAgents.dao.specification.core.DemandeAbsence.DemandeAbsenceSpecification;
import ma.zs.GestionAgents.service.facade.utilisateur.DemandeAbsence.DemandeAbsenceUtilisateurService;
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

import ma.zs.GestionAgents.service.facade.utilisateur.Poste.RaisonDemandeAbsenceUtilisateurService ;
import ma.zs.GestionAgents.bean.core.Poste.RaisonDemandeAbsence ;

import java.util.List;
@Service
public class DemandeAbsenceUtilisateurServiceImpl implements DemandeAbsenceUtilisateurService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public DemandeAbsence update(DemandeAbsence t) {
        DemandeAbsence loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{DemandeAbsence.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public DemandeAbsence findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public DemandeAbsence findOrSave(DemandeAbsence t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            DemandeAbsence result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<DemandeAbsence> importData(List<DemandeAbsence> items) {
        List<DemandeAbsence> list = new ArrayList<>();
        for (DemandeAbsence t : items) {
            DemandeAbsence founded = findByReferenceEntity(t);
			if (founded == null) {
				findOrSaveAssociatedObject(t);
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<DemandeAbsence> findAll() {
        return dao.findAll();
    }

    public List<DemandeAbsence> findByCriteria(DemandeAbsenceCriteria criteria) {
        List<DemandeAbsence> content = null;
        if (criteria != null) {
            DemandeAbsenceSpecification mySpecification = constructSpecification(criteria);
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


    private DemandeAbsenceSpecification constructSpecification(DemandeAbsenceCriteria criteria) {
        DemandeAbsenceSpecification mySpecification =  (DemandeAbsenceSpecification) RefelexivityUtil.constructObjectUsingOneParam(DemandeAbsenceSpecification.class, criteria);
        return mySpecification;
    }

    public List<DemandeAbsence> findPaginatedByCriteria(DemandeAbsenceCriteria criteria, int page, int pageSize, String order, String sortField) {
        DemandeAbsenceSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(DemandeAbsenceCriteria criteria) {
        DemandeAbsenceSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<DemandeAbsence> findByRaisonDemandeAbsenceId(Long id){
        return dao.findByRaisonDemandeAbsenceId(id);
    }
    public int deleteByRaisonDemandeAbsenceId(Long id){
        return dao.deleteByRaisonDemandeAbsenceId(id);
    }
    public long countByRaisonDemandeAbsenceRef(String ref){
        return dao.countByRaisonDemandeAbsenceRef(ref);
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
    public int delete(DemandeAbsence t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<DemandeAbsence> delete(List<DemandeAbsence> list) {
		List<DemandeAbsence> result = new ArrayList();
        if (list != null) {
            for (DemandeAbsence t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public DemandeAbsence create(DemandeAbsence t) {
        DemandeAbsence loaded = findByReferenceEntity(t);
        DemandeAbsence saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<DemandeAbsence> create(List<DemandeAbsence> ts) {
        List<DemandeAbsence> result = new ArrayList<>();
        if (ts != null) {
            for (DemandeAbsence t : ts) {
				DemandeAbsence created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public DemandeAbsence findWithAssociatedLists(Long id){
        DemandeAbsence result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<DemandeAbsence> update(List<DemandeAbsence> ts, boolean createIfNotExist) {
        List<DemandeAbsence> result = new ArrayList<>();
        if (ts != null) {
            for (DemandeAbsence t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    DemandeAbsence loadedItem = dao.findById(t.getId()).orElse(null);
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





    public DemandeAbsence findByReferenceEntity(DemandeAbsence t){
        return t==null? null : dao.findByRef(t.getRef());
    }
    public void findOrSaveAssociatedObject(DemandeAbsence t){
        if( t != null) {
            t.setRaisonDemandeAbsence(raisonDemandeAbsenceService.findOrSave(t.getRaisonDemandeAbsence()));
        }
    }



    public List<DemandeAbsence> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<DemandeAbsence>> getToBeSavedAndToBeDeleted(List<DemandeAbsence> oldList, List<DemandeAbsence> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<DemandeAbsence> importExcel(MultipartFile file) {
        return null;
    }








    @Autowired
    private RaisonDemandeAbsenceUtilisateurService raisonDemandeAbsenceService ;

    private @Autowired DemandeAbsenceDao dao;


}
