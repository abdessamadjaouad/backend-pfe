package  ma.zs.GestionAgents.ws.facade.admin.DemandeConge;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zs.GestionAgents.bean.core.DemandeConge.DemandeConge;
import ma.zs.GestionAgents.dao.criteria.core.DemandeConge.DemandeCongeCriteria;
import ma.zs.GestionAgents.service.facade.admin.DemandeConge.DemandeCongeAdminService;
import ma.zs.GestionAgents.ws.converter.DemandeConge.DemandeCongeConverter;
import ma.zs.GestionAgents.ws.dto.DemandeConge.DemandeCongeDto;
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
@RequestMapping("/api/admin/demandeConge/")
public class DemandeCongeRestAdmin {




    @Operation(summary = "Finds a list of all demandeConges")
    @GetMapping("")
    public ResponseEntity<List<DemandeCongeDto>> findAll() throws Exception {
        ResponseEntity<List<DemandeCongeDto>> res = null;
        List<DemandeConge> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<DemandeCongeDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all demandeConges")
    @GetMapping("optimized")
    public ResponseEntity<List<DemandeCongeDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<DemandeCongeDto>> res = null;
        List<DemandeConge> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<DemandeCongeDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a demandeConge by id")
    @GetMapping("id/{id}")
    public ResponseEntity<DemandeCongeDto> findById(@PathVariable Long id) {
        DemandeConge t = service.findById(id);
        if (t != null) {
            converter.init(true);
            DemandeCongeDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a demandeConge by ref")
    @GetMapping("ref/{ref}")
    public ResponseEntity<DemandeCongeDto> findByRef(@PathVariable String ref) {
	    DemandeConge t = service.findByReferenceEntity(new DemandeConge(ref));
        if (t != null) {
            converter.init(true);
            DemandeCongeDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  demandeConge")
    @PostMapping("")
    public ResponseEntity<DemandeCongeDto> save(@RequestBody DemandeCongeDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            DemandeConge myT = converter.toItem(dto);
            DemandeConge t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                DemandeCongeDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  demandeConge")
    @PutMapping("")
    public ResponseEntity<DemandeCongeDto> update(@RequestBody DemandeCongeDto dto) throws Exception {
        ResponseEntity<DemandeCongeDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            DemandeConge t = service.findById(dto.getId());
            converter.copy(dto,t);
            DemandeConge updated = service.update(t);
            DemandeCongeDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of demandeConge")
    @PostMapping("multiple")
    public ResponseEntity<List<DemandeCongeDto>> delete(@RequestBody List<DemandeCongeDto> dtos) throws Exception {
        ResponseEntity<List<DemandeCongeDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<DemandeConge> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified demandeConge")
    @DeleteMapping("")
    public ResponseEntity<DemandeCongeDto> delete(@RequestBody DemandeCongeDto dto) throws Exception {
		ResponseEntity<DemandeCongeDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            converter.init(false);
            DemandeConge t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified demandeConge")
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
    @Operation(summary = "Delete multiple demandeConges by ids")
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



    @Operation(summary = "Finds a demandeConge and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<DemandeCongeDto> findWithAssociatedLists(@PathVariable Long id) {
        DemandeConge loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        DemandeCongeDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds demandeConges by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<DemandeCongeDto>> findByCriteria(@RequestBody DemandeCongeCriteria criteria) throws Exception {
        ResponseEntity<List<DemandeCongeDto>> res = null;
        List<DemandeConge> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<DemandeCongeDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated demandeConges by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody DemandeCongeCriteria criteria) throws Exception {
        List<DemandeConge> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<DemandeCongeDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets demandeConge data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody DemandeCongeCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<DemandeCongeDto> findDtos(List<DemandeConge> list){
        converter.initObject(true);
        List<DemandeCongeDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<DemandeCongeDto> getDtoResponseEntity(DemandeCongeDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private DemandeCongeAdminService service;
    @Autowired private DemandeCongeConverter converter;





}
