package entity;

public class Criteria {
    private String fieldName;
    private String value;
    private String operator;
    private boolean orderBy;
    private boolean asc;
    private String logic;

    public Criteria(String fieldName, String value, String operator, boolean orderBy, boolean asc, String logic) {
        this.fieldName = fieldName;
        this.value = value;
        this.operator = operator;
        this.orderBy = orderBy;
        this.asc = asc;
        this.logic = logic;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getValue() {
        return value;
    }

    public String getOperator() {
        return operator;
    }

    public boolean isOrderBy() {
        return orderBy;
    }

    public boolean isAsc() {
        return asc;
    }

    public String getLogic() {
        return logic;
    }
}
