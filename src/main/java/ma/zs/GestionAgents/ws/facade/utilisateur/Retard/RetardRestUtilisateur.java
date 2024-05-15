package  ma.zs.GestionAgents.ws.facade.utilisateur.Retard;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zs.GestionAgents.bean.core.Retard.Retard;
import ma.zs.GestionAgents.dao.criteria.core.Retard.RetardCriteria;
import ma.zs.GestionAgents.service.facade.utilisateur.Retard.RetardUtilisateurService;
import ma.zs.GestionAgents.ws.converter.Retard.RetardConverter;
import ma.zs.GestionAgents.ws.dto.Retard.RetardDto;
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
@RequestMapping("/api/utilisateur/retard/")
public class RetardRestUtilisateur {




    @Operation(summary = "Finds a list of all retards")
    @GetMapping("")
    public ResponseEntity<List<RetardDto>> findAll() throws Exception {
        ResponseEntity<List<RetardDto>> res = null;
        List<Retard> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<RetardDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all retards")
    @GetMapping("optimized")
    public ResponseEntity<List<RetardDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<RetardDto>> res = null;
        List<Retard> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<RetardDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a retard by id")
    @GetMapping("id/{id}")
    public ResponseEntity<RetardDto> findById(@PathVariable Long id) {
        Retard t = service.findById(id);
        if (t != null) {
            converter.init(true);
            RetardDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a retard by ref")
    @GetMapping("ref/{ref}")
    public ResponseEntity<RetardDto> findByRef(@PathVariable String ref) {
	    Retard t = service.findByReferenceEntity(new Retard(ref));
        if (t != null) {
            converter.init(true);
            RetardDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  retard")
    @PostMapping("")
    public ResponseEntity<RetardDto> save(@RequestBody RetardDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            Retard myT = converter.toItem(dto);
            Retard t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                RetardDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  retard")
    @PutMapping("")
    public ResponseEntity<RetardDto> update(@RequestBody RetardDto dto) throws Exception {
        ResponseEntity<RetardDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Retard t = service.findById(dto.getId());
            converter.copy(dto,t);
            Retard updated = service.update(t);
            RetardDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of retard")
    @PostMapping("multiple")
    public ResponseEntity<List<RetardDto>> delete(@RequestBody List<RetardDto> dtos) throws Exception {
        ResponseEntity<List<RetardDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<Retard> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified retard")
    @DeleteMapping("")
    public ResponseEntity<RetardDto> delete(@RequestBody RetardDto dto) throws Exception {
		ResponseEntity<RetardDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            converter.init(false);
            Retard t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified retard")
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
    @Operation(summary = "Delete multiple retards by ids")
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
    public List<RetardDto> findByAgentId(@PathVariable Long id){
        return findDtos(service.findByAgentId(id));
    }
    @Operation(summary = "delete by agent id")
    @DeleteMapping("agent/id/{id}")
    public int deleteByAgentId(@PathVariable Long id){
        return service.deleteByAgentId(id);
    }

    @Operation(summary = "Finds a retard and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<RetardDto> findWithAssociatedLists(@PathVariable Long id) {
        Retard loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        RetardDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds retards by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<RetardDto>> findByCriteria(@RequestBody RetardCriteria criteria) throws Exception {
        ResponseEntity<List<RetardDto>> res = null;
        List<Retard> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<RetardDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated retards by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody RetardCriteria criteria) throws Exception {
        List<Retard> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<RetardDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets retard data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody RetardCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<RetardDto> findDtos(List<Retard> list){
        converter.initObject(true);
        List<RetardDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<RetardDto> getDtoResponseEntity(RetardDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private RetardUtilisateurService service;
    @Autowired private RetardConverter converter;





}
