package com.project.habitual.Database;

import org.springframework.stereotype.Component;

@Component
public class UserAdapter extends AbstractMySqlAdapter{
    private String table = "User";

    public String insertUser(String email, String password) {
        String[] values = {email, password};
        return this.insert(this.table, values);
    }
}
