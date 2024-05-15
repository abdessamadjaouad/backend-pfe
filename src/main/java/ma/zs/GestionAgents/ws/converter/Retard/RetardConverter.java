package  ma.zs.GestionAgents.ws.converter.Retard;

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
import ma.zs.GestionAgents.bean.core.Retard.Retard;
import ma.zs.GestionAgents.ws.dto.Retard.RetardDto;

@Component
public class RetardConverter {

    @Autowired
    private AgentConverter agentConverter ;
    private boolean agent;

    public  RetardConverter() {
        initObject(true);
    }


    public Retard toItem(RetardDto dto) {
        if (dto == null) {
            return null;
        } else {
        Retard item = new Retard();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getRef()))
                item.setRef(dto.getRef());
            if(StringUtil.isNotEmpty(dto.getDateRetart()))
                item.setDateRetart(DateUtil.stringEnToDate(dto.getDateRetart()));
            if(StringUtil.isNotEmpty(dto.getDuree()))
                item.setDuree(dto.getDuree());
            if(this.agent && dto.getAgent()!=null)
                item.setAgent(agentConverter.toItem(dto.getAgent())) ;




        return item;
        }
    }


    public RetardDto toDto(Retard item) {
        if (item == null) {
            return null;
        } else {
            RetardDto dto = new RetardDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getRef()))
                dto.setRef(item.getRef());
            if(item.getDateRetart()!=null)
                dto.setDateRetart(DateUtil.dateTimeToString(item.getDateRetart()));
            if(StringUtil.isNotEmpty(item.getDuree()))
                dto.setDuree(item.getDuree());
            if(this.agent && item.getAgent()!=null) {
                dto.setAgent(agentConverter.toDto(item.getAgent())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.agent = value;
    }
	
    public List<Retard> toItem(List<RetardDto> dtos) {
        List<Retard> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (RetardDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<RetardDto> toDto(List<Retard> items) {
        List<RetardDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Retard item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(RetardDto dto, Retard t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if (dto.getAgent() != null)
        agentConverter.copy(dto.getAgent(), t.getAgent());
    }

    public List<Retard> copy(List<RetardDto> dtos) {
        List<Retard> result = new ArrayList<>();
        if (dtos != null) {
            for (RetardDto dto : dtos) {
                Retard instance = new Retard();
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
    public boolean  isAgent(){
        return this.agent;
    }
    public void  setAgent(boolean agent){
        this.agent = agent;
    }
}
