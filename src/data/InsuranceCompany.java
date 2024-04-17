package data;

public class InsuranceCompany extends Organization {
    private String benefit;
    
    public InsuranceCompany(String name, int id, String benefit) {
        super(name, id);
        this.benefit = benefit;
    }

    public String getBenefit() {
        return benefit;
    }

    public void setBenefit(String benefit) {
        this.benefit = benefit;
    }

    @Override
    public void sail() {
        System.out.println("Страховая страхует даже не страхуемое");
    }
}