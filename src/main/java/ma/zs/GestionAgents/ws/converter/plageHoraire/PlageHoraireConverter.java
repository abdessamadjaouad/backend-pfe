package  ma.zs.GestionAgents.ws.converter.plageHoraire;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.GestionAgents.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;




import ma.zs.GestionAgents.zynerator.util.StringUtil;
import ma.zs.GestionAgents.zynerator.converter.AbstractConverter;
import ma.zs.GestionAgents.zynerator.util.DateUtil;
import ma.zs.GestionAgents.bean.core.plageHoraire.PlageHoraire;
import ma.zs.GestionAgents.ws.dto.plageHoraire.PlageHoraireDto;

@Component
public class PlageHoraireConverter {


    public  PlageHoraireConverter() {
    }


    public PlageHoraire toItem(PlageHoraireDto dto) {
        if (dto == null) {
            return null;
        } else {
        PlageHoraire item = new PlageHoraire();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getRef()))
                item.setRef(dto.getRef());
            if(StringUtil.isNotEmpty(dto.getTypeHoraire()))
                item.setTypeHoraire(dto.getTypeHoraire());
            if(StringUtil.isNotEmpty(dto.getHeureDebut()))
                item.setHeureDebut(DateUtil.stringEnToDate(dto.getHeureDebut()));
            if(StringUtil.isNotEmpty(dto.getHeureFin()))
                item.setHeureFin(DateUtil.stringEnToDate(dto.getHeureFin()));



        return item;
        }
    }


    public PlageHoraireDto toDto(PlageHoraire item) {
        if (item == null) {
            return null;
        } else {
            PlageHoraireDto dto = new PlageHoraireDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getRef()))
                dto.setRef(item.getRef());
            if(StringUtil.isNotEmpty(item.getTypeHoraire()))
                dto.setTypeHoraire(item.getTypeHoraire());
            if(item.getHeureDebut()!=null)
                dto.setHeureDebut(DateUtil.dateTimeToString(item.getHeureDebut()));
            if(item.getHeureFin()!=null)
                dto.setHeureFin(DateUtil.dateTimeToString(item.getHeureFin()));


        return dto;
        }
    }


	
    public List<PlageHoraire> toItem(List<PlageHoraireDto> dtos) {
        List<PlageHoraire> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (PlageHoraireDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<PlageHoraireDto> toDto(List<PlageHoraire> items) {
        List<PlageHoraireDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (PlageHoraire item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(PlageHoraireDto dto, PlageHoraire t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<PlageHoraire> copy(List<PlageHoraireDto> dtos) {
        List<PlageHoraire> result = new ArrayList<>();
        if (dtos != null) {
            for (PlageHoraireDto dto : dtos) {
                PlageHoraire instance = new PlageHoraire();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
