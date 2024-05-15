package ma.zs.GestionAgents.zynerator.service;

import ma.zs.GestionAgents.zynerator.bean.BaseEntity;
import ma.zs.GestionAgents.zynerator.criteria.BaseCriteria;
import ma.zs.GestionAgents.zynerator.repository.AbstractRepository;

public abstract class AbstractReferentielServiceImpl<T extends BaseEntity, CRITERIA extends BaseCriteria, REPO extends AbstractRepository<T, Long>> extends AbstractServiceImpl<T, CRITERIA, REPO> {

    public AbstractReferentielServiceImpl(REPO dao) {
        super(dao);
    }

}
