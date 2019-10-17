package Controller;

import java.util.LinkedList;
import java.util.List;

import Model.Hardware;

public class ControllerHardware {
	List<Hardware> listHw  = new LinkedList();
	public void addHardware(Hardware h) {
		listHw.add(h);
	}
	
	public Hardware buscarHardware(String tipo) {
		for (Hardware hardware : listHw) {
			if(hardware.getTipo().contains(tipo)) {
				return hardware;
			}
		}
		return null;
	}
	
}
