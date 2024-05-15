package  ma.zs.GestionAgents.dao.specification.core.Absence;

import ma.zs.GestionAgents.dao.criteria.core.Absence.AbsenceCriteria;
import ma.zs.GestionAgents.bean.core.Absence.Absence;
import ma.zs.GestionAgents.zynerator.specification.AbstractSpecification;


public class AbsenceSpecification extends  AbstractSpecification<AbsenceCriteria, Absence>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("ref", criteria.getRef(),criteria.getRefLike());
        addPredicate("dateDebut", criteria.getDateDebut(), criteria.getDateDebutFrom(), criteria.getDateDebutTo());
        addPredicate("dateFin", criteria.getDateFin(), criteria.getDateFinFrom(), criteria.getDateFinTo());
        addPredicate("typeAbsence", criteria.getTypeAbsence(),criteria.getTypeAbsenceLike());
    }

    public AbsenceSpecification(AbsenceCriteria criteria) {
        super(criteria);
    }

    public AbsenceSpecification(AbsenceCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
