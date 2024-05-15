package  ma.zs.GestionAgents.ws.facade.admin.BioManager;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zs.GestionAgents.bean.core.BioManager.BadgeNfc;
import ma.zs.GestionAgents.dao.criteria.core.BioManager.BadgeNfcCriteria;
import ma.zs.GestionAgents.service.facade.admin.BioManager.BadgeNfcAdminService;
import ma.zs.GestionAgents.ws.converter.BioManager.BadgeNfcConverter;
import ma.zs.GestionAgents.ws.dto.BioManager.BadgeNfcDto;
import ma.zs.GestionAgents.zynerator.controller.AbstractController;
import ma.zs.GestionAgents.zynerator.dto.AuditEntityDto;
import ma.zs.GestionAgents.zynerator.util.PaginatedList;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zs.GestionAgents.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zs.GestionAgents.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/admin/badgeNfc/")
public class BadgeNfcRestAdmin {




    @Operation(summary = "Finds a list of all badgeNfcs")
    @GetMapping("")
    public ResponseEntity<List<BadgeNfcDto>> findAll() throws Exception {
        ResponseEntity<List<BadgeNfcDto>> res = null;
        List<BadgeNfc> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<BadgeNfcDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all badgeNfcs")
    @GetMapping("optimized")
    public ResponseEntity<List<BadgeNfcDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<BadgeNfcDto>> res = null;
        List<BadgeNfc> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<BadgeNfcDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a badgeNfc by id")
    @GetMapping("id/{id}")
    public ResponseEntity<BadgeNfcDto> findById(@PathVariable Long id) {
        BadgeNfc t = service.findById(id);
        if (t != null) {
            BadgeNfcDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a badgeNfc by ref")
    @GetMapping("ref/{ref}")
    public ResponseEntity<BadgeNfcDto> findByRef(@PathVariable String ref) {
	    BadgeNfc t = service.findByReferenceEntity(new BadgeNfc(ref));
        if (t != null) {
            BadgeNfcDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  badgeNfc")
    @PostMapping("")
    public ResponseEntity<BadgeNfcDto> save(@RequestBody BadgeNfcDto dto) throws Exception {
        if(dto!=null){
            BadgeNfc myT = converter.toItem(dto);
            BadgeNfc t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                BadgeNfcDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  badgeNfc")
    @PutMapping("")
    public ResponseEntity<BadgeNfcDto> update(@RequestBody BadgeNfcDto dto) throws Exception {
        ResponseEntity<BadgeNfcDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            BadgeNfc t = service.findById(dto.getId());
            converter.copy(dto,t);
            BadgeNfc updated = service.update(t);
            BadgeNfcDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of badgeNfc")
    @PostMapping("multiple")
    public ResponseEntity<List<BadgeNfcDto>> delete(@RequestBody List<BadgeNfcDto> dtos) throws Exception {
        ResponseEntity<List<BadgeNfcDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<BadgeNfc> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified badgeNfc")
    @DeleteMapping("")
    public ResponseEntity<BadgeNfcDto> delete(@RequestBody BadgeNfcDto dto) throws Exception {
		ResponseEntity<BadgeNfcDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            BadgeNfc t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified badgeNfc")
    @DeleteMapping("id/{id}")
    public ResponseEntity<Long> deleteById(@PathVariable Long id) throws Exception {
        ResponseEntity<Long> res;
        HttpStatus status = HttpStatus.PRECONDITION_FAILED;
        if (id != null) {
            boolean resultDelete = service.deleteById(id);
            if (resultDelete) {
                status = HttpStatus.OK;
            }
        }
        res = new ResponseEntity<>(id, status);
        return res;
    }
    @Operation(summary = "Delete multiple badgeNfcs by ids")
    @DeleteMapping("multiple/id")
    public ResponseEntity<List<Long>> deleteByIdIn(@RequestBody List<Long> ids) throws Exception {
        ResponseEntity<List<Long>> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (ids != null) {
            service.deleteByIdIn(ids);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(ids, status);
        return res;
     }



    @Operation(summary = "Finds a badgeNfc and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<BadgeNfcDto> findWithAssociatedLists(@PathVariable Long id) {
        BadgeNfc loaded =  service.findWithAssociatedLists(id);
        BadgeNfcDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds badgeNfcs by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<BadgeNfcDto>> findByCriteria(@RequestBody BadgeNfcCriteria criteria) throws Exception {
        ResponseEntity<List<BadgeNfcDto>> res = null;
        List<BadgeNfc> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<BadgeNfcDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated badgeNfcs by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody BadgeNfcCriteria criteria) throws Exception {
        List<BadgeNfc> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<BadgeNfcDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets badgeNfc data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody BadgeNfcCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<BadgeNfcDto> findDtos(List<BadgeNfc> list){
        List<BadgeNfcDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<BadgeNfcDto> getDtoResponseEntity(BadgeNfcDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private BadgeNfcAdminService service;
    @Autowired private BadgeNfcConverter converter;





}
