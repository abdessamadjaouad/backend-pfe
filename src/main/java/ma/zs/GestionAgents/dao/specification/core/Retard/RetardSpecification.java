package  ma.zs.GestionAgents.dao.specification.core.Retard;

import ma.zs.GestionAgents.dao.criteria.core.Retard.RetardCriteria;
import ma.zs.GestionAgents.bean.core.Retard.Retard;
import ma.zs.GestionAgents.zynerator.specification.AbstractSpecification;


public class RetardSpecification extends  AbstractSpecification<RetardCriteria, Retard>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("ref", criteria.getRef(),criteria.getRefLike());
        addPredicate("dateRetart", criteria.getDateRetart(), criteria.getDateRetartFrom(), criteria.getDateRetartTo());
        addPredicateLong("duree", criteria.getDuree(), criteria.getDureeMin(), criteria.getDureeMax());
        addPredicateFk("agent","id", criteria.getAgent()==null?null:criteria.getAgent().getId());
        addPredicateFk("agent","id", criteria.getAgents());
        addPredicateFk("agent","ref", criteria.getAgent()==null?null:criteria.getAgent().getRef());
    }

    public RetardSpecification(RetardCriteria criteria) {
        super(criteria);
    }

    public RetardSpecification(RetardCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
