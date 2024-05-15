package  ma.zs.GestionAgents.ws.facade.utilisateur.DemandeAbsence;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zs.GestionAgents.bean.core.DemandeAbsence.DemandeAbsence;
import ma.zs.GestionAgents.dao.criteria.core.DemandeAbsence.DemandeAbsenceCriteria;
import ma.zs.GestionAgents.service.facade.utilisateur.DemandeAbsence.DemandeAbsenceUtilisateurService;
import ma.zs.GestionAgents.ws.converter.DemandeAbsence.DemandeAbsenceConverter;
import ma.zs.GestionAgents.ws.dto.DemandeAbsence.DemandeAbsenceDto;
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
@RequestMapping("/api/utilisateur/demandeAbsence/")
public class DemandeAbsenceRestUtilisateur {




    @Operation(summary = "Finds a list of all demandeAbsences")
    @GetMapping("")
    public ResponseEntity<List<DemandeAbsenceDto>> findAll() throws Exception {
        ResponseEntity<List<DemandeAbsenceDto>> res = null;
        List<DemandeAbsence> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<DemandeAbsenceDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all demandeAbsences")
    @GetMapping("optimized")
    public ResponseEntity<List<DemandeAbsenceDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<DemandeAbsenceDto>> res = null;
        List<DemandeAbsence> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<DemandeAbsenceDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a demandeAbsence by id")
    @GetMapping("id/{id}")
    public ResponseEntity<DemandeAbsenceDto> findById(@PathVariable Long id) {
        DemandeAbsence t = service.findById(id);
        if (t != null) {
            converter.init(true);
            DemandeAbsenceDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a demandeAbsence by ref")
    @GetMapping("ref/{ref}")
    public ResponseEntity<DemandeAbsenceDto> findByRef(@PathVariable String ref) {
	    DemandeAbsence t = service.findByReferenceEntity(new DemandeAbsence(ref));
        if (t != null) {
            converter.init(true);
            DemandeAbsenceDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  demandeAbsence")
    @PostMapping("")
    public ResponseEntity<DemandeAbsenceDto> save(@RequestBody DemandeAbsenceDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            DemandeAbsence myT = converter.toItem(dto);
            DemandeAbsence t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                DemandeAbsenceDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  demandeAbsence")
    @PutMapping("")
    public ResponseEntity<DemandeAbsenceDto> update(@RequestBody DemandeAbsenceDto dto) throws Exception {
        ResponseEntity<DemandeAbsenceDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            DemandeAbsence t = service.findById(dto.getId());
            converter.copy(dto,t);
            DemandeAbsence updated = service.update(t);
            DemandeAbsenceDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of demandeAbsence")
    @PostMapping("multiple")
    public ResponseEntity<List<DemandeAbsenceDto>> delete(@RequestBody List<DemandeAbsenceDto> dtos) throws Exception {
        ResponseEntity<List<DemandeAbsenceDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<DemandeAbsence> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified demandeAbsence")
    @DeleteMapping("")
    public ResponseEntity<DemandeAbsenceDto> delete(@RequestBody DemandeAbsenceDto dto) throws Exception {
		ResponseEntity<DemandeAbsenceDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            converter.init(false);
            DemandeAbsence t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified demandeAbsence")
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
    @Operation(summary = "Delete multiple demandeAbsences by ids")
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



    @Operation(summary = "Finds a demandeAbsence and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<DemandeAbsenceDto> findWithAssociatedLists(@PathVariable Long id) {
        DemandeAbsence loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        DemandeAbsenceDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds demandeAbsences by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<DemandeAbsenceDto>> findByCriteria(@RequestBody DemandeAbsenceCriteria criteria) throws Exception {
        ResponseEntity<List<DemandeAbsenceDto>> res = null;
        List<DemandeAbsence> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<DemandeAbsenceDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated demandeAbsences by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody DemandeAbsenceCriteria criteria) throws Exception {
        List<DemandeAbsence> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<DemandeAbsenceDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets demandeAbsence data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody DemandeAbsenceCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<DemandeAbsenceDto> findDtos(List<DemandeAbsence> list){
        converter.initObject(true);
        List<DemandeAbsenceDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<DemandeAbsenceDto> getDtoResponseEntity(DemandeAbsenceDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private DemandeAbsenceUtilisateurService service;
    @Autowired private DemandeAbsenceConverter converter;





}
