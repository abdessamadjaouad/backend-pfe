package  ma.zs.GestionAgents.ws.converter.DemandeDocumentAdministratif;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.GestionAgents.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;




import ma.zs.GestionAgents.zynerator.util.StringUtil;
import ma.zs.GestionAgents.zynerator.converter.AbstractConverter;
import ma.zs.GestionAgents.zynerator.util.DateUtil;
import ma.zs.GestionAgents.bean.core.DemandeDocumentAdministratif.DemandeDocumentAdministratif;
import ma.zs.GestionAgents.ws.dto.DemandeDocumentAdministratif.DemandeDocumentAdministratifDto;

@Component
public class DemandeDocumentAdministratifConverter {


    public  DemandeDocumentAdministratifConverter() {
    }


    public DemandeDocumentAdministratif toItem(DemandeDocumentAdministratifDto dto) {
        if (dto == null) {
            return null;
        } else {
        DemandeDocumentAdministratif item = new DemandeDocumentAdministratif();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getRef()))
                item.setRef(dto.getRef());
            if(StringUtil.isNotEmpty(dto.getType()))
                item.setType(dto.getType());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());



        return item;
        }
    }


    public DemandeDocumentAdministratifDto toDto(DemandeDocumentAdministratif item) {
        if (item == null) {
            return null;
        } else {
            DemandeDocumentAdministratifDto dto = new DemandeDocumentAdministratifDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getRef()))
                dto.setRef(item.getRef());
            if(StringUtil.isNotEmpty(item.getType()))
                dto.setType(item.getType());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());


        return dto;
        }
    }


	
    public List<DemandeDocumentAdministratif> toItem(List<DemandeDocumentAdministratifDto> dtos) {
        List<DemandeDocumentAdministratif> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (DemandeDocumentAdministratifDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<DemandeDocumentAdministratifDto> toDto(List<DemandeDocumentAdministratif> items) {
        List<DemandeDocumentAdministratifDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (DemandeDocumentAdministratif item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(DemandeDocumentAdministratifDto dto, DemandeDocumentAdministratif t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<DemandeDocumentAdministratif> copy(List<DemandeDocumentAdministratifDto> dtos) {
        List<DemandeDocumentAdministratif> result = new ArrayList<>();
        if (dtos != null) {
            for (DemandeDocumentAdministratifDto dto : dtos) {
                DemandeDocumentAdministratif instance = new DemandeDocumentAdministratif();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
