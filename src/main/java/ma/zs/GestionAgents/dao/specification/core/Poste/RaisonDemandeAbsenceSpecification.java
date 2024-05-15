package  ma.zs.GestionAgents.dao.specification.core.Poste;

import ma.zs.GestionAgents.dao.criteria.core.Poste.RaisonDemandeAbsenceCriteria;
import ma.zs.GestionAgents.bean.core.Poste.RaisonDemandeAbsence;
import ma.zs.GestionAgents.zynerator.specification.AbstractSpecification;


public class RaisonDemandeAbsenceSpecification extends  AbstractSpecification<RaisonDemandeAbsenceCriteria, RaisonDemandeAbsence>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("ref", criteria.getRef(),criteria.getRefLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
    }

    public RaisonDemandeAbsenceSpecification(RaisonDemandeAbsenceCriteria criteria) {
        super(criteria);
    }

    public RaisonDemandeAbsenceSpecification(RaisonDemandeAbsenceCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
