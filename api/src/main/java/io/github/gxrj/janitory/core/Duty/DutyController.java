package io.github.gxrj.janitory.core.Duty;

import io.github.gxrj.janitory.utils.PlainJson;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( consumes = "application/json", produces = "application/json" )
public class DutyController {
    
    @Autowired
    private DutyService dutyService;


    @GetMapping( "/anonymous/duty/all" )
    public List<Duty> list() {
        return dutyService.listAll();
    }

    @GetMapping( "/anonymous/duty/all_by_category" )
    public List<Duty> listByGroupName( String groupName ) {
        return dutyService.listByGroupName( groupName );
    }

    @GetMapping( "/agent/duty/all_by_department" )
    public List<Duty> listByDeptName( String deptName ) {
        return dutyService.listByDeptName( deptName );
    }

    @PostMapping( { "/manager/duty/new", "/manager/duty/edition" } )
    public String create( @RequestBody Duty duty ) {
        dutyService.createOrUpdate( duty );
        return PlainJson.builder().append( "message", "Servico criado com sucesso!" ).build();
    }
}
