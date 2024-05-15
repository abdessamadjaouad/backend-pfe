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

import ma.zs.GestionAgents.bean.core.Poste.RaisonDemandeConge;
import ma.zs.GestionAgents.dao.criteria.core.Poste.RaisonDemandeCongeCriteria;
import ma.zs.GestionAgents.service.facade.utilisateur.Poste.RaisonDemandeCongeUtilisateurService;
import ma.zs.GestionAgents.ws.converter.Poste.RaisonDemandeCongeConverter;
import ma.zs.GestionAgents.ws.dto.Poste.RaisonDemandeCongeDto;
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
@RequestMapping("/api/utilisateur/raisonDemandeConge/")
public class RaisonDemandeCongeRestUtilisateur {




    @Operation(summary = "Finds a list of all raisonDemandeConges")
    @GetMapping("")
    public ResponseEntity<List<RaisonDemandeCongeDto>> findAll() throws Exception {
        ResponseEntity<List<RaisonDemandeCongeDto>> res = null;
        List<RaisonDemandeConge> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<RaisonDemandeCongeDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all raisonDemandeConges")
    @GetMapping("optimized")
    public ResponseEntity<List<RaisonDemandeCongeDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<RaisonDemandeCongeDto>> res = null;
        List<RaisonDemandeConge> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<RaisonDemandeCongeDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a raisonDemandeConge by id")
    @GetMapping("id/{id}")
    public ResponseEntity<RaisonDemandeCongeDto> findById(@PathVariable Long id) {
        RaisonDemandeConge t = service.findById(id);
        if (t != null) {
            RaisonDemandeCongeDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a raisonDemandeConge by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<RaisonDemandeCongeDto> findByLibelle(@PathVariable String libelle) {
	    RaisonDemandeConge t = service.findByReferenceEntity(new RaisonDemandeConge(libelle));
        if (t != null) {
            RaisonDemandeCongeDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  raisonDemandeConge")
    @PostMapping("")
    public ResponseEntity<RaisonDemandeCongeDto> save(@RequestBody RaisonDemandeCongeDto dto) throws Exception {
        if(dto!=null){
            RaisonDemandeConge myT = converter.toItem(dto);
            RaisonDemandeConge t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                RaisonDemandeCongeDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  raisonDemandeConge")
    @PutMapping("")
    public ResponseEntity<RaisonDemandeCongeDto> update(@RequestBody RaisonDemandeCongeDto dto) throws Exception {
        ResponseEntity<RaisonDemandeCongeDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            RaisonDemandeConge t = service.findById(dto.getId());
            converter.copy(dto,t);
            RaisonDemandeConge updated = service.update(t);
            RaisonDemandeCongeDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of raisonDemandeConge")
    @PostMapping("multiple")
    public ResponseEntity<List<RaisonDemandeCongeDto>> delete(@RequestBody List<RaisonDemandeCongeDto> dtos) throws Exception {
        ResponseEntity<List<RaisonDemandeCongeDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<RaisonDemandeConge> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified raisonDemandeConge")
    @DeleteMapping("")
    public ResponseEntity<RaisonDemandeCongeDto> delete(@RequestBody RaisonDemandeCongeDto dto) throws Exception {
		ResponseEntity<RaisonDemandeCongeDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            RaisonDemandeConge t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified raisonDemandeConge")
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
    @Operation(summary = "Delete multiple raisonDemandeConges by ids")
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



    @Operation(summary = "Finds a raisonDemandeConge and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<RaisonDemandeCongeDto> findWithAssociatedLists(@PathVariable Long id) {
        RaisonDemandeConge loaded =  service.findWithAssociatedLists(id);
        RaisonDemandeCongeDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds raisonDemandeConges by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<RaisonDemandeCongeDto>> findByCriteria(@RequestBody RaisonDemandeCongeCriteria criteria) throws Exception {
        ResponseEntity<List<RaisonDemandeCongeDto>> res = null;
        List<RaisonDemandeConge> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<RaisonDemandeCongeDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated raisonDemandeConges by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody RaisonDemandeCongeCriteria criteria) throws Exception {
        List<RaisonDemandeConge> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<RaisonDemandeCongeDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets raisonDemandeConge data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody RaisonDemandeCongeCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<RaisonDemandeCongeDto> findDtos(List<RaisonDemandeConge> list){
        List<RaisonDemandeCongeDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<RaisonDemandeCongeDto> getDtoResponseEntity(RaisonDemandeCongeDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private RaisonDemandeCongeUtilisateurService service;
    @Autowired private RaisonDemandeCongeConverter converter;





}
