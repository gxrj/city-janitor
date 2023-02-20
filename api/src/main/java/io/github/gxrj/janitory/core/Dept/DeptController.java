package io.github.gxrj.janitory.core.Dept;

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
public class DeptController {

    @Autowired
    private DeptService deptService;
    
    @GetMapping( "/agent/dept/all" )
    public List<Dept> listAllDepartments() {
        return deptService.list();
    }

    @PostMapping( path = { "/manager/dept/new", "/manager/dept/edition" } )
    public String createOrUpdate( @RequestBody Dept dept ) {
        deptService.createOrUpdate( dept );
        return PlainJson.builder().append( "message", "Gravado com sucesso!" ).build();
    }
}
