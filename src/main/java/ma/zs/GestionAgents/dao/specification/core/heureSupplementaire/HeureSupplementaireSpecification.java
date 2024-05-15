package  ma.zs.GestionAgents.dao.specification.core.heureSupplementaire;

import ma.zs.GestionAgents.dao.criteria.core.heureSupplementaire.HeureSupplementaireCriteria;
import ma.zs.GestionAgents.bean.core.heureSupplementaire.HeureSupplementaire;
import ma.zs.GestionAgents.zynerator.specification.AbstractSpecification;


public class HeureSupplementaireSpecification extends  AbstractSpecification<HeureSupplementaireCriteria, HeureSupplementaire>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("ref", criteria.getRef(),criteria.getRefLike());
        addPredicateLong("nbrHeure", criteria.getNbrHeure(), criteria.getNbrHeureMin(), criteria.getNbrHeureMax());
    }

    public HeureSupplementaireSpecification(HeureSupplementaireCriteria criteria) {
        super(criteria);
    }

    public HeureSupplementaireSpecification(HeureSupplementaireCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
