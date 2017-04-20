package com.ridelr.site.auth;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Date;

public class SiteUser extends User {

    private final String id;
    private final Date created;


    public SiteUser(String id, String username, String password,
                    Collection<? extends GrantedAuthority> authorities,
                    Date created) {
        super(username, password, authorities);
        this.id = id;
        this.created = created;
    }

    public SiteUser(String id, String username, String password, boolean enabled, boolean accountNonExpired,
                    boolean credentialsNonExpired, boolean accountNonLocked,
                    Collection<? extends GrantedAuthority> authorities,
                    Date created) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.id = id;
        this.created = created;
    }

    public String getId() {
        return id;
    }

    public Date getCreated() {
        return created;
    }

    @Override
    public String toString() {
        return "SiteUser{" +
                "id='" + id + '\'' +
                ", created=" + created +
                "} " + super.toString();
    }
}
