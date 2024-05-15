package  ma.zs.GestionAgents.dao.specification.core.Agent;

import ma.zs.GestionAgents.dao.criteria.core.Agent.AgentCriteria;
import ma.zs.GestionAgents.bean.core.Agent.Agent;
import ma.zs.GestionAgents.zynerator.specification.AbstractSpecification;


public class AgentSpecification extends  AbstractSpecification<AgentCriteria, Agent>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("ref", criteria.getRef(),criteria.getRefLike());
        addPredicate("nomUtilisateur", criteria.getNomUtilisateur(),criteria.getNomUtilisateurLike());
        addPredicate("motDePasse", criteria.getMotDePasse(),criteria.getMotDePasseLike());
        addPredicate("nom", criteria.getNom(),criteria.getNomLike());
        addPredicate("prenom", criteria.getPrenom(),criteria.getPrenomLike());
        addPredicate("adresse", criteria.getAdresse(),criteria.getAdresseLike());
        addPredicateFk("entiteAdministrative","id", criteria.getEntiteAdministrative()==null?null:criteria.getEntiteAdministrative().getId());
        addPredicateFk("entiteAdministrative","id", criteria.getEntiteAdministratives());
        addPredicateFk("entiteAdministrative","ref", criteria.getEntiteAdministrative()==null?null:criteria.getEntiteAdministrative().getRef());
        addPredicateFk("poste","id", criteria.getPoste()==null?null:criteria.getPoste().getId());
        addPredicateFk("poste","id", criteria.getPostes());
        addPredicateFk("poste","ref", criteria.getPoste()==null?null:criteria.getPoste().getRef());
        addPredicateFk("plageHoraire","id", criteria.getPlageHoraire()==null?null:criteria.getPlageHoraire().getId());
        addPredicateFk("plageHoraire","id", criteria.getPlageHoraires());
        addPredicateFk("plageHoraire","ref", criteria.getPlageHoraire()==null?null:criteria.getPlageHoraire().getRef());
    }

    public AgentSpecification(AgentCriteria criteria) {
        super(criteria);
    }

    public AgentSpecification(AgentCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
