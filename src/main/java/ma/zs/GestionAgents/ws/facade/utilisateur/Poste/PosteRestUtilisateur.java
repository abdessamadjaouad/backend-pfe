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

import ma.zs.GestionAgents.bean.core.Poste.Poste;
import ma.zs.GestionAgents.dao.criteria.core.Poste.PosteCriteria;
import ma.zs.GestionAgents.service.facade.utilisateur.Poste.PosteUtilisateurService;
import ma.zs.GestionAgents.ws.converter.Poste.PosteConverter;
import ma.zs.GestionAgents.ws.dto.Poste.PosteDto;
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
@RequestMapping("/api/utilisateur/poste/")
public class PosteRestUtilisateur {




    @Operation(summary = "Finds a list of all postes")
    @GetMapping("")
    public ResponseEntity<List<PosteDto>> findAll() throws Exception {
        ResponseEntity<List<PosteDto>> res = null;
        List<Poste> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<PosteDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all postes")
    @GetMapping("optimized")
    public ResponseEntity<List<PosteDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<PosteDto>> res = null;
        List<Poste> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<PosteDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a poste by id")
    @GetMapping("id/{id}")
    public ResponseEntity<PosteDto> findById(@PathVariable Long id) {
        Poste t = service.findById(id);
        if (t != null) {
            PosteDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a poste by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<PosteDto> findByLibelle(@PathVariable String libelle) {
	    Poste t = service.findByReferenceEntity(new Poste(libelle));
        if (t != null) {
            PosteDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  poste")
    @PostMapping("")
    public ResponseEntity<PosteDto> save(@RequestBody PosteDto dto) throws Exception {
        if(dto!=null){
            Poste myT = converter.toItem(dto);
            Poste t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                PosteDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  poste")
    @PutMapping("")
    public ResponseEntity<PosteDto> update(@RequestBody PosteDto dto) throws Exception {
        ResponseEntity<PosteDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Poste t = service.findById(dto.getId());
            converter.copy(dto,t);
            Poste updated = service.update(t);
            PosteDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of poste")
    @PostMapping("multiple")
    public ResponseEntity<List<PosteDto>> delete(@RequestBody List<PosteDto> dtos) throws Exception {
        ResponseEntity<List<PosteDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<Poste> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified poste")
    @DeleteMapping("")
    public ResponseEntity<PosteDto> delete(@RequestBody PosteDto dto) throws Exception {
		ResponseEntity<PosteDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            Poste t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified poste")
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
    @Operation(summary = "Delete multiple postes by ids")
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



    @Operation(summary = "Finds a poste and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<PosteDto> findWithAssociatedLists(@PathVariable Long id) {
        Poste loaded =  service.findWithAssociatedLists(id);
        PosteDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds postes by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<PosteDto>> findByCriteria(@RequestBody PosteCriteria criteria) throws Exception {
        ResponseEntity<List<PosteDto>> res = null;
        List<Poste> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<PosteDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated postes by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody PosteCriteria criteria) throws Exception {
        List<Poste> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<PosteDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets poste data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody PosteCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<PosteDto> findDtos(List<Poste> list){
        List<PosteDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<PosteDto> getDtoResponseEntity(PosteDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private PosteUtilisateurService service;
    @Autowired private PosteConverter converter;





}
