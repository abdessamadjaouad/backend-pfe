package  ma.zs.GestionAgents.dao.specification.core.BioManager;

import ma.zs.GestionAgents.dao.criteria.core.BioManager.BadgeNfcCriteria;
import ma.zs.GestionAgents.bean.core.BioManager.BadgeNfc;
import ma.zs.GestionAgents.zynerator.specification.AbstractSpecification;


public class BadgeNfcSpecification extends  AbstractSpecification<BadgeNfcCriteria, BadgeNfc>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("ref", criteria.getRef(),criteria.getRefLike());
        addPredicate("matricule", criteria.getMatricule(),criteria.getMatriculeLike());
        addPredicate("location", criteria.getLocation(),criteria.getLocationLike());
        addPredicate("status", criteria.getStatus(),criteria.getStatusLike());
    }

    public BadgeNfcSpecification(BadgeNfcCriteria criteria) {
        super(criteria);
    }

    public BadgeNfcSpecification(BadgeNfcCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
