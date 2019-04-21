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
	
	
	public int getFeedBack() {
		return numberOfFeedback;
	}
	
	public float getWeight() {
		return weight;
	}
	
	public int getNumOfReviews() {
		return numOfReviews;
	}
}
