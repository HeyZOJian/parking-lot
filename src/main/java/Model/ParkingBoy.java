package Model;

import Exception.AllParkingLotFullException;
import Exception.ParkingLotNoEmptyException;
import Exception.ParkingLotNoExistException;
import Exception.WrongReceiptException;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Vito Zhuang on 7/12/2018.
 */
public class ParkingBoy {
    private List<ParkingLot> parkingLots;
    private int idRecord = 1;

    public ParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public Receipt park(Car car) {
        ParkingLot parkingLot = getParkingLotHaveRemainSpace();
        return parkingLot.park(car);
    }

    public Car unPark(String uuid) {
        for (ParkingLot parkingLot : parkingLots) {
            Car car = parkingLot.unPark(uuid);
            if (car != null)
                return car;
        }
        throw new WrongReceiptException();
    }


    public ParkingLot getParkingLotHaveRemainSpace() {
        for (ParkingLot parkingLot : parkingLots) {
            if (!parkingLot.isFull())
                return parkingLot;
        }
        throw new AllParkingLotFullException();
    }

    public boolean isAllParkingLotFull() {
        return parkingLots.stream()
                .allMatch(parkingLot -> !parkingLot.isFull());
    }

    public void addParkingLot(String name, int size) {
        parkingLots.add(new ParkingLot(idRecord, name, size));
    }

    public boolean deleteParkingLotById(String id) {
        ParkingLot parkingLot = getParkingLotById(id);
        if (parkingLot != null) {
            if (parkingLot.getParkNum() == 0) {
                return true;
            }
            throw new ParkingLotNoEmptyException();
        }
        throw new ParkingLotNoExistException();
    }

    private ParkingLot getParkingLotById(String id) {
        for(ParkingLot parkingLot:parkingLots){
            if(parkingLot.getId() == Integer.parseInt(id))
                return parkingLot;
        }
        return null;
    }

    public List<Map<String,String>> getParkingLotsInfo() {
        List<Map<String,String>> result = new LinkedList<>();
        for (ParkingLot parkingLot:parkingLots) {
            Map<String,String> map = new LinkedHashMap<>();
            map.put("id",String.format("%0" + 3 + "d", parkingLot.getId() ));
            map.put("name",parkingLot.getName());
            map.put("capacity",String.valueOf(parkingLot.getCapacity()));
            map.put("parkNum",String.valueOf(parkingLot.getParkNum()));
            map.put("remainNum",String.valueOf(parkingLot.getRemainNum()));
            result.add(map);
        }
        return result;
    }
}
