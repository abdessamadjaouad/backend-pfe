package  ma.zs.GestionAgents.ws.converter.BioManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.GestionAgents.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;




import ma.zs.GestionAgents.zynerator.util.StringUtil;
import ma.zs.GestionAgents.zynerator.converter.AbstractConverter;
import ma.zs.GestionAgents.zynerator.util.DateUtil;
import ma.zs.GestionAgents.bean.core.BioManager.BadgeNfc;
import ma.zs.GestionAgents.ws.dto.BioManager.BadgeNfcDto;

@Component
public class BadgeNfcConverter {


    public  BadgeNfcConverter() {
    }


    public BadgeNfc toItem(BadgeNfcDto dto) {
        if (dto == null) {
            return null;
        } else {
        BadgeNfc item = new BadgeNfc();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getRef()))
                item.setRef(dto.getRef());
            if(StringUtil.isNotEmpty(dto.getMatricule()))
                item.setMatricule(dto.getMatricule());
            if(StringUtil.isNotEmpty(dto.getLocation()))
                item.setLocation(dto.getLocation());
            if(StringUtil.isNotEmpty(dto.getStatus()))
                item.setStatus(dto.getStatus());



        return item;
        }
    }


    public BadgeNfcDto toDto(BadgeNfc item) {
        if (item == null) {
            return null;
        } else {
            BadgeNfcDto dto = new BadgeNfcDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getRef()))
                dto.setRef(item.getRef());
            if(StringUtil.isNotEmpty(item.getMatricule()))
                dto.setMatricule(item.getMatricule());
            if(StringUtil.isNotEmpty(item.getLocation()))
                dto.setLocation(item.getLocation());
            if(StringUtil.isNotEmpty(item.getStatus()))
                dto.setStatus(item.getStatus());


        return dto;
        }
    }


	
    public List<BadgeNfc> toItem(List<BadgeNfcDto> dtos) {
        List<BadgeNfc> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (BadgeNfcDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<BadgeNfcDto> toDto(List<BadgeNfc> items) {
        List<BadgeNfcDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (BadgeNfc item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(BadgeNfcDto dto, BadgeNfc t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<BadgeNfc> copy(List<BadgeNfcDto> dtos) {
        List<BadgeNfc> result = new ArrayList<>();
        if (dtos != null) {
            for (BadgeNfcDto dto : dtos) {
                BadgeNfc instance = new BadgeNfc();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
