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
import ma.zs.GestionAgents.bean.core.Poste.Poste;
import ma.zs.GestionAgents.ws.dto.Poste.PosteDto;

@Component
public class PosteConverter {


    public  PosteConverter() {
    }


    public Poste toItem(PosteDto dto) {
        if (dto == null) {
            return null;
        } else {
        Poste item = new Poste();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getRef()))
                item.setRef(dto.getRef());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());



        return item;
        }
    }


    public PosteDto toDto(Poste item) {
        if (item == null) {
            return null;
        } else {
            PosteDto dto = new PosteDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getRef()))
                dto.setRef(item.getRef());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());


        return dto;
        }
    }


	
    public List<Poste> toItem(List<PosteDto> dtos) {
        List<Poste> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (PosteDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<PosteDto> toDto(List<Poste> items) {
        List<PosteDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Poste item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(PosteDto dto, Poste t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<Poste> copy(List<PosteDto> dtos) {
        List<Poste> result = new ArrayList<>();
        if (dtos != null) {
            for (PosteDto dto : dtos) {
                Poste instance = new Poste();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
