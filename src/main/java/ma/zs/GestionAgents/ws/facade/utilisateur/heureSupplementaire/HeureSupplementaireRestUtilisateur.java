package  ma.zs.GestionAgents.ws.facade.utilisateur.heureSupplementaire;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zs.GestionAgents.bean.core.heureSupplementaire.HeureSupplementaire;
import ma.zs.GestionAgents.dao.criteria.core.heureSupplementaire.HeureSupplementaireCriteria;
import ma.zs.GestionAgents.service.facade.utilisateur.heureSupplementaire.HeureSupplementaireUtilisateurService;
import ma.zs.GestionAgents.ws.converter.heureSupplementaire.HeureSupplementaireConverter;
import ma.zs.GestionAgents.ws.dto.heureSupplementaire.HeureSupplementaireDto;
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
@RequestMapping("/api/utilisateur/heureSupplementaire/")
public class HeureSupplementaireRestUtilisateur {




    @Operation(summary = "Finds a list of all heureSupplementaires")
    @GetMapping("")
    public ResponseEntity<List<HeureSupplementaireDto>> findAll() throws Exception {
        ResponseEntity<List<HeureSupplementaireDto>> res = null;
        List<HeureSupplementaire> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<HeureSupplementaireDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all heureSupplementaires")
    @GetMapping("optimized")
    public ResponseEntity<List<HeureSupplementaireDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<HeureSupplementaireDto>> res = null;
        List<HeureSupplementaire> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<HeureSupplementaireDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a heureSupplementaire by id")
    @GetMapping("id/{id}")
    public ResponseEntity<HeureSupplementaireDto> findById(@PathVariable Long id) {
        HeureSupplementaire t = service.findById(id);
        if (t != null) {
            HeureSupplementaireDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a heureSupplementaire by ref")
    @GetMapping("ref/{ref}")
    public ResponseEntity<HeureSupplementaireDto> findByRef(@PathVariable String ref) {
	    HeureSupplementaire t = service.findByReferenceEntity(new HeureSupplementaire(ref));
        if (t != null) {
            HeureSupplementaireDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  heureSupplementaire")
    @PostMapping("")
    public ResponseEntity<HeureSupplementaireDto> save(@RequestBody HeureSupplementaireDto dto) throws Exception {
        if(dto!=null){
            HeureSupplementaire myT = converter.toItem(dto);
            HeureSupplementaire t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                HeureSupplementaireDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  heureSupplementaire")
    @PutMapping("")
    public ResponseEntity<HeureSupplementaireDto> update(@RequestBody HeureSupplementaireDto dto) throws Exception {
        ResponseEntity<HeureSupplementaireDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            HeureSupplementaire t = service.findById(dto.getId());
            converter.copy(dto,t);
            HeureSupplementaire updated = service.update(t);
            HeureSupplementaireDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of heureSupplementaire")
    @PostMapping("multiple")
    public ResponseEntity<List<HeureSupplementaireDto>> delete(@RequestBody List<HeureSupplementaireDto> dtos) throws Exception {
        ResponseEntity<List<HeureSupplementaireDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<HeureSupplementaire> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified heureSupplementaire")
    @DeleteMapping("")
    public ResponseEntity<HeureSupplementaireDto> delete(@RequestBody HeureSupplementaireDto dto) throws Exception {
		ResponseEntity<HeureSupplementaireDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            HeureSupplementaire t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified heureSupplementaire")
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
    @Operation(summary = "Delete multiple heureSupplementaires by ids")
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



    @Operation(summary = "Finds a heureSupplementaire and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<HeureSupplementaireDto> findWithAssociatedLists(@PathVariable Long id) {
        HeureSupplementaire loaded =  service.findWithAssociatedLists(id);
        HeureSupplementaireDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds heureSupplementaires by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<HeureSupplementaireDto>> findByCriteria(@RequestBody HeureSupplementaireCriteria criteria) throws Exception {
        ResponseEntity<List<HeureSupplementaireDto>> res = null;
        List<HeureSupplementaire> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<HeureSupplementaireDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated heureSupplementaires by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody HeureSupplementaireCriteria criteria) throws Exception {
        List<HeureSupplementaire> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<HeureSupplementaireDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets heureSupplementaire data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody HeureSupplementaireCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<HeureSupplementaireDto> findDtos(List<HeureSupplementaire> list){
        List<HeureSupplementaireDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<HeureSupplementaireDto> getDtoResponseEntity(HeureSupplementaireDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private HeureSupplementaireUtilisateurService service;
    @Autowired private HeureSupplementaireConverter converter;





}
