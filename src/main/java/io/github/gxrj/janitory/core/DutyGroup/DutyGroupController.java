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

    @GetMapping( "/anonymous/categories/list" )
    public List<DutyGroup> list() {
        return groupService.list();
    }

    @PostMapping( "/manager/categories/new" )
    public String create( @RequestBody DutyGroup group ) {
        
        return PlainJson.builder().append( "message", "Categoria criada com sucesso!" ).build();
    }
}
