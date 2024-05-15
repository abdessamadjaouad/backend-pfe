package  ma.zs.GestionAgents.ws.converter.Pointage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.GestionAgents.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zs.GestionAgents.ws.converter.Agent.AgentConverter;
import ma.zs.GestionAgents.ws.converter.BioManager.BadgeNfcConverter;



import ma.zs.GestionAgents.zynerator.util.StringUtil;
import ma.zs.GestionAgents.zynerator.converter.AbstractConverter;
import ma.zs.GestionAgents.zynerator.util.DateUtil;
import ma.zs.GestionAgents.bean.core.Pointage.Pointage;
import ma.zs.GestionAgents.ws.dto.Pointage.PointageDto;

@Component
public class PointageConverter {

    @Autowired
    private AgentConverter agentConverter ;
    @Autowired
    private BadgeNfcConverter badgeNfcConverter ;
    private boolean agent;
    private boolean badgeNfc;

    public  PointageConverter() {
        initObject(true);
    }


    public Pointage toItem(PointageDto dto) {
        if (dto == null) {
            return null;
        } else {
        Pointage item = new Pointage();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getRef()))
                item.setRef(dto.getRef());
            if(StringUtil.isNotEmpty(dto.getHeureEntree()))
                item.setHeureEntree(DateUtil.stringEnToDate(dto.getHeureEntree()));
            if(StringUtil.isNotEmpty(dto.getHeureSortie()))
                item.setHeureSortie(DateUtil.stringEnToDate(dto.getHeureSortie()));
            if(dto.getPresence() != null)
                item.setPresence(dto.getPresence());
            if(StringUtil.isNotEmpty(dto.getPointageSens()))
                item.setPointageSens(dto.getPointageSens());
            if(this.agent && dto.getAgent()!=null)
                item.setAgent(agentConverter.toItem(dto.getAgent())) ;

            if(this.badgeNfc && dto.getBadgeNfc()!=null)
                item.setBadgeNfc(badgeNfcConverter.toItem(dto.getBadgeNfc())) ;




        return item;
        }
    }


    public PointageDto toDto(Pointage item) {
        if (item == null) {
            return null;
        } else {
            PointageDto dto = new PointageDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getRef()))
                dto.setRef(item.getRef());
            if(item.getHeureEntree()!=null)
                dto.setHeureEntree(DateUtil.dateTimeToString(item.getHeureEntree()));
            if(item.getHeureSortie()!=null)
                dto.setHeureSortie(DateUtil.dateTimeToString(item.getHeureSortie()));
                dto.setPresence(item.getPresence());
            if(StringUtil.isNotEmpty(item.getPointageSens()))
                dto.setPointageSens(item.getPointageSens());
            if(this.agent && item.getAgent()!=null) {
                dto.setAgent(agentConverter.toDto(item.getAgent())) ;

            }
            if(this.badgeNfc && item.getBadgeNfc()!=null) {
                dto.setBadgeNfc(badgeNfcConverter.toDto(item.getBadgeNfc())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.agent = value;
        this.badgeNfc = value;
    }
	
    public List<Pointage> toItem(List<PointageDto> dtos) {
        List<Pointage> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (PointageDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<PointageDto> toDto(List<Pointage> items) {
        List<PointageDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Pointage item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(PointageDto dto, Pointage t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if (dto.getAgent() != null)
        agentConverter.copy(dto.getAgent(), t.getAgent());
        if (dto.getBadgeNfc() != null)
        badgeNfcConverter.copy(dto.getBadgeNfc(), t.getBadgeNfc());
    }

    public List<Pointage> copy(List<PointageDto> dtos) {
        List<Pointage> result = new ArrayList<>();
        if (dtos != null) {
            for (PointageDto dto : dtos) {
                Pointage instance = new Pointage();
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
    public BadgeNfcConverter getBadgeNfcConverter(){
        return this.badgeNfcConverter;
    }
    public void setBadgeNfcConverter(BadgeNfcConverter badgeNfcConverter ){
        this.badgeNfcConverter = badgeNfcConverter;
    }
    public boolean  isAgent(){
        return this.agent;
    }
    public void  setAgent(boolean agent){
        this.agent = agent;
    }
    public boolean  isBadgeNfc(){
        return this.badgeNfc;
    }
    public void  setBadgeNfc(boolean badgeNfc){
        this.badgeNfc = badgeNfc;
    }
}
