package io.github.gxrj.janitory.core.DutyGroup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.gxrj.janitory.utils.PlainJson;

import java.util.List;

@RestController
@RequestMapping( consumes = "application/json", produces = "application/json" )
public class DutyGroupController {
    
    @Autowired
    private DutyGroupService groupService;

    @GetMapping( "/anonymous/category/all" )
    public List<DutyGroup> list() {
        return groupService.listAll();
    }

    @PostMapping( { "/manager/category/new", "/manager/category/edition" } )
    public String createOrUpdate( @RequestBody DutyGroup group ) {
        groupService.createOrUpdate( group );
        return PlainJson.builder().append( "message", "Categoria registarda com sucesso!" ).build();
    }

    @PostMapping( "/manager/category/deletion" )
    public String delete( DutyGroup entity ) {
        groupService.delete( entity );
        return PlainJson.builder().append( "message", "Deletado com sucesso!" ).build();
    }
}
