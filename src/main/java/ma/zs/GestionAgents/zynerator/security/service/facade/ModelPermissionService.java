package ma.zs.GestionAgents.zynerator.security.service.facade;

import ma.zs.GestionAgents.zynerator.security.bean.ModelPermission;
import ma.zs.GestionAgents.zynerator.security.dao.criteria.core.ModelPermissionCriteria;
import ma.zs.GestionAgents.zynerator.service.IService;
import java.util.List;



public interface ModelPermissionService extends  IService<ModelPermission,ModelPermissionCriteria>  {
    List<ModelPermission> findAllOptimized();

}
