package ma.zs.GestionAgents.service.impl.utilisateur.Pointage;


import ma.zs.GestionAgents.zynerator.exception.EntityNotFoundException;
import ma.zs.GestionAgents.bean.core.Pointage.Pointage;
import ma.zs.GestionAgents.dao.criteria.core.Pointage.PointageCriteria;
import ma.zs.GestionAgents.dao.facade.core.Pointage.PointageDao;
import ma.zs.GestionAgents.dao.specification.core.Pointage.PointageSpecification;
import ma.zs.GestionAgents.service.facade.utilisateur.Pointage.PointageUtilisateurService;
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

import ma.zs.GestionAgents.service.facade.utilisateur.Agent.AgentUtilisateurService ;
import ma.zs.GestionAgents.bean.core.Agent.Agent ;
import ma.zs.GestionAgents.service.facade.utilisateur.BioManager.BadgeNfcUtilisateurService ;
import ma.zs.GestionAgents.bean.core.BioManager.BadgeNfc ;

import java.util.List;
@Service
public class PointageUtilisateurServiceImpl implements PointageUtilisateurService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Pointage update(Pointage t) {
        Pointage loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Pointage.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public Pointage findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Pointage findOrSave(Pointage t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            Pointage result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<Pointage> importData(List<Pointage> items) {
        List<Pointage> list = new ArrayList<>();
        for (Pointage t : items) {
            Pointage founded = findByReferenceEntity(t);
			if (founded == null) {
				findOrSaveAssociatedObject(t);
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<Pointage> findAll() {
        return dao.findAll();
    }

    public List<Pointage> findByCriteria(PointageCriteria criteria) {
        List<Pointage> content = null;
        if (criteria != null) {
            PointageSpecification mySpecification = constructSpecification(criteria);
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


    private PointageSpecification constructSpecification(PointageCriteria criteria) {
        PointageSpecification mySpecification =  (PointageSpecification) RefelexivityUtil.constructObjectUsingOneParam(PointageSpecification.class, criteria);
        return mySpecification;
    }

    public List<Pointage> findPaginatedByCriteria(PointageCriteria criteria, int page, int pageSize, String order, String sortField) {
        PointageSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(PointageCriteria criteria) {
        PointageSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<Pointage> findByAgentId(Long id){
        return dao.findByAgentId(id);
    }
    public int deleteByAgentId(Long id){
        return dao.deleteByAgentId(id);
    }
    public long countByAgentRef(String ref){
        return dao.countByAgentRef(ref);
    }
    public List<Pointage> findByBadgeNfcId(Long id){
        return dao.findByBadgeNfcId(id);
    }
    public int deleteByBadgeNfcId(Long id){
        return dao.deleteByBadgeNfcId(id);
    }
    public long countByBadgeNfcRef(String ref){
        return dao.countByBadgeNfcRef(ref);
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
    public int delete(Pointage t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Pointage> delete(List<Pointage> list) {
		List<Pointage> result = new ArrayList();
        if (list != null) {
            for (Pointage t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Pointage create(Pointage t) {
        Pointage loaded = findByReferenceEntity(t);
        Pointage saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Pointage> create(List<Pointage> ts) {
        List<Pointage> result = new ArrayList<>();
        if (ts != null) {
            for (Pointage t : ts) {
				Pointage created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public Pointage findWithAssociatedLists(Long id){
        Pointage result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Pointage> update(List<Pointage> ts, boolean createIfNotExist) {
        List<Pointage> result = new ArrayList<>();
        if (ts != null) {
            for (Pointage t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Pointage loadedItem = dao.findById(t.getId()).orElse(null);
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





    public Pointage findByReferenceEntity(Pointage t){
        return t==null? null : dao.findByRef(t.getRef());
    }
    public void findOrSaveAssociatedObject(Pointage t){
        if( t != null) {
            t.setAgent(agentService.findOrSave(t.getAgent()));
            t.setBadgeNfc(badgeNfcService.findOrSave(t.getBadgeNfc()));
        }
    }



    public List<Pointage> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<Pointage>> getToBeSavedAndToBeDeleted(List<Pointage> oldList, List<Pointage> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<Pointage> importExcel(MultipartFile file) {
        return null;
    }








    @Autowired
    private AgentUtilisateurService agentService ;
    @Autowired
    private BadgeNfcUtilisateurService badgeNfcService ;

    private @Autowired PointageDao dao;


}
