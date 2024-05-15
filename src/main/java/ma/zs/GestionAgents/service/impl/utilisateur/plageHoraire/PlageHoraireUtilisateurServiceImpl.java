package ma.zs.GestionAgents.service.impl.utilisateur.plageHoraire;


import ma.zs.GestionAgents.zynerator.exception.EntityNotFoundException;
import ma.zs.GestionAgents.bean.core.plageHoraire.PlageHoraire;
import ma.zs.GestionAgents.dao.criteria.core.plageHoraire.PlageHoraireCriteria;
import ma.zs.GestionAgents.dao.facade.core.plageHoraire.PlageHoraireDao;
import ma.zs.GestionAgents.dao.specification.core.plageHoraire.PlageHoraireSpecification;
import ma.zs.GestionAgents.service.facade.utilisateur.plageHoraire.PlageHoraireUtilisateurService;
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
public class PlageHoraireUtilisateurServiceImpl implements PlageHoraireUtilisateurService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public PlageHoraire update(PlageHoraire t) {
        PlageHoraire loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{PlageHoraire.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public PlageHoraire findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public PlageHoraire findOrSave(PlageHoraire t) {
        if (t != null) {
            PlageHoraire result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<PlageHoraire> importData(List<PlageHoraire> items) {
        List<PlageHoraire> list = new ArrayList<>();
        for (PlageHoraire t : items) {
            PlageHoraire founded = findByReferenceEntity(t);
			if (founded == null) {
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<PlageHoraire> findAll() {
        return dao.findAll();
    }

    public List<PlageHoraire> findByCriteria(PlageHoraireCriteria criteria) {
        List<PlageHoraire> content = null;
        if (criteria != null) {
            PlageHoraireSpecification mySpecification = constructSpecification(criteria);
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


    private PlageHoraireSpecification constructSpecification(PlageHoraireCriteria criteria) {
        PlageHoraireSpecification mySpecification =  (PlageHoraireSpecification) RefelexivityUtil.constructObjectUsingOneParam(PlageHoraireSpecification.class, criteria);
        return mySpecification;
    }

    public List<PlageHoraire> findPaginatedByCriteria(PlageHoraireCriteria criteria, int page, int pageSize, String order, String sortField) {
        PlageHoraireSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(PlageHoraireCriteria criteria) {
        PlageHoraireSpecification mySpecification = constructSpecification(criteria);
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
    public int delete(PlageHoraire t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<PlageHoraire> delete(List<PlageHoraire> list) {
		List<PlageHoraire> result = new ArrayList();
        if (list != null) {
            for (PlageHoraire t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public PlageHoraire create(PlageHoraire t) {
        PlageHoraire loaded = findByReferenceEntity(t);
        PlageHoraire saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<PlageHoraire> create(List<PlageHoraire> ts) {
        List<PlageHoraire> result = new ArrayList<>();
        if (ts != null) {
            for (PlageHoraire t : ts) {
				PlageHoraire created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public PlageHoraire findWithAssociatedLists(Long id){
        PlageHoraire result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<PlageHoraire> update(List<PlageHoraire> ts, boolean createIfNotExist) {
        List<PlageHoraire> result = new ArrayList<>();
        if (ts != null) {
            for (PlageHoraire t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    PlageHoraire loadedItem = dao.findById(t.getId()).orElse(null);
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





    public PlageHoraire findByReferenceEntity(PlageHoraire t){
        return t==null? null : dao.findByRef(t.getRef());
    }



    public List<PlageHoraire> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<PlageHoraire>> getToBeSavedAndToBeDeleted(List<PlageHoraire> oldList, List<PlageHoraire> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<PlageHoraire> importExcel(MultipartFile file) {
        return null;
    }









    private @Autowired PlageHoraireDao dao;


}
