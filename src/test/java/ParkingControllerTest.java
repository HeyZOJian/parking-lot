import Controller.ParkingController;
import Expection.WrongReceiptException;
import Model.Car;
import Model.ParkingBoy;
import Model.ParkingLot;
import Model.Receipt;
import View.Request;
import View.Response;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.fail;
import static org.mockito.Mockito.*;

/**
 * Created by Vito Zhuang on 7/15/2018.
 */
public class ParkingControllerTest {
	private ParkingLot parkingLot;
	private ParkingBoy parkingBoy;
	private ParkingController parkingController;
	private Request request;
	private Response response;
	private Car car;
	private Receipt receipt;

}
