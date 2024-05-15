package  ma.zs.GestionAgents.dao.specification.core.DemandeDocumentAdministratif;

import ma.zs.GestionAgents.dao.criteria.core.DemandeDocumentAdministratif.DemandeDocumentAdministratifCriteria;
import ma.zs.GestionAgents.bean.core.DemandeDocumentAdministratif.DemandeDocumentAdministratif;
import ma.zs.GestionAgents.zynerator.specification.AbstractSpecification;


public class DemandeDocumentAdministratifSpecification extends  AbstractSpecification<DemandeDocumentAdministratifCriteria, DemandeDocumentAdministratif>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("ref", criteria.getRef(),criteria.getRefLike());
        addPredicate("type", criteria.getType(),criteria.getTypeLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
    }

    public DemandeDocumentAdministratifSpecification(DemandeDocumentAdministratifCriteria criteria) {
        super(criteria);
    }

    public DemandeDocumentAdministratifSpecification(DemandeDocumentAdministratifCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
