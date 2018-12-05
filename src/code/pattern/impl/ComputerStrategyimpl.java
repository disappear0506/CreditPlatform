package code.pattern.impl;

import code.Patttern.ComputerStrategy;


public class ComputerStrategyimpl{
	
	private ComputerStrategy computerstrategy;
	public void setComputerStrategy(ComputerStrategy computerstrategy)
	{
		this.computerstrategy = computerstrategy;
	}
	public double algorithm(double score,int weight)
	{
		return computerstrategy.computer(score,weight);
	}
	public void select(int i)
	{
		if(i==1)  
		{
			ComputerStrategy computerstrategy1 = new ComputerStrategy1();
			setComputerStrategy(computerstrategy1);
		}
		if(i==2)  
		{
			ComputerStrategy computerstrategy2 = new ComputerStrategy2();
			setComputerStrategy(computerstrategy2);
		}
		if(i==3)  
		{
			ComputerStrategy computerstrategy3 = new ComputerStrategy2();
			setComputerStrategy(computerstrategy3);
		}
		
	}
	
}


 class ComputerStrategy1 extends ComputerStrategy{// score<5
	public double computer(double score,int weight)
	{
		 score-= score/10.0*weight;
		 return score;
	}
	
}
 class ComputerStrategy2 extends ComputerStrategy{// score=5
		public double computer(double score,int weight)
		{
			 score+= (score-5)/10.0*weight;
			 return score;
		}	
} 
 class ComputerStrategy3 extends ComputerStrategy{// score>5
		public double computer(double score,int weight)
		{
			 score+=score/10.0*weight;
			 return score;
		}	
} 
 
 
 
 
 



