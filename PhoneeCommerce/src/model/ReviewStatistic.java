package model;

public class ReviewStatistic {
	
	private int numberOfFeedback;
	private float weight;
	private int numOfReviews;
	
	public ReviewStatistic(int f,float w, int num) {
		
		numberOfFeedback=f;
		weight=w;
		numOfReviews=num;
	}
	
	public void setFeedBack(int numOfFeed) {
		numberOfFeedback=numOfFeed;
	}
	
	public void setWeight(float w) {
		weight=w;
	}
	
	public void setNumOfReviews(int numOfRev) {
		numOfReviews=numOfRev;
	}
	
	public int getNumberOfFeedback() {
		return numberOfFeedback;
	}
	
	public float getWeight() {
		return weight;
	}
	
	public int getNumOfReviews() {
		return numOfReviews;
	}
}
