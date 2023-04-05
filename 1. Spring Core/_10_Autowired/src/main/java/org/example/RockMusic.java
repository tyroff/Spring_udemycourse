package org.example;

import org.springframework.stereotype.Component;

/**
 * @author Neil Alishev
 */
@Component
public class RockMusic implements Music {
    @Override
    public String getSong() {
        return "Rock is forever";
    }
}
