package ma.zs.GestionAgents.zynerator.security.service.facade;

import ma.zs.GestionAgents.zynerator.security.bean.Role;
import ma.zs.GestionAgents.zynerator.security.dao.criteria.core.RoleCriteria;
import ma.zs.GestionAgents.zynerator.service.IService;



public interface RoleService extends  IService<Role,RoleCriteria>  {
    Role findByAuthority(String authority);
    int deleteByAuthority(String authority);


    



}
