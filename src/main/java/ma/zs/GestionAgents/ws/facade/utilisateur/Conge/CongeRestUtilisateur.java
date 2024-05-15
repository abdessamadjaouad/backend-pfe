package  ma.zs.GestionAgents.ws.facade.utilisateur.Conge;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zs.GestionAgents.bean.core.Conge.Conge;
import ma.zs.GestionAgents.dao.criteria.core.Conge.CongeCriteria;
import ma.zs.GestionAgents.service.facade.utilisateur.Conge.CongeUtilisateurService;
import ma.zs.GestionAgents.ws.converter.Conge.CongeConverter;
import ma.zs.GestionAgents.ws.dto.Conge.CongeDto;
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
@RequestMapping("/api/utilisateur/conge/")
public class CongeRestUtilisateur {




    @Operation(summary = "Finds a list of all conges")
    @GetMapping("")
    public ResponseEntity<List<CongeDto>> findAll() throws Exception {
        ResponseEntity<List<CongeDto>> res = null;
        List<Conge> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<CongeDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all conges")
    @GetMapping("optimized")
    public ResponseEntity<List<CongeDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<CongeDto>> res = null;
        List<Conge> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<CongeDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a conge by id")
    @GetMapping("id/{id}")
    public ResponseEntity<CongeDto> findById(@PathVariable Long id) {
        Conge t = service.findById(id);
        if (t != null) {
            CongeDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a conge by ref")
    @GetMapping("ref/{ref}")
    public ResponseEntity<CongeDto> findByRef(@PathVariable String ref) {
	    Conge t = service.findByReferenceEntity(new Conge(ref));
        if (t != null) {
            CongeDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  conge")
    @PostMapping("")
    public ResponseEntity<CongeDto> save(@RequestBody CongeDto dto) throws Exception {
        if(dto!=null){
            Conge myT = converter.toItem(dto);
            Conge t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                CongeDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  conge")
    @PutMapping("")
    public ResponseEntity<CongeDto> update(@RequestBody CongeDto dto) throws Exception {
        ResponseEntity<CongeDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Conge t = service.findById(dto.getId());
            converter.copy(dto,t);
            Conge updated = service.update(t);
            CongeDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of conge")
    @PostMapping("multiple")
    public ResponseEntity<List<CongeDto>> delete(@RequestBody List<CongeDto> dtos) throws Exception {
        ResponseEntity<List<CongeDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<Conge> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified conge")
    @DeleteMapping("")
    public ResponseEntity<CongeDto> delete(@RequestBody CongeDto dto) throws Exception {
		ResponseEntity<CongeDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            Conge t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified conge")
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
    @Operation(summary = "Delete multiple conges by ids")
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



    @Operation(summary = "Finds a conge and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<CongeDto> findWithAssociatedLists(@PathVariable Long id) {
        Conge loaded =  service.findWithAssociatedLists(id);
        CongeDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds conges by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<CongeDto>> findByCriteria(@RequestBody CongeCriteria criteria) throws Exception {
        ResponseEntity<List<CongeDto>> res = null;
        List<Conge> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<CongeDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated conges by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody CongeCriteria criteria) throws Exception {
        List<Conge> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<CongeDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets conge data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody CongeCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<CongeDto> findDtos(List<Conge> list){
        List<CongeDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<CongeDto> getDtoResponseEntity(CongeDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private CongeUtilisateurService service;
    @Autowired private CongeConverter converter;





}
