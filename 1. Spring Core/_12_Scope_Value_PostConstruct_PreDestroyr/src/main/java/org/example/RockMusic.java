package org.example;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author Neil Alishev
 */
@Component
@Scope("prototype")
public class RockMusic implements Music {
    @Override
    public String getSong() {
        return "Rock is forever";
    }
}
