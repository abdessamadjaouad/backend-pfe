package  ma.zs.GestionAgents.dao.specification.core.Conge;

import ma.zs.GestionAgents.dao.criteria.core.Conge.CongeCriteria;
import ma.zs.GestionAgents.bean.core.Conge.Conge;
import ma.zs.GestionAgents.zynerator.specification.AbstractSpecification;


public class CongeSpecification extends  AbstractSpecification<CongeCriteria, Conge>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("ref", criteria.getRef(),criteria.getRefLike());
        addPredicate("dateDebut", criteria.getDateDebut(), criteria.getDateDebutFrom(), criteria.getDateDebutTo());
        addPredicate("dateFin", criteria.getDateFin(), criteria.getDateFinFrom(), criteria.getDateFinTo());
        addPredicate("typeConge", criteria.getTypeConge(),criteria.getTypeCongeLike());
    }

    public CongeSpecification(CongeCriteria criteria) {
        super(criteria);
    }

    public CongeSpecification(CongeCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
