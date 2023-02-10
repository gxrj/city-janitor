package io.github.gxrj.janitory.core.Address;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.gxrj.janitory.utils.PlainJson;

@RestController
@RequestMapping( consumes = "application/json", produces = "application/json" )
public class DistrictController {
 
    @Autowired
    private DistrictService districtService;

    @GetMapping( "/anonymous/district/all" )
    public List<District> listAll() {
        return districtService.findAll();
    }

    @PostMapping( { "/manager/district/new", "/manager/district/edition" } )
    public String createOrUpdate( @RequestBody District district ) {
        districtService.createOrUpdate( district );
        return PlainJson.builder().append( "messsage", "Gravado com sucesso!" ).build();
    }

    @PostMapping( "/manager/district/deletion" )
    public String delete( @RequestBody District district ) {
        districtService.delete( district );
        return PlainJson.builder().append( "messsage", "Deletado com sucesso!" ).build();
    }
}
