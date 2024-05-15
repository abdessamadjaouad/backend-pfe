package  ma.zs.GestionAgents.dao.specification.core.DemandeAbsence;

import ma.zs.GestionAgents.dao.criteria.core.DemandeAbsence.DemandeAbsenceCriteria;
import ma.zs.GestionAgents.bean.core.DemandeAbsence.DemandeAbsence;
import ma.zs.GestionAgents.zynerator.specification.AbstractSpecification;


public class DemandeAbsenceSpecification extends  AbstractSpecification<DemandeAbsenceCriteria, DemandeAbsence>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("ref", criteria.getRef(),criteria.getRefLike());
        addPredicate("contenu", criteria.getContenu(),criteria.getContenuLike());
        addPredicateFk("raisonDemandeAbsence","id", criteria.getRaisonDemandeAbsence()==null?null:criteria.getRaisonDemandeAbsence().getId());
        addPredicateFk("raisonDemandeAbsence","id", criteria.getRaisonDemandeAbsences());
        addPredicateFk("raisonDemandeAbsence","ref", criteria.getRaisonDemandeAbsence()==null?null:criteria.getRaisonDemandeAbsence().getRef());
    }

    public DemandeAbsenceSpecification(DemandeAbsenceCriteria criteria) {
        super(criteria);
    }

    public DemandeAbsenceSpecification(DemandeAbsenceCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
