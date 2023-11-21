package by.vinokurov.springcourse.SecurityApp.services;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    //@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    //@PreAuthorize("hasRole('ROLE_ADMIN') and hasRole('ROLE_USER')")
    public void doAdminStuff() {
        System.out.println("Only admin here!");
    }
}
