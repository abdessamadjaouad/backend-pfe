package  ma.zs.GestionAgents.dao.specification.core.Pointage;

import ma.zs.GestionAgents.dao.criteria.core.Pointage.PointageCriteria;
import ma.zs.GestionAgents.bean.core.Pointage.Pointage;
import ma.zs.GestionAgents.zynerator.specification.AbstractSpecification;


public class PointageSpecification extends  AbstractSpecification<PointageCriteria, Pointage>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("ref", criteria.getRef(),criteria.getRefLike());
        addPredicate("heureEntree", criteria.getHeureEntree(), criteria.getHeureEntreeFrom(), criteria.getHeureEntreeTo());
        addPredicate("heureSortie", criteria.getHeureSortie(), criteria.getHeureSortieFrom(), criteria.getHeureSortieTo());
        addPredicateBool("presence", criteria.getPresence());
        addPredicateInt("pointageSens", criteria.getPointageSens(), criteria.getPointageSensMin(), criteria.getPointageSensMax());
        addPredicateFk("agent","id", criteria.getAgent()==null?null:criteria.getAgent().getId());
        addPredicateFk("agent","id", criteria.getAgents());
        addPredicateFk("agent","ref", criteria.getAgent()==null?null:criteria.getAgent().getRef());
        addPredicateFk("badgeNfc","id", criteria.getBadgeNfc()==null?null:criteria.getBadgeNfc().getId());
        addPredicateFk("badgeNfc","id", criteria.getBadgeNfcs());
        addPredicateFk("badgeNfc","ref", criteria.getBadgeNfc()==null?null:criteria.getBadgeNfc().getRef());
    }

    public PointageSpecification(PointageCriteria criteria) {
        super(criteria);
    }

    public PointageSpecification(PointageCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
