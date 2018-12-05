package code.pattern.impl;

import code.Patttern.CreditPointsFactory;


public class CreditPointsFactoryimpl {

		public int getCreditPoint(int activityTypeId){
			
			if(activityTypeId==1)
			{		
				return new activity1().point();	
			}
			else if(activityTypeId==2)
			{
				return new activity2().point();
				
			}else if(activityTypeId==3)
			{
				return new activity3().point();
				
			}else if(activityTypeId==4){
				
				return new activity4().point();
				
			}else{
				
				return new activity5().point();
				
			} 
		}
	
}
class activity1 extends CreditPointsFactory{
	public int point()
	{
		
		return 10;
	}
}
 class activity2 extends CreditPointsFactory{
	public int point()
	{
		return 5;
	}
} 
 class activity3 extends CreditPointsFactory{
	public int point()
	{
		return 15;
	}
}  
 class activity4 extends CreditPointsFactory{
	public int point()
	{
		return 8;
	}
}  
 class activity5 extends CreditPointsFactory{
	public int point()
	{
		return 20;
	}
}   
 