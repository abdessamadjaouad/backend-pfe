package  ma.zs.GestionAgents.ws.facade.utilisateur.DemandeDocumentAdministratif;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zs.GestionAgents.bean.core.DemandeDocumentAdministratif.DemandeDocumentAdministratif;
import ma.zs.GestionAgents.dao.criteria.core.DemandeDocumentAdministratif.DemandeDocumentAdministratifCriteria;
import ma.zs.GestionAgents.service.facade.utilisateur.DemandeDocumentAdministratif.DemandeDocumentAdministratifUtilisateurService;
import ma.zs.GestionAgents.ws.converter.DemandeDocumentAdministratif.DemandeDocumentAdministratifConverter;
import ma.zs.GestionAgents.ws.dto.DemandeDocumentAdministratif.DemandeDocumentAdministratifDto;
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
@RequestMapping("/api/utilisateur/demandeDocumentAdministratif/")
public class DemandeDocumentAdministratifRestUtilisateur {




    @Operation(summary = "Finds a list of all demandeDocumentAdministratifs")
    @GetMapping("")
    public ResponseEntity<List<DemandeDocumentAdministratifDto>> findAll() throws Exception {
        ResponseEntity<List<DemandeDocumentAdministratifDto>> res = null;
        List<DemandeDocumentAdministratif> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<DemandeDocumentAdministratifDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all demandeDocumentAdministratifs")
    @GetMapping("optimized")
    public ResponseEntity<List<DemandeDocumentAdministratifDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<DemandeDocumentAdministratifDto>> res = null;
        List<DemandeDocumentAdministratif> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<DemandeDocumentAdministratifDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a demandeDocumentAdministratif by id")
    @GetMapping("id/{id}")
    public ResponseEntity<DemandeDocumentAdministratifDto> findById(@PathVariable Long id) {
        DemandeDocumentAdministratif t = service.findById(id);
        if (t != null) {
            DemandeDocumentAdministratifDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a demandeDocumentAdministratif by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<DemandeDocumentAdministratifDto> findByLibelle(@PathVariable String libelle) {
	    DemandeDocumentAdministratif t = service.findByReferenceEntity(new DemandeDocumentAdministratif(libelle));
        if (t != null) {
            DemandeDocumentAdministratifDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  demandeDocumentAdministratif")
    @PostMapping("")
    public ResponseEntity<DemandeDocumentAdministratifDto> save(@RequestBody DemandeDocumentAdministratifDto dto) throws Exception {
        if(dto!=null){
            DemandeDocumentAdministratif myT = converter.toItem(dto);
            DemandeDocumentAdministratif t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                DemandeDocumentAdministratifDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  demandeDocumentAdministratif")
    @PutMapping("")
    public ResponseEntity<DemandeDocumentAdministratifDto> update(@RequestBody DemandeDocumentAdministratifDto dto) throws Exception {
        ResponseEntity<DemandeDocumentAdministratifDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            DemandeDocumentAdministratif t = service.findById(dto.getId());
            converter.copy(dto,t);
            DemandeDocumentAdministratif updated = service.update(t);
            DemandeDocumentAdministratifDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of demandeDocumentAdministratif")
    @PostMapping("multiple")
    public ResponseEntity<List<DemandeDocumentAdministratifDto>> delete(@RequestBody List<DemandeDocumentAdministratifDto> dtos) throws Exception {
        ResponseEntity<List<DemandeDocumentAdministratifDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<DemandeDocumentAdministratif> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified demandeDocumentAdministratif")
    @DeleteMapping("")
    public ResponseEntity<DemandeDocumentAdministratifDto> delete(@RequestBody DemandeDocumentAdministratifDto dto) throws Exception {
		ResponseEntity<DemandeDocumentAdministratifDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            DemandeDocumentAdministratif t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified demandeDocumentAdministratif")
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
    @Operation(summary = "Delete multiple demandeDocumentAdministratifs by ids")
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



    @Operation(summary = "Finds a demandeDocumentAdministratif and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<DemandeDocumentAdministratifDto> findWithAssociatedLists(@PathVariable Long id) {
        DemandeDocumentAdministratif loaded =  service.findWithAssociatedLists(id);
        DemandeDocumentAdministratifDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds demandeDocumentAdministratifs by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<DemandeDocumentAdministratifDto>> findByCriteria(@RequestBody DemandeDocumentAdministratifCriteria criteria) throws Exception {
        ResponseEntity<List<DemandeDocumentAdministratifDto>> res = null;
        List<DemandeDocumentAdministratif> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<DemandeDocumentAdministratifDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated demandeDocumentAdministratifs by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody DemandeDocumentAdministratifCriteria criteria) throws Exception {
        List<DemandeDocumentAdministratif> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<DemandeDocumentAdministratifDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets demandeDocumentAdministratif data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody DemandeDocumentAdministratifCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<DemandeDocumentAdministratifDto> findDtos(List<DemandeDocumentAdministratif> list){
        List<DemandeDocumentAdministratifDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<DemandeDocumentAdministratifDto> getDtoResponseEntity(DemandeDocumentAdministratifDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private DemandeDocumentAdministratifUtilisateurService service;
    @Autowired private DemandeDocumentAdministratifConverter converter;





}
