package View;

import Controller.ParkingController;
import Expection.ParkingLotFullException;
import Expection.WrongReceiptException;
import Model.Car;
import Model.ParkingBoy;
import Model.ParkingLot;
import Model.Receipt;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ParkingView {
    private ParkingLot parkingLot1;
    private ParkingLot parkingLot2;
    private List<ParkingLot> parkingLots;
    private ParkingBoy parkingBoy;
    private ParkingController parkingController;

    public ParkingView() {
        parkingLot1 = new ParkingLot(2);
        parkingLot2 = new ParkingLot(2);
        parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        parkingBoy = new ParkingBoy(parkingLots);
        parkingController = new ParkingController(parkingBoy);
    }

    public void inputCommand() {
        boolean flag = false;
        while (flag == false) {
            printIndex();
            Scanner scanner = new Scanner(System.in);
            int command = scanner.nextInt();
            while (!validate(command)) {
                System.out.println("非法指令，请查证后再输");
                command = scanner.nextInt();
            }
            execute(command);
        }

    }

    public void printIndex() {
        System.out.println("1. 停车\n" +
                "2. 取车 \n" +
                "请输入您要进行的操作：");
    }

    private boolean validate(int command) {
        if (command == 1 || command == 2) {
            return true;
        }
        return false;
    }

    public void execute(int command) {
        try {
            if (command == 1) {
                park();
            } else {
                unPark();
            }
        } catch (ParkingLotFullException e1) {
            System.out.println("车已停满，请晚点再来");

        } catch (WrongReceiptException e2) {
            System.out.println("非法小票，无法取出车，请查证后再输");

        }
    }

    public void park() {
        System.out.println("请输入车牌号:");
        Scanner scanner = new Scanner(System.in);
        String plateNumber = scanner.next();
        Receipt receipt = parkingController.park(plateNumber);
        System.out.println("停车成功，您的小票是：\n" + receipt.getUuid());
    }

    public void unPark() {
        System.out.println("请输入小票编号：");
        Scanner scanner = new Scanner(System.in);
        String uuid = scanner.next();
        Car car = parkingController.unPark(uuid);
        System.out.println("车已取出，您的车牌号是：" + car.getPlateNumber());
    }
}
