package  ma.zs.GestionAgents.dao.specification.core.Service;

import ma.zs.GestionAgents.dao.criteria.core.Service.EntiteAdministrativeCriteria;
import ma.zs.GestionAgents.bean.core.Service.EntiteAdministrative;
import ma.zs.GestionAgents.zynerator.specification.AbstractSpecification;


public class EntiteAdministrativeSpecification extends  AbstractSpecification<EntiteAdministrativeCriteria, EntiteAdministrative>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("ref", criteria.getRef(),criteria.getRefLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
        addPredicateFk("chefService","id", criteria.getChefService()==null?null:criteria.getChefService().getId());
        addPredicateFk("chefService","id", criteria.getChefServices());
        addPredicateFk("chefService","ref", criteria.getChefService()==null?null:criteria.getChefService().getRef());
    }

    public EntiteAdministrativeSpecification(EntiteAdministrativeCriteria criteria) {
        super(criteria);
    }

    public EntiteAdministrativeSpecification(EntiteAdministrativeCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
