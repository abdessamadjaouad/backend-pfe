package  ma.zs.GestionAgents.dao.specification.core.DemandeConge;

import ma.zs.GestionAgents.dao.criteria.core.DemandeConge.DemandeCongeCriteria;
import ma.zs.GestionAgents.bean.core.DemandeConge.DemandeConge;
import ma.zs.GestionAgents.zynerator.specification.AbstractSpecification;


public class DemandeCongeSpecification extends  AbstractSpecification<DemandeCongeCriteria, DemandeConge>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("ref", criteria.getRef(),criteria.getRefLike());
        addPredicate("dateDebut", criteria.getDateDebut(), criteria.getDateDebutFrom(), criteria.getDateDebutTo());
        addPredicate("dateFin", criteria.getDateFin(), criteria.getDateFinFrom(), criteria.getDateFinTo());
        addPredicateFk("raisonDemandeConge","id", criteria.getRaisonDemandeConge()==null?null:criteria.getRaisonDemandeConge().getId());
        addPredicateFk("raisonDemandeConge","id", criteria.getRaisonDemandeConges());
        addPredicateFk("raisonDemandeConge","ref", criteria.getRaisonDemandeConge()==null?null:criteria.getRaisonDemandeConge().getRef());
    }

    public DemandeCongeSpecification(DemandeCongeCriteria criteria) {
        super(criteria);
    }

    public DemandeCongeSpecification(DemandeCongeCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
