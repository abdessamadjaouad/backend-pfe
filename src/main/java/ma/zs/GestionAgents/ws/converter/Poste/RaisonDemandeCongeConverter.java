package  ma.zs.GestionAgents.ws.converter.Poste;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.GestionAgents.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;




import ma.zs.GestionAgents.zynerator.util.StringUtil;
import ma.zs.GestionAgents.zynerator.converter.AbstractConverter;
import ma.zs.GestionAgents.zynerator.util.DateUtil;
import ma.zs.GestionAgents.bean.core.Poste.RaisonDemandeConge;
import ma.zs.GestionAgents.ws.dto.Poste.RaisonDemandeCongeDto;

@Component
public class RaisonDemandeCongeConverter {


    public  RaisonDemandeCongeConverter() {
    }


    public RaisonDemandeConge toItem(RaisonDemandeCongeDto dto) {
        if (dto == null) {
            return null;
        } else {
        RaisonDemandeConge item = new RaisonDemandeConge();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getRef()))
                item.setRef(dto.getRef());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());



        return item;
        }
    }


    public RaisonDemandeCongeDto toDto(RaisonDemandeConge item) {
        if (item == null) {
            return null;
        } else {
            RaisonDemandeCongeDto dto = new RaisonDemandeCongeDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getRef()))
                dto.setRef(item.getRef());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());


        return dto;
        }
    }


	
    public List<RaisonDemandeConge> toItem(List<RaisonDemandeCongeDto> dtos) {
        List<RaisonDemandeConge> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (RaisonDemandeCongeDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<RaisonDemandeCongeDto> toDto(List<RaisonDemandeConge> items) {
        List<RaisonDemandeCongeDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (RaisonDemandeConge item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(RaisonDemandeCongeDto dto, RaisonDemandeConge t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<RaisonDemandeConge> copy(List<RaisonDemandeCongeDto> dtos) {
        List<RaisonDemandeConge> result = new ArrayList<>();
        if (dtos != null) {
            for (RaisonDemandeCongeDto dto : dtos) {
                RaisonDemandeConge instance = new RaisonDemandeConge();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
