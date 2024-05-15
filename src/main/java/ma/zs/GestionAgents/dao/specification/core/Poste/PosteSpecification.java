package  ma.zs.GestionAgents.dao.specification.core.Poste;

import ma.zs.GestionAgents.dao.criteria.core.Poste.PosteCriteria;
import ma.zs.GestionAgents.bean.core.Poste.Poste;
import ma.zs.GestionAgents.zynerator.specification.AbstractSpecification;


public class PosteSpecification extends  AbstractSpecification<PosteCriteria, Poste>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("ref", criteria.getRef(),criteria.getRefLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
    }

    public PosteSpecification(PosteCriteria criteria) {
        super(criteria);
    }

    public PosteSpecification(PosteCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
