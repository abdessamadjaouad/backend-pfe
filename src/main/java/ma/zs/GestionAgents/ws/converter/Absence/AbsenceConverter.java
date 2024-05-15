package  ma.zs.GestionAgents.ws.converter.Absence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.GestionAgents.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;




import ma.zs.GestionAgents.zynerator.util.StringUtil;
import ma.zs.GestionAgents.zynerator.converter.AbstractConverter;
import ma.zs.GestionAgents.zynerator.util.DateUtil;
import ma.zs.GestionAgents.bean.core.Absence.Absence;
import ma.zs.GestionAgents.ws.dto.Absence.AbsenceDto;

@Component
public class AbsenceConverter {


    public  AbsenceConverter() {
    }


    public Absence toItem(AbsenceDto dto) {
        if (dto == null) {
            return null;
        } else {
        Absence item = new Absence();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getRef()))
                item.setRef(dto.getRef());
            if(StringUtil.isNotEmpty(dto.getDateDebut()))
                item.setDateDebut(DateUtil.stringEnToDate(dto.getDateDebut()));
            if(StringUtil.isNotEmpty(dto.getDateFin()))
                item.setDateFin(DateUtil.stringEnToDate(dto.getDateFin()));
            if(StringUtil.isNotEmpty(dto.getTypeAbsence()))
                item.setTypeAbsence(dto.getTypeAbsence());



        return item;
        }
    }


    public AbsenceDto toDto(Absence item) {
        if (item == null) {
            return null;
        } else {
            AbsenceDto dto = new AbsenceDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getRef()))
                dto.setRef(item.getRef());
            if(item.getDateDebut()!=null)
                dto.setDateDebut(DateUtil.dateTimeToString(item.getDateDebut()));
            if(item.getDateFin()!=null)
                dto.setDateFin(DateUtil.dateTimeToString(item.getDateFin()));
            if(StringUtil.isNotEmpty(item.getTypeAbsence()))
                dto.setTypeAbsence(item.getTypeAbsence());


        return dto;
        }
    }


	
    public List<Absence> toItem(List<AbsenceDto> dtos) {
        List<Absence> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (AbsenceDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<AbsenceDto> toDto(List<Absence> items) {
        List<AbsenceDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Absence item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(AbsenceDto dto, Absence t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<Absence> copy(List<AbsenceDto> dtos) {
        List<Absence> result = new ArrayList<>();
        if (dtos != null) {
            for (AbsenceDto dto : dtos) {
                Absence instance = new Absence();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
