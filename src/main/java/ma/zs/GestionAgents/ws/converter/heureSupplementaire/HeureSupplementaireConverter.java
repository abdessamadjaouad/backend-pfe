package  ma.zs.GestionAgents.ws.converter.heureSupplementaire;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.GestionAgents.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;




import ma.zs.GestionAgents.zynerator.util.StringUtil;
import ma.zs.GestionAgents.zynerator.converter.AbstractConverter;
import ma.zs.GestionAgents.zynerator.util.DateUtil;
import ma.zs.GestionAgents.bean.core.heureSupplementaire.HeureSupplementaire;
import ma.zs.GestionAgents.ws.dto.heureSupplementaire.HeureSupplementaireDto;

@Component
public class HeureSupplementaireConverter {


    public  HeureSupplementaireConverter() {
    }


    public HeureSupplementaire toItem(HeureSupplementaireDto dto) {
        if (dto == null) {
            return null;
        } else {
        HeureSupplementaire item = new HeureSupplementaire();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getRef()))
                item.setRef(dto.getRef());
            if(StringUtil.isNotEmpty(dto.getNbrHeure()))
                item.setNbrHeure(dto.getNbrHeure());



        return item;
        }
    }


    public HeureSupplementaireDto toDto(HeureSupplementaire item) {
        if (item == null) {
            return null;
        } else {
            HeureSupplementaireDto dto = new HeureSupplementaireDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getRef()))
                dto.setRef(item.getRef());
            if(StringUtil.isNotEmpty(item.getNbrHeure()))
                dto.setNbrHeure(item.getNbrHeure());


        return dto;
        }
    }


	
    public List<HeureSupplementaire> toItem(List<HeureSupplementaireDto> dtos) {
        List<HeureSupplementaire> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (HeureSupplementaireDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<HeureSupplementaireDto> toDto(List<HeureSupplementaire> items) {
        List<HeureSupplementaireDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (HeureSupplementaire item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(HeureSupplementaireDto dto, HeureSupplementaire t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<HeureSupplementaire> copy(List<HeureSupplementaireDto> dtos) {
        List<HeureSupplementaire> result = new ArrayList<>();
        if (dtos != null) {
            for (HeureSupplementaireDto dto : dtos) {
                HeureSupplementaire instance = new HeureSupplementaire();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
