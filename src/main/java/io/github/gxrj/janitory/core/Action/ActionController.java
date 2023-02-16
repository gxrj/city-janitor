package io.github.gxrj.janitory.core.Action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ActionController {
    
    @Autowired
    private ActionService actionService;

    @GetMapping( "/agent/action/list" )
    public List<Action> listByCall( ActionDto dto ) {
        return actionService.listByCall( dto.callProtocol );
    }
}
