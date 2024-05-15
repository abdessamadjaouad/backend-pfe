package  ma.zs.GestionAgents.ws.converter.DemandeAbsence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.GestionAgents.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zs.GestionAgents.ws.converter.Poste.RaisonDemandeAbsenceConverter;



import ma.zs.GestionAgents.zynerator.util.StringUtil;
import ma.zs.GestionAgents.zynerator.converter.AbstractConverter;
import ma.zs.GestionAgents.zynerator.util.DateUtil;
import ma.zs.GestionAgents.bean.core.DemandeAbsence.DemandeAbsence;
import ma.zs.GestionAgents.ws.dto.DemandeAbsence.DemandeAbsenceDto;

@Component
public class DemandeAbsenceConverter {

    @Autowired
    private RaisonDemandeAbsenceConverter raisonDemandeAbsenceConverter ;
    private boolean raisonDemandeAbsence;

    public  DemandeAbsenceConverter() {
        initObject(true);
    }


    public DemandeAbsence toItem(DemandeAbsenceDto dto) {
        if (dto == null) {
            return null;
        } else {
        DemandeAbsence item = new DemandeAbsence();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getRef()))
                item.setRef(dto.getRef());
            if(StringUtil.isNotEmpty(dto.getContenu()))
                item.setContenu(dto.getContenu());
            if(this.raisonDemandeAbsence && dto.getRaisonDemandeAbsence()!=null)
                item.setRaisonDemandeAbsence(raisonDemandeAbsenceConverter.toItem(dto.getRaisonDemandeAbsence())) ;




        return item;
        }
    }


    public DemandeAbsenceDto toDto(DemandeAbsence item) {
        if (item == null) {
            return null;
        } else {
            DemandeAbsenceDto dto = new DemandeAbsenceDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getRef()))
                dto.setRef(item.getRef());
            if(StringUtil.isNotEmpty(item.getContenu()))
                dto.setContenu(item.getContenu());
            if(this.raisonDemandeAbsence && item.getRaisonDemandeAbsence()!=null) {
                dto.setRaisonDemandeAbsence(raisonDemandeAbsenceConverter.toDto(item.getRaisonDemandeAbsence())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.raisonDemandeAbsence = value;
    }
	
    public List<DemandeAbsence> toItem(List<DemandeAbsenceDto> dtos) {
        List<DemandeAbsence> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (DemandeAbsenceDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<DemandeAbsenceDto> toDto(List<DemandeAbsence> items) {
        List<DemandeAbsenceDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (DemandeAbsence item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(DemandeAbsenceDto dto, DemandeAbsence t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if (dto.getRaisonDemandeAbsence() != null)
        raisonDemandeAbsenceConverter.copy(dto.getRaisonDemandeAbsence(), t.getRaisonDemandeAbsence());
    }

    public List<DemandeAbsence> copy(List<DemandeAbsenceDto> dtos) {
        List<DemandeAbsence> result = new ArrayList<>();
        if (dtos != null) {
            for (DemandeAbsenceDto dto : dtos) {
                DemandeAbsence instance = new DemandeAbsence();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public RaisonDemandeAbsenceConverter getRaisonDemandeAbsenceConverter(){
        return this.raisonDemandeAbsenceConverter;
    }
    public void setRaisonDemandeAbsenceConverter(RaisonDemandeAbsenceConverter raisonDemandeAbsenceConverter ){
        this.raisonDemandeAbsenceConverter = raisonDemandeAbsenceConverter;
    }
    public boolean  isRaisonDemandeAbsence(){
        return this.raisonDemandeAbsence;
    }
    public void  setRaisonDemandeAbsence(boolean raisonDemandeAbsence){
        this.raisonDemandeAbsence = raisonDemandeAbsence;
    }
}
