package models.enums;

public enum SkillType {
    Single("Single"),
    Group("Group"),
    SingleSelf("Single self"),
    SingleOther("Single other"),
    GroupSelf("Group self"),
    ;
    private final String name;

    SkillType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
