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
import ma.zs.GestionAgents.bean.core.Poste.RaisonDemandeAbsence;
import ma.zs.GestionAgents.ws.dto.Poste.RaisonDemandeAbsenceDto;

@Component
public class RaisonDemandeAbsenceConverter {


    public  RaisonDemandeAbsenceConverter() {
    }


    public RaisonDemandeAbsence toItem(RaisonDemandeAbsenceDto dto) {
        if (dto == null) {
            return null;
        } else {
        RaisonDemandeAbsence item = new RaisonDemandeAbsence();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getRef()))
                item.setRef(dto.getRef());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());



        return item;
        }
    }


    public RaisonDemandeAbsenceDto toDto(RaisonDemandeAbsence item) {
        if (item == null) {
            return null;
        } else {
            RaisonDemandeAbsenceDto dto = new RaisonDemandeAbsenceDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getRef()))
                dto.setRef(item.getRef());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());


        return dto;
        }
    }


	
    public List<RaisonDemandeAbsence> toItem(List<RaisonDemandeAbsenceDto> dtos) {
        List<RaisonDemandeAbsence> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (RaisonDemandeAbsenceDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<RaisonDemandeAbsenceDto> toDto(List<RaisonDemandeAbsence> items) {
        List<RaisonDemandeAbsenceDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (RaisonDemandeAbsence item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(RaisonDemandeAbsenceDto dto, RaisonDemandeAbsence t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<RaisonDemandeAbsence> copy(List<RaisonDemandeAbsenceDto> dtos) {
        List<RaisonDemandeAbsence> result = new ArrayList<>();
        if (dtos != null) {
            for (RaisonDemandeAbsenceDto dto : dtos) {
                RaisonDemandeAbsence instance = new RaisonDemandeAbsence();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
