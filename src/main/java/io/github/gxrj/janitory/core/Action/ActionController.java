package io.github.gxrj.janitory.core.Action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ActionController {
    
    @Autowired
    private ActionService actionService;
}
