package  ma.zs.GestionAgents.ws.facade.utilisateur.Poste;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zs.GestionAgents.bean.core.Poste.RaisonDemandeAbsence;
import ma.zs.GestionAgents.dao.criteria.core.Poste.RaisonDemandeAbsenceCriteria;
import ma.zs.GestionAgents.service.facade.utilisateur.Poste.RaisonDemandeAbsenceUtilisateurService;
import ma.zs.GestionAgents.ws.converter.Poste.RaisonDemandeAbsenceConverter;
import ma.zs.GestionAgents.ws.dto.Poste.RaisonDemandeAbsenceDto;
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
@RequestMapping("/api/utilisateur/raisonDemandeAbsence/")
public class RaisonDemandeAbsenceRestUtilisateur {




    @Operation(summary = "Finds a list of all raisonDemandeAbsences")
    @GetMapping("")
    public ResponseEntity<List<RaisonDemandeAbsenceDto>> findAll() throws Exception {
        ResponseEntity<List<RaisonDemandeAbsenceDto>> res = null;
        List<RaisonDemandeAbsence> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<RaisonDemandeAbsenceDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all raisonDemandeAbsences")
    @GetMapping("optimized")
    public ResponseEntity<List<RaisonDemandeAbsenceDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<RaisonDemandeAbsenceDto>> res = null;
        List<RaisonDemandeAbsence> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<RaisonDemandeAbsenceDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a raisonDemandeAbsence by id")
    @GetMapping("id/{id}")
    public ResponseEntity<RaisonDemandeAbsenceDto> findById(@PathVariable Long id) {
        RaisonDemandeAbsence t = service.findById(id);
        if (t != null) {
            RaisonDemandeAbsenceDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a raisonDemandeAbsence by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<RaisonDemandeAbsenceDto> findByLibelle(@PathVariable String libelle) {
	    RaisonDemandeAbsence t = service.findByReferenceEntity(new RaisonDemandeAbsence(libelle));
        if (t != null) {
            RaisonDemandeAbsenceDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  raisonDemandeAbsence")
    @PostMapping("")
    public ResponseEntity<RaisonDemandeAbsenceDto> save(@RequestBody RaisonDemandeAbsenceDto dto) throws Exception {
        if(dto!=null){
            RaisonDemandeAbsence myT = converter.toItem(dto);
            RaisonDemandeAbsence t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                RaisonDemandeAbsenceDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  raisonDemandeAbsence")
    @PutMapping("")
    public ResponseEntity<RaisonDemandeAbsenceDto> update(@RequestBody RaisonDemandeAbsenceDto dto) throws Exception {
        ResponseEntity<RaisonDemandeAbsenceDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            RaisonDemandeAbsence t = service.findById(dto.getId());
            converter.copy(dto,t);
            RaisonDemandeAbsence updated = service.update(t);
            RaisonDemandeAbsenceDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of raisonDemandeAbsence")
    @PostMapping("multiple")
    public ResponseEntity<List<RaisonDemandeAbsenceDto>> delete(@RequestBody List<RaisonDemandeAbsenceDto> dtos) throws Exception {
        ResponseEntity<List<RaisonDemandeAbsenceDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<RaisonDemandeAbsence> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified raisonDemandeAbsence")
    @DeleteMapping("")
    public ResponseEntity<RaisonDemandeAbsenceDto> delete(@RequestBody RaisonDemandeAbsenceDto dto) throws Exception {
		ResponseEntity<RaisonDemandeAbsenceDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            RaisonDemandeAbsence t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified raisonDemandeAbsence")
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
    @Operation(summary = "Delete multiple raisonDemandeAbsences by ids")
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



    @Operation(summary = "Finds a raisonDemandeAbsence and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<RaisonDemandeAbsenceDto> findWithAssociatedLists(@PathVariable Long id) {
        RaisonDemandeAbsence loaded =  service.findWithAssociatedLists(id);
        RaisonDemandeAbsenceDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds raisonDemandeAbsences by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<RaisonDemandeAbsenceDto>> findByCriteria(@RequestBody RaisonDemandeAbsenceCriteria criteria) throws Exception {
        ResponseEntity<List<RaisonDemandeAbsenceDto>> res = null;
        List<RaisonDemandeAbsence> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<RaisonDemandeAbsenceDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated raisonDemandeAbsences by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody RaisonDemandeAbsenceCriteria criteria) throws Exception {
        List<RaisonDemandeAbsence> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<RaisonDemandeAbsenceDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets raisonDemandeAbsence data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody RaisonDemandeAbsenceCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<RaisonDemandeAbsenceDto> findDtos(List<RaisonDemandeAbsence> list){
        List<RaisonDemandeAbsenceDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<RaisonDemandeAbsenceDto> getDtoResponseEntity(RaisonDemandeAbsenceDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private RaisonDemandeAbsenceUtilisateurService service;
    @Autowired private RaisonDemandeAbsenceConverter converter;





}
