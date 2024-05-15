package  ma.zs.GestionAgents.ws.converter.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.GestionAgents.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zs.GestionAgents.ws.converter.Agent.AgentConverter;



import ma.zs.GestionAgents.zynerator.util.StringUtil;
import ma.zs.GestionAgents.zynerator.converter.AbstractConverter;
import ma.zs.GestionAgents.zynerator.util.DateUtil;
import ma.zs.GestionAgents.bean.core.Service.EntiteAdministrative;
import ma.zs.GestionAgents.ws.dto.Service.EntiteAdministrativeDto;

@Component
public class EntiteAdministrativeConverter {

    @Autowired
    private AgentConverter agentConverter ;
    private boolean chefService;

    public  EntiteAdministrativeConverter() {
        initObject(true);
    }


    public EntiteAdministrative toItem(EntiteAdministrativeDto dto) {
        if (dto == null) {
            return null;
        } else {
        EntiteAdministrative item = new EntiteAdministrative();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getRef()))
                item.setRef(dto.getRef());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());
            if(this.chefService && dto.getChefService()!=null)
                item.setChefService(agentConverter.toItem(dto.getChefService())) ;




        return item;
        }
    }


    public EntiteAdministrativeDto toDto(EntiteAdministrative item) {
        if (item == null) {
            return null;
        } else {
            EntiteAdministrativeDto dto = new EntiteAdministrativeDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getRef()))
                dto.setRef(item.getRef());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());
            if(this.chefService && item.getChefService()!=null) {
                agentConverter.setEntiteAdministrative(false);
                dto.setChefService(agentConverter.toDto(item.getChefService())) ;
                agentConverter.setEntiteAdministrative(true);

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.chefService = value;
    }
	
    public List<EntiteAdministrative> toItem(List<EntiteAdministrativeDto> dtos) {
        List<EntiteAdministrative> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (EntiteAdministrativeDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<EntiteAdministrativeDto> toDto(List<EntiteAdministrative> items) {
        List<EntiteAdministrativeDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (EntiteAdministrative item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(EntiteAdministrativeDto dto, EntiteAdministrative t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if (dto.getChefService() != null)
        agentConverter.copy(dto.getChefService(), t.getChefService());
    }

    public List<EntiteAdministrative> copy(List<EntiteAdministrativeDto> dtos) {
        List<EntiteAdministrative> result = new ArrayList<>();
        if (dtos != null) {
            for (EntiteAdministrativeDto dto : dtos) {
                EntiteAdministrative instance = new EntiteAdministrative();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public AgentConverter getAgentConverter(){
        return this.agentConverter;
    }
    public void setAgentConverter(AgentConverter agentConverter ){
        this.agentConverter = agentConverter;
    }
    public boolean  isChefService(){
        return this.chefService;
    }
    public void  setChefService(boolean chefService){
        this.chefService = chefService;
    }
}
