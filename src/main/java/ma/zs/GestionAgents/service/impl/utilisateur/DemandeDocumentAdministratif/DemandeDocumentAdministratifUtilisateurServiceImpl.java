package ma.zs.GestionAgents.service.impl.utilisateur.DemandeDocumentAdministratif;


import ma.zs.GestionAgents.zynerator.exception.EntityNotFoundException;
import ma.zs.GestionAgents.bean.core.DemandeDocumentAdministratif.DemandeDocumentAdministratif;
import ma.zs.GestionAgents.dao.criteria.core.DemandeDocumentAdministratif.DemandeDocumentAdministratifCriteria;
import ma.zs.GestionAgents.dao.facade.core.DemandeDocumentAdministratif.DemandeDocumentAdministratifDao;
import ma.zs.GestionAgents.dao.specification.core.DemandeDocumentAdministratif.DemandeDocumentAdministratifSpecification;
import ma.zs.GestionAgents.service.facade.utilisateur.DemandeDocumentAdministratif.DemandeDocumentAdministratifUtilisateurService;
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
public class DemandeDocumentAdministratifUtilisateurServiceImpl implements DemandeDocumentAdministratifUtilisateurService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public DemandeDocumentAdministratif update(DemandeDocumentAdministratif t) {
        DemandeDocumentAdministratif loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{DemandeDocumentAdministratif.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public DemandeDocumentAdministratif findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public DemandeDocumentAdministratif findOrSave(DemandeDocumentAdministratif t) {
        if (t != null) {
            DemandeDocumentAdministratif result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<DemandeDocumentAdministratif> importData(List<DemandeDocumentAdministratif> items) {
        List<DemandeDocumentAdministratif> list = new ArrayList<>();
        for (DemandeDocumentAdministratif t : items) {
            DemandeDocumentAdministratif founded = findByReferenceEntity(t);
			if (founded == null) {
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<DemandeDocumentAdministratif> findAll() {
        return dao.findAll();
    }

    public List<DemandeDocumentAdministratif> findByCriteria(DemandeDocumentAdministratifCriteria criteria) {
        List<DemandeDocumentAdministratif> content = null;
        if (criteria != null) {
            DemandeDocumentAdministratifSpecification mySpecification = constructSpecification(criteria);
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


    private DemandeDocumentAdministratifSpecification constructSpecification(DemandeDocumentAdministratifCriteria criteria) {
        DemandeDocumentAdministratifSpecification mySpecification =  (DemandeDocumentAdministratifSpecification) RefelexivityUtil.constructObjectUsingOneParam(DemandeDocumentAdministratifSpecification.class, criteria);
        return mySpecification;
    }

    public List<DemandeDocumentAdministratif> findPaginatedByCriteria(DemandeDocumentAdministratifCriteria criteria, int page, int pageSize, String order, String sortField) {
        DemandeDocumentAdministratifSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(DemandeDocumentAdministratifCriteria criteria) {
        DemandeDocumentAdministratifSpecification mySpecification = constructSpecification(criteria);
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
    public int delete(DemandeDocumentAdministratif t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<DemandeDocumentAdministratif> delete(List<DemandeDocumentAdministratif> list) {
		List<DemandeDocumentAdministratif> result = new ArrayList();
        if (list != null) {
            for (DemandeDocumentAdministratif t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public DemandeDocumentAdministratif create(DemandeDocumentAdministratif t) {
        DemandeDocumentAdministratif loaded = findByReferenceEntity(t);
        DemandeDocumentAdministratif saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<DemandeDocumentAdministratif> create(List<DemandeDocumentAdministratif> ts) {
        List<DemandeDocumentAdministratif> result = new ArrayList<>();
        if (ts != null) {
            for (DemandeDocumentAdministratif t : ts) {
				DemandeDocumentAdministratif created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public DemandeDocumentAdministratif findWithAssociatedLists(Long id){
        DemandeDocumentAdministratif result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<DemandeDocumentAdministratif> update(List<DemandeDocumentAdministratif> ts, boolean createIfNotExist) {
        List<DemandeDocumentAdministratif> result = new ArrayList<>();
        if (ts != null) {
            for (DemandeDocumentAdministratif t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    DemandeDocumentAdministratif loadedItem = dao.findById(t.getId()).orElse(null);
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





    public DemandeDocumentAdministratif findByReferenceEntity(DemandeDocumentAdministratif t){
        return t==null? null : dao.findByRef(t.getRef());
    }



    public List<DemandeDocumentAdministratif> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<DemandeDocumentAdministratif>> getToBeSavedAndToBeDeleted(List<DemandeDocumentAdministratif> oldList, List<DemandeDocumentAdministratif> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<DemandeDocumentAdministratif> importExcel(MultipartFile file) {
        return null;
    }









    private @Autowired DemandeDocumentAdministratifDao dao;


}
