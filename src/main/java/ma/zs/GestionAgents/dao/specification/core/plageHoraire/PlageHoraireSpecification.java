package  ma.zs.GestionAgents.dao.specification.core.plageHoraire;

import ma.zs.GestionAgents.dao.criteria.core.plageHoraire.PlageHoraireCriteria;
import ma.zs.GestionAgents.bean.core.plageHoraire.PlageHoraire;
import ma.zs.GestionAgents.zynerator.specification.AbstractSpecification;


public class PlageHoraireSpecification extends  AbstractSpecification<PlageHoraireCriteria, PlageHoraire>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("ref", criteria.getRef(),criteria.getRefLike());
        addPredicate("typeHoraire", criteria.getTypeHoraire(),criteria.getTypeHoraireLike());
        addPredicate("heureDebut", criteria.getHeureDebut(), criteria.getHeureDebutFrom(), criteria.getHeureDebutTo());
        addPredicate("heureFin", criteria.getHeureFin(), criteria.getHeureFinFrom(), criteria.getHeureFinTo());
    }

    public PlageHoraireSpecification(PlageHoraireCriteria criteria) {
        super(criteria);
    }

    public PlageHoraireSpecification(PlageHoraireCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
