package  ma.zs.GestionAgents.ws.converter.DemandeConge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.GestionAgents.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zs.GestionAgents.ws.converter.Poste.RaisonDemandeCongeConverter;



import ma.zs.GestionAgents.zynerator.util.StringUtil;
import ma.zs.GestionAgents.zynerator.converter.AbstractConverter;
import ma.zs.GestionAgents.zynerator.util.DateUtil;
import ma.zs.GestionAgents.bean.core.DemandeConge.DemandeConge;
import ma.zs.GestionAgents.ws.dto.DemandeConge.DemandeCongeDto;

@Component
public class DemandeCongeConverter {

    @Autowired
    private RaisonDemandeCongeConverter raisonDemandeCongeConverter ;
    private boolean raisonDemandeConge;

    public  DemandeCongeConverter() {
        initObject(true);
    }


    public DemandeConge toItem(DemandeCongeDto dto) {
        if (dto == null) {
            return null;
        } else {
        DemandeConge item = new DemandeConge();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getRef()))
                item.setRef(dto.getRef());
            if(StringUtil.isNotEmpty(dto.getDateDebut()))
                item.setDateDebut(DateUtil.stringEnToDate(dto.getDateDebut()));
            if(StringUtil.isNotEmpty(dto.getDateFin()))
                item.setDateFin(DateUtil.stringEnToDate(dto.getDateFin()));
            if(this.raisonDemandeConge && dto.getRaisonDemandeConge()!=null)
                item.setRaisonDemandeConge(raisonDemandeCongeConverter.toItem(dto.getRaisonDemandeConge())) ;




        return item;
        }
    }


    public DemandeCongeDto toDto(DemandeConge item) {
        if (item == null) {
            return null;
        } else {
            DemandeCongeDto dto = new DemandeCongeDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getRef()))
                dto.setRef(item.getRef());
            if(item.getDateDebut()!=null)
                dto.setDateDebut(DateUtil.dateTimeToString(item.getDateDebut()));
            if(item.getDateFin()!=null)
                dto.setDateFin(DateUtil.dateTimeToString(item.getDateFin()));
            if(this.raisonDemandeConge && item.getRaisonDemandeConge()!=null) {
                dto.setRaisonDemandeConge(raisonDemandeCongeConverter.toDto(item.getRaisonDemandeConge())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.raisonDemandeConge = value;
    }
	
    public List<DemandeConge> toItem(List<DemandeCongeDto> dtos) {
        List<DemandeConge> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (DemandeCongeDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<DemandeCongeDto> toDto(List<DemandeConge> items) {
        List<DemandeCongeDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (DemandeConge item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(DemandeCongeDto dto, DemandeConge t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if (dto.getRaisonDemandeConge() != null)
        raisonDemandeCongeConverter.copy(dto.getRaisonDemandeConge(), t.getRaisonDemandeConge());
    }

    public List<DemandeConge> copy(List<DemandeCongeDto> dtos) {
        List<DemandeConge> result = new ArrayList<>();
        if (dtos != null) {
            for (DemandeCongeDto dto : dtos) {
                DemandeConge instance = new DemandeConge();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public RaisonDemandeCongeConverter getRaisonDemandeCongeConverter(){
        return this.raisonDemandeCongeConverter;
    }
    public void setRaisonDemandeCongeConverter(RaisonDemandeCongeConverter raisonDemandeCongeConverter ){
        this.raisonDemandeCongeConverter = raisonDemandeCongeConverter;
    }
    public boolean  isRaisonDemandeConge(){
        return this.raisonDemandeConge;
    }
    public void  setRaisonDemandeConge(boolean raisonDemandeConge){
        this.raisonDemandeConge = raisonDemandeConge;
    }
}
