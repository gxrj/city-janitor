package io.github.gxrj.janitory.core.Dept;

import io.github.gxrj.janitory.utils.PlainJson;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( consumes = "application/json", produces = "application/json" )
public class DeptController {

    @Autowired
    private DeptService deptService;
    
    @PostMapping( "/agent/dept/all" )
    public List<Dept> listAllDepartments() {
        return deptService.list();
    }

    @PostMapping( "/manager/dept/new" )
    public String create( @RequestBody DeptDto dept ) {
        deptService.createOrUpdate( new Dept( dept.name ) );
        return PlainJson.builder().append( "message", "Gravado com sucesso!" ).build();
    }

    @PostMapping( "/manager/dept/edition" )
    public String update( @RequestBody DeptDto dto ) {

        var dept = deptService.findByName( dto.name );
        
        if( dept == null ) 
            return PlainJson.builder().append( "error", "Departamento " + dto.name +" inexistente" ).build();

        deptService.createOrUpdate( dept );

        return PlainJson.builder().append( "message", "Departamento atualizado" ).build();
    }
}
