package  ma.zs.GestionAgents.ws.facade.utilisateur.plageHoraire;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zs.GestionAgents.bean.core.plageHoraire.PlageHoraire;
import ma.zs.GestionAgents.dao.criteria.core.plageHoraire.PlageHoraireCriteria;
import ma.zs.GestionAgents.service.facade.utilisateur.plageHoraire.PlageHoraireUtilisateurService;
import ma.zs.GestionAgents.ws.converter.plageHoraire.PlageHoraireConverter;
import ma.zs.GestionAgents.ws.dto.plageHoraire.PlageHoraireDto;
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
@RequestMapping("/api/utilisateur/plageHoraire/")
public class PlageHoraireRestUtilisateur {




    @Operation(summary = "Finds a list of all plageHoraires")
    @GetMapping("")
    public ResponseEntity<List<PlageHoraireDto>> findAll() throws Exception {
        ResponseEntity<List<PlageHoraireDto>> res = null;
        List<PlageHoraire> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<PlageHoraireDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all plageHoraires")
    @GetMapping("optimized")
    public ResponseEntity<List<PlageHoraireDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<PlageHoraireDto>> res = null;
        List<PlageHoraire> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<PlageHoraireDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a plageHoraire by id")
    @GetMapping("id/{id}")
    public ResponseEntity<PlageHoraireDto> findById(@PathVariable Long id) {
        PlageHoraire t = service.findById(id);
        if (t != null) {
            PlageHoraireDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a plageHoraire by ref")
    @GetMapping("ref/{ref}")
    public ResponseEntity<PlageHoraireDto> findByRef(@PathVariable String ref) {
	    PlageHoraire t = service.findByReferenceEntity(new PlageHoraire(ref));
        if (t != null) {
            PlageHoraireDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  plageHoraire")
    @PostMapping("")
    public ResponseEntity<PlageHoraireDto> save(@RequestBody PlageHoraireDto dto) throws Exception {
        if(dto!=null){
            PlageHoraire myT = converter.toItem(dto);
            PlageHoraire t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                PlageHoraireDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  plageHoraire")
    @PutMapping("")
    public ResponseEntity<PlageHoraireDto> update(@RequestBody PlageHoraireDto dto) throws Exception {
        ResponseEntity<PlageHoraireDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            PlageHoraire t = service.findById(dto.getId());
            converter.copy(dto,t);
            PlageHoraire updated = service.update(t);
            PlageHoraireDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of plageHoraire")
    @PostMapping("multiple")
    public ResponseEntity<List<PlageHoraireDto>> delete(@RequestBody List<PlageHoraireDto> dtos) throws Exception {
        ResponseEntity<List<PlageHoraireDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<PlageHoraire> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified plageHoraire")
    @DeleteMapping("")
    public ResponseEntity<PlageHoraireDto> delete(@RequestBody PlageHoraireDto dto) throws Exception {
		ResponseEntity<PlageHoraireDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            PlageHoraire t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified plageHoraire")
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
    @Operation(summary = "Delete multiple plageHoraires by ids")
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



    @Operation(summary = "Finds a plageHoraire and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<PlageHoraireDto> findWithAssociatedLists(@PathVariable Long id) {
        PlageHoraire loaded =  service.findWithAssociatedLists(id);
        PlageHoraireDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds plageHoraires by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<PlageHoraireDto>> findByCriteria(@RequestBody PlageHoraireCriteria criteria) throws Exception {
        ResponseEntity<List<PlageHoraireDto>> res = null;
        List<PlageHoraire> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<PlageHoraireDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated plageHoraires by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody PlageHoraireCriteria criteria) throws Exception {
        List<PlageHoraire> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<PlageHoraireDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets plageHoraire data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody PlageHoraireCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<PlageHoraireDto> findDtos(List<PlageHoraire> list){
        List<PlageHoraireDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<PlageHoraireDto> getDtoResponseEntity(PlageHoraireDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private PlageHoraireUtilisateurService service;
    @Autowired private PlageHoraireConverter converter;





}
