package ma.zs.GestionAgents.zynerator.security.service.facade;

import ma.zs.GestionAgents.zynerator.security.bean.ActionPermission;
import ma.zs.GestionAgents.zynerator.security.dao.criteria.core.ActionPermissionCriteria;
import ma.zs.GestionAgents.zynerator.service.IService;
import java.util.List;


public interface ActionPermissionService extends  IService<ActionPermission,ActionPermissionCriteria>  {

    List<ActionPermission> findAllOptimized();

}
