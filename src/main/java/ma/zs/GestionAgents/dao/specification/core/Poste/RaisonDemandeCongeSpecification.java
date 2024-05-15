package  ma.zs.GestionAgents.dao.specification.core.Poste;

import ma.zs.GestionAgents.dao.criteria.core.Poste.RaisonDemandeCongeCriteria;
import ma.zs.GestionAgents.bean.core.Poste.RaisonDemandeConge;
import ma.zs.GestionAgents.zynerator.specification.AbstractSpecification;


public class RaisonDemandeCongeSpecification extends  AbstractSpecification<RaisonDemandeCongeCriteria, RaisonDemandeConge>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("ref", criteria.getRef(),criteria.getRefLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
    }

    public RaisonDemandeCongeSpecification(RaisonDemandeCongeCriteria criteria) {
        super(criteria);
    }

    public RaisonDemandeCongeSpecification(RaisonDemandeCongeCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
