package ma.zs.GestionAgents.service.impl.utilisateur.Poste;


import ma.zs.GestionAgents.zynerator.exception.EntityNotFoundException;
import ma.zs.GestionAgents.bean.core.Poste.RaisonDemandeAbsence;
import ma.zs.GestionAgents.dao.criteria.core.Poste.RaisonDemandeAbsenceCriteria;
import ma.zs.GestionAgents.dao.facade.core.Poste.RaisonDemandeAbsenceDao;
import ma.zs.GestionAgents.dao.specification.core.Poste.RaisonDemandeAbsenceSpecification;
import ma.zs.GestionAgents.service.facade.utilisateur.Poste.RaisonDemandeAbsenceUtilisateurService;
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
public class RaisonDemandeAbsenceUtilisateurServiceImpl implements RaisonDemandeAbsenceUtilisateurService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public RaisonDemandeAbsence update(RaisonDemandeAbsence t) {
        RaisonDemandeAbsence loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{RaisonDemandeAbsence.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public RaisonDemandeAbsence findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public RaisonDemandeAbsence findOrSave(RaisonDemandeAbsence t) {
        if (t != null) {
            RaisonDemandeAbsence result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<RaisonDemandeAbsence> importData(List<RaisonDemandeAbsence> items) {
        List<RaisonDemandeAbsence> list = new ArrayList<>();
        for (RaisonDemandeAbsence t : items) {
            RaisonDemandeAbsence founded = findByReferenceEntity(t);
			if (founded == null) {
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<RaisonDemandeAbsence> findAll() {
        return dao.findAll();
    }

    public List<RaisonDemandeAbsence> findByCriteria(RaisonDemandeAbsenceCriteria criteria) {
        List<RaisonDemandeAbsence> content = null;
        if (criteria != null) {
            RaisonDemandeAbsenceSpecification mySpecification = constructSpecification(criteria);
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


    private RaisonDemandeAbsenceSpecification constructSpecification(RaisonDemandeAbsenceCriteria criteria) {
        RaisonDemandeAbsenceSpecification mySpecification =  (RaisonDemandeAbsenceSpecification) RefelexivityUtil.constructObjectUsingOneParam(RaisonDemandeAbsenceSpecification.class, criteria);
        return mySpecification;
    }

    public List<RaisonDemandeAbsence> findPaginatedByCriteria(RaisonDemandeAbsenceCriteria criteria, int page, int pageSize, String order, String sortField) {
        RaisonDemandeAbsenceSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(RaisonDemandeAbsenceCriteria criteria) {
        RaisonDemandeAbsenceSpecification mySpecification = constructSpecification(criteria);
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
    public int delete(RaisonDemandeAbsence t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<RaisonDemandeAbsence> delete(List<RaisonDemandeAbsence> list) {
		List<RaisonDemandeAbsence> result = new ArrayList();
        if (list != null) {
            for (RaisonDemandeAbsence t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public RaisonDemandeAbsence create(RaisonDemandeAbsence t) {
        RaisonDemandeAbsence loaded = findByReferenceEntity(t);
        RaisonDemandeAbsence saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<RaisonDemandeAbsence> create(List<RaisonDemandeAbsence> ts) {
        List<RaisonDemandeAbsence> result = new ArrayList<>();
        if (ts != null) {
            for (RaisonDemandeAbsence t : ts) {
				RaisonDemandeAbsence created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public RaisonDemandeAbsence findWithAssociatedLists(Long id){
        RaisonDemandeAbsence result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<RaisonDemandeAbsence> update(List<RaisonDemandeAbsence> ts, boolean createIfNotExist) {
        List<RaisonDemandeAbsence> result = new ArrayList<>();
        if (ts != null) {
            for (RaisonDemandeAbsence t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    RaisonDemandeAbsence loadedItem = dao.findById(t.getId()).orElse(null);
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





    public RaisonDemandeAbsence findByReferenceEntity(RaisonDemandeAbsence t){
        return t==null? null : dao.findByRef(t.getRef());
    }



    public List<RaisonDemandeAbsence> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<RaisonDemandeAbsence>> getToBeSavedAndToBeDeleted(List<RaisonDemandeAbsence> oldList, List<RaisonDemandeAbsence> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<RaisonDemandeAbsence> importExcel(MultipartFile file) {
        return null;
    }









    private @Autowired RaisonDemandeAbsenceDao dao;


}
