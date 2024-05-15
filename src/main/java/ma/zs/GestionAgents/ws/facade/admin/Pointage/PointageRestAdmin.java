package  ma.zs.GestionAgents.ws.facade.admin.Pointage;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zs.GestionAgents.bean.core.Pointage.Pointage;
import ma.zs.GestionAgents.dao.criteria.core.Pointage.PointageCriteria;
import ma.zs.GestionAgents.service.facade.admin.Pointage.PointageAdminService;
import ma.zs.GestionAgents.ws.converter.Pointage.PointageConverter;
import ma.zs.GestionAgents.ws.dto.Pointage.PointageDto;
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
@RequestMapping("/api/admin/pointage/")
public class PointageRestAdmin {




    @Operation(summary = "Finds a list of all pointages")
    @GetMapping("")
    public ResponseEntity<List<PointageDto>> findAll() throws Exception {
        ResponseEntity<List<PointageDto>> res = null;
        List<Pointage> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<PointageDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all pointages")
    @GetMapping("optimized")
    public ResponseEntity<List<PointageDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<PointageDto>> res = null;
        List<Pointage> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<PointageDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a pointage by id")
    @GetMapping("id/{id}")
    public ResponseEntity<PointageDto> findById(@PathVariable Long id) {
        Pointage t = service.findById(id);
        if (t != null) {
            converter.init(true);
            PointageDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a pointage by ref")
    @GetMapping("ref/{ref}")
    public ResponseEntity<PointageDto> findByRef(@PathVariable String ref) {
	    Pointage t = service.findByReferenceEntity(new Pointage(ref));
        if (t != null) {
            converter.init(true);
            PointageDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  pointage")
    @PostMapping("")
    public ResponseEntity<PointageDto> save(@RequestBody PointageDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            Pointage myT = converter.toItem(dto);
            Pointage t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                PointageDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  pointage")
    @PutMapping("")
    public ResponseEntity<PointageDto> update(@RequestBody PointageDto dto) throws Exception {
        ResponseEntity<PointageDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Pointage t = service.findById(dto.getId());
            converter.copy(dto,t);
            Pointage updated = service.update(t);
            PointageDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of pointage")
    @PostMapping("multiple")
    public ResponseEntity<List<PointageDto>> delete(@RequestBody List<PointageDto> dtos) throws Exception {
        ResponseEntity<List<PointageDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<Pointage> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified pointage")
    @DeleteMapping("")
    public ResponseEntity<PointageDto> delete(@RequestBody PointageDto dto) throws Exception {
		ResponseEntity<PointageDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            converter.init(false);
            Pointage t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified pointage")
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
    @Operation(summary = "Delete multiple pointages by ids")
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


    @Operation(summary = "find by agent id")
    @GetMapping("agent/id/{id}")
    public List<PointageDto> findByAgentId(@PathVariable Long id){
        return findDtos(service.findByAgentId(id));
    }
    @Operation(summary = "delete by agent id")
    @DeleteMapping("agent/id/{id}")
    public int deleteByAgentId(@PathVariable Long id){
        return service.deleteByAgentId(id);
    }

    @Operation(summary = "Finds a pointage and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<PointageDto> findWithAssociatedLists(@PathVariable Long id) {
        Pointage loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        PointageDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds pointages by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<PointageDto>> findByCriteria(@RequestBody PointageCriteria criteria) throws Exception {
        ResponseEntity<List<PointageDto>> res = null;
        List<Pointage> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<PointageDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated pointages by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody PointageCriteria criteria) throws Exception {
        List<Pointage> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<PointageDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets pointage data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody PointageCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<PointageDto> findDtos(List<Pointage> list){
        converter.initObject(true);
        List<PointageDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<PointageDto> getDtoResponseEntity(PointageDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private PointageAdminService service;
    @Autowired private PointageConverter converter;





}
