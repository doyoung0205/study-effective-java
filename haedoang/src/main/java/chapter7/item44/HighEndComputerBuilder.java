package chapter7.item44;

/**
 * author : haedoang
 * date : 2022/05/02
 * description :
 */
public class HighEndComputerBuilder extends ComputerBuilder {

    @Override
    public void addProcessor() {
        computerParts.put("Processor", "High-end Processor");
    }

    @Override
    public void addMotherboard() {
        computerParts.put("Motherboard", "High-end Motherboard");
    }

    @Override
    public void setupMotherboard() {
        motherboardSetupStatus.add(
                "Screwing the high-end motherboard to the case.");
        motherboardSetupStatus.add(
                "Pluging in the power supply connectors.");
        motherboardSetupStatus.forEach(
                System.out::println);
    }
}
