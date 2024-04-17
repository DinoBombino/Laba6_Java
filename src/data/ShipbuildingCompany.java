package data;

public class ShipbuildingCompany extends Organization {
    private String type_of_benefit;
    private int benefit;
    public ShipbuildingCompany(String name, int id, String type_of_benefit) {
        super(name, id);
        this.type_of_benefit = type_of_benefit;
    }
    public void setBenefit(int benefit) {
        this.benefit = benefit;
    }
    public String getBenefit() {
        return type_of_benefit;
    }

    @Override
    public void sail() {
        System.out.println("Судостроительная готовит новый тип кораблей");
    };
}