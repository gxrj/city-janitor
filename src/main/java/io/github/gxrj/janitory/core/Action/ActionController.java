package io.github.gxrj.janitory.core.Action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ActionController {
    
    @Autowired
    private ActionService actionService;

    @PostMapping( "/agent/action/list_by_call" )
    public List<Action> listByCall( ActionDto dto ) {
        return actionService.listByCall( dto.callProtocol );
    }
}
