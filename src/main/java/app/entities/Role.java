package app.entities;

public enum Role {
    ADMINISTRATOR("administrator"), MODERATOR("moderator"), USER("user");

    private final String roleName;

    Role(String roleName){
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }

    public static Role getValue(String value) {
        for (Role r :  Role.values()) {
            if (r.getRoleName().equals(value)) {
                return r;
            }
        }
        return null;
        }
    }