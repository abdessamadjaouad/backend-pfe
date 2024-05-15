package ma.zs.GestionAgents.service.impl.utilisateur.Agent;


import ma.zs.GestionAgents.zynerator.exception.EntityNotFoundException;
import ma.zs.GestionAgents.bean.core.Agent.Agent;
import ma.zs.GestionAgents.dao.criteria.core.Agent.AgentCriteria;
import ma.zs.GestionAgents.dao.facade.core.Agent.AgentDao;
import ma.zs.GestionAgents.dao.specification.core.Agent.AgentSpecification;
import ma.zs.GestionAgents.service.facade.utilisateur.Agent.AgentUtilisateurService;
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

import ma.zs.GestionAgents.service.facade.utilisateur.plageHoraire.PlageHoraireUtilisateurService ;
import ma.zs.GestionAgents.bean.core.plageHoraire.PlageHoraire ;
import ma.zs.GestionAgents.service.facade.utilisateur.Service.EntiteAdministrativeUtilisateurService ;
import ma.zs.GestionAgents.bean.core.Service.EntiteAdministrative ;
import ma.zs.GestionAgents.service.facade.utilisateur.Poste.PosteUtilisateurService ;
import ma.zs.GestionAgents.bean.core.Poste.Poste ;

import java.util.List;
@Service
public class AgentUtilisateurServiceImpl implements AgentUtilisateurService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Agent update(Agent t) {
        Agent loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Agent.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public Agent findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Agent findOrSave(Agent t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            Agent result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<Agent> importData(List<Agent> items) {
        List<Agent> list = new ArrayList<>();
        for (Agent t : items) {
            Agent founded = findByReferenceEntity(t);
			if (founded == null) {
				findOrSaveAssociatedObject(t);
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<Agent> findAll() {
        return dao.findAll();
    }

    public List<Agent> findByCriteria(AgentCriteria criteria) {
        List<Agent> content = null;
        if (criteria != null) {
            AgentSpecification mySpecification = constructSpecification(criteria);
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


    private AgentSpecification constructSpecification(AgentCriteria criteria) {
        AgentSpecification mySpecification =  (AgentSpecification) RefelexivityUtil.constructObjectUsingOneParam(AgentSpecification.class, criteria);
        return mySpecification;
    }

    public List<Agent> findPaginatedByCriteria(AgentCriteria criteria, int page, int pageSize, String order, String sortField) {
        AgentSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(AgentCriteria criteria) {
        AgentSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<Agent> findByEntiteAdministrativeId(Long id){
        return dao.findByEntiteAdministrativeId(id);
    }
    public int deleteByEntiteAdministrativeId(Long id){
        return dao.deleteByEntiteAdministrativeId(id);
    }
    public long countByEntiteAdministrativeRef(String ref){
        return dao.countByEntiteAdministrativeRef(ref);
    }
    public List<Agent> findByPosteId(Long id){
        return dao.findByPosteId(id);
    }
    public int deleteByPosteId(Long id){
        return dao.deleteByPosteId(id);
    }
    public long countByPosteRef(String ref){
        return dao.countByPosteRef(ref);
    }
    public List<Agent> findByPlageHoraireId(Long id){
        return dao.findByPlageHoraireId(id);
    }
    public int deleteByPlageHoraireId(Long id){
        return dao.deleteByPlageHoraireId(id);
    }
    public long countByPlageHoraireRef(String ref){
        return dao.countByPlageHoraireRef(ref);
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
    public int delete(Agent t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Agent> delete(List<Agent> list) {
		List<Agent> result = new ArrayList();
        if (list != null) {
            for (Agent t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Agent create(Agent t) {
        Agent loaded = findByReferenceEntity(t);
        Agent saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Agent> create(List<Agent> ts) {
        List<Agent> result = new ArrayList<>();
        if (ts != null) {
            for (Agent t : ts) {
				Agent created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public Agent findWithAssociatedLists(Long id){
        Agent result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Agent> update(List<Agent> ts, boolean createIfNotExist) {
        List<Agent> result = new ArrayList<>();
        if (ts != null) {
            for (Agent t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Agent loadedItem = dao.findById(t.getId()).orElse(null);
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





    public Agent findByReferenceEntity(Agent t){
        return t==null? null : dao.findByRef(t.getRef());
    }
    public void findOrSaveAssociatedObject(Agent t){
        if( t != null) {
            t.setEntiteAdministrative(entiteAdministrativeService.findOrSave(t.getEntiteAdministrative()));
            t.setPoste(posteService.findOrSave(t.getPoste()));
            t.setPlageHoraire(plageHoraireService.findOrSave(t.getPlageHoraire()));
        }
    }



    public List<Agent> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<Agent>> getToBeSavedAndToBeDeleted(List<Agent> oldList, List<Agent> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<Agent> importExcel(MultipartFile file) {
        return null;
    }








    @Autowired
    private PlageHoraireUtilisateurService plageHoraireService ;
    @Autowired
    private EntiteAdministrativeUtilisateurService entiteAdministrativeService ;
    @Autowired
    private PosteUtilisateurService posteService ;

    private @Autowired AgentDao dao;


}
