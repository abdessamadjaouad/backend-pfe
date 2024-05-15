package  ma.zs.GestionAgents.ws.converter.Conge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.GestionAgents.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;




import ma.zs.GestionAgents.zynerator.util.StringUtil;
import ma.zs.GestionAgents.zynerator.converter.AbstractConverter;
import ma.zs.GestionAgents.zynerator.util.DateUtil;
import ma.zs.GestionAgents.bean.core.Conge.Conge;
import ma.zs.GestionAgents.ws.dto.Conge.CongeDto;

@Component
public class CongeConverter {


    public  CongeConverter() {
    }


    public Conge toItem(CongeDto dto) {
        if (dto == null) {
            return null;
        } else {
        Conge item = new Conge();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getRef()))
                item.setRef(dto.getRef());
            if(StringUtil.isNotEmpty(dto.getDateDebut()))
                item.setDateDebut(DateUtil.stringEnToDate(dto.getDateDebut()));
            if(StringUtil.isNotEmpty(dto.getDateFin()))
                item.setDateFin(DateUtil.stringEnToDate(dto.getDateFin()));
            if(StringUtil.isNotEmpty(dto.getTypeConge()))
                item.setTypeConge(dto.getTypeConge());



        return item;
        }
    }


    public CongeDto toDto(Conge item) {
        if (item == null) {
            return null;
        } else {
            CongeDto dto = new CongeDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getRef()))
                dto.setRef(item.getRef());
            if(item.getDateDebut()!=null)
                dto.setDateDebut(DateUtil.dateTimeToString(item.getDateDebut()));
            if(item.getDateFin()!=null)
                dto.setDateFin(DateUtil.dateTimeToString(item.getDateFin()));
            if(StringUtil.isNotEmpty(item.getTypeConge()))
                dto.setTypeConge(item.getTypeConge());


        return dto;
        }
    }


	
    public List<Conge> toItem(List<CongeDto> dtos) {
        List<Conge> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (CongeDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<CongeDto> toDto(List<Conge> items) {
        List<CongeDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Conge item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(CongeDto dto, Conge t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<Conge> copy(List<CongeDto> dtos) {
        List<Conge> result = new ArrayList<>();
        if (dtos != null) {
            for (CongeDto dto : dtos) {
                Conge instance = new Conge();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
