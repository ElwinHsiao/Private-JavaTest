package test.main;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxyStudy {

	public DynamicProxyStudy() {
		// TODO Auto-generated constructor stub
	}
	

}

//		int youMoney = 10;
//		Peldar peldar = new Butcher();    						// find a peldar.
//		int meat = peldar.buyMeat(youMoney);
//		System.out.println("Cook the meat, weight: " + meat);		// you cooked it.	
class You {
	public void work() {
		int youMoney = 10;
		MachineConfig machineConfig = new MachineConfig();
		Peldar peldar = (Peldar) Proxy.newProxyInstance(Farmer.class.getClassLoader(), Peldar.class.getInterfaces(), machineConfig);
		peldar.buyMeat(youMoney);
	}
}

class MachineConfig implements InvocationHandler {
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
//		method.invoke(arg0, arg1);
		Peldar.class.getInterfaces();
		
		return null;
	}
	
}

interface Peldar {
	int buyMeat(int money);
}

class Farmer implements Peldar {
	@Override
	public int buyMeat(int money) {
		int meat = 0;
		// ... meat = ***;
		return meat;
	}
}

class Butcher implements Peldar {
	@Override
	public int buyMeat(int money) {
		Farmer farmer = new Farmer();		// 1.find a farmer.
		int meat = farmer.buyMeat(money);		// 2.buy meat from the farmer.
		meat += 5;							// 3.inject 5 pound water into the meat, so weight will increase. 
		return meat;						// 4.return to you.
	}
}
