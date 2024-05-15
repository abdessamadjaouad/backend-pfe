package ma.zs.GestionAgents.service.impl.admin.Poste;


import ma.zs.GestionAgents.zynerator.exception.EntityNotFoundException;
import ma.zs.GestionAgents.bean.core.Poste.Poste;
import ma.zs.GestionAgents.dao.criteria.core.Poste.PosteCriteria;
import ma.zs.GestionAgents.dao.facade.core.Poste.PosteDao;
import ma.zs.GestionAgents.dao.specification.core.Poste.PosteSpecification;
import ma.zs.GestionAgents.service.facade.admin.Poste.PosteAdminService;
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
public class PosteAdminServiceImpl implements PosteAdminService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Poste update(Poste t) {
        Poste loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Poste.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public Poste findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Poste findOrSave(Poste t) {
        if (t != null) {
            Poste result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<Poste> importData(List<Poste> items) {
        List<Poste> list = new ArrayList<>();
        for (Poste t : items) {
            Poste founded = findByReferenceEntity(t);
			if (founded == null) {
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<Poste> findAll() {
        return dao.findAll();
    }

    public List<Poste> findByCriteria(PosteCriteria criteria) {
        List<Poste> content = null;
        if (criteria != null) {
            PosteSpecification mySpecification = constructSpecification(criteria);
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


    private PosteSpecification constructSpecification(PosteCriteria criteria) {
        PosteSpecification mySpecification =  (PosteSpecification) RefelexivityUtil.constructObjectUsingOneParam(PosteSpecification.class, criteria);
        return mySpecification;
    }

    public List<Poste> findPaginatedByCriteria(PosteCriteria criteria, int page, int pageSize, String order, String sortField) {
        PosteSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(PosteCriteria criteria) {
        PosteSpecification mySpecification = constructSpecification(criteria);
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
    public int delete(Poste t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Poste> delete(List<Poste> list) {
		List<Poste> result = new ArrayList();
        if (list != null) {
            for (Poste t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Poste create(Poste t) {
        Poste loaded = findByReferenceEntity(t);
        Poste saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Poste> create(List<Poste> ts) {
        List<Poste> result = new ArrayList<>();
        if (ts != null) {
            for (Poste t : ts) {
				Poste created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public Poste findWithAssociatedLists(Long id){
        Poste result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Poste> update(List<Poste> ts, boolean createIfNotExist) {
        List<Poste> result = new ArrayList<>();
        if (ts != null) {
            for (Poste t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Poste loadedItem = dao.findById(t.getId()).orElse(null);
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





    public Poste findByReferenceEntity(Poste t){
        return t==null? null : dao.findByRef(t.getRef());
    }



    public List<Poste> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<Poste>> getToBeSavedAndToBeDeleted(List<Poste> oldList, List<Poste> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<Poste> importExcel(MultipartFile file) {
        return null;
    }









    private @Autowired PosteDao dao;


}
