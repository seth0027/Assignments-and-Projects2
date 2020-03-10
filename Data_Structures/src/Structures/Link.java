package Structures;

public class Link {

	 String bookname;
	 int numbersold;
	 Link next;
	 
	 Link(String name,int sale){
		 bookname=name;
		 numbersold=sale;
	 }
	 String display() {
		 return ("Book : "+bookname+" Sale in millions : "+numbersold );
	 }
	 @Override
	public  String toString() {
		 return bookname;
		 
	 }
}
	 
	