package  ma.zs.GestionAgents.ws.facade.utilisateur.Absence;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zs.GestionAgents.bean.core.Absence.Absence;
import ma.zs.GestionAgents.dao.criteria.core.Absence.AbsenceCriteria;
import ma.zs.GestionAgents.service.facade.utilisateur.Absence.AbsenceUtilisateurService;
import ma.zs.GestionAgents.ws.converter.Absence.AbsenceConverter;
import ma.zs.GestionAgents.ws.dto.Absence.AbsenceDto;
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
@RequestMapping("/api/utilisateur/absence/")
public class AbsenceRestUtilisateur {




    @Operation(summary = "Finds a list of all absences")
    @GetMapping("")
    public ResponseEntity<List<AbsenceDto>> findAll() throws Exception {
        ResponseEntity<List<AbsenceDto>> res = null;
        List<Absence> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<AbsenceDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all absences")
    @GetMapping("optimized")
    public ResponseEntity<List<AbsenceDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<AbsenceDto>> res = null;
        List<Absence> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<AbsenceDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a absence by id")
    @GetMapping("id/{id}")
    public ResponseEntity<AbsenceDto> findById(@PathVariable Long id) {
        Absence t = service.findById(id);
        if (t != null) {
            AbsenceDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a absence by ref")
    @GetMapping("ref/{ref}")
    public ResponseEntity<AbsenceDto> findByRef(@PathVariable String ref) {
	    Absence t = service.findByReferenceEntity(new Absence(ref));
        if (t != null) {
            AbsenceDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  absence")
    @PostMapping("")
    public ResponseEntity<AbsenceDto> save(@RequestBody AbsenceDto dto) throws Exception {
        if(dto!=null){
            Absence myT = converter.toItem(dto);
            Absence t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                AbsenceDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  absence")
    @PutMapping("")
    public ResponseEntity<AbsenceDto> update(@RequestBody AbsenceDto dto) throws Exception {
        ResponseEntity<AbsenceDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Absence t = service.findById(dto.getId());
            converter.copy(dto,t);
            Absence updated = service.update(t);
            AbsenceDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of absence")
    @PostMapping("multiple")
    public ResponseEntity<List<AbsenceDto>> delete(@RequestBody List<AbsenceDto> dtos) throws Exception {
        ResponseEntity<List<AbsenceDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<Absence> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified absence")
    @DeleteMapping("")
    public ResponseEntity<AbsenceDto> delete(@RequestBody AbsenceDto dto) throws Exception {
		ResponseEntity<AbsenceDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            Absence t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified absence")
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
    @Operation(summary = "Delete multiple absences by ids")
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



    @Operation(summary = "Finds a absence and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<AbsenceDto> findWithAssociatedLists(@PathVariable Long id) {
        Absence loaded =  service.findWithAssociatedLists(id);
        AbsenceDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds absences by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<AbsenceDto>> findByCriteria(@RequestBody AbsenceCriteria criteria) throws Exception {
        ResponseEntity<List<AbsenceDto>> res = null;
        List<Absence> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<AbsenceDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated absences by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody AbsenceCriteria criteria) throws Exception {
        List<Absence> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<AbsenceDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets absence data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody AbsenceCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<AbsenceDto> findDtos(List<Absence> list){
        List<AbsenceDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<AbsenceDto> getDtoResponseEntity(AbsenceDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private AbsenceUtilisateurService service;
    @Autowired private AbsenceConverter converter;





}
