package Structures;

public class Qeues {
	
	
	  int rear;
	  int size;
	 String [] names;
	
	Qeues(int size){
		this.size=size;
		names= new String[size];
	}
	void insert(String name) {
		if(rear<size) {
			names[rear]=name;
			rear++;
			System.out.println("Added "+name+" to the qeue at "+(rear-1));
		}
		else {
			System.out.println("Sorry qeue is full !");
		}
	}
	 String remove() {
		 if(rear>0) {
			 
			 String removed=names[0];
			 for(int i=1;i<rear;i++) {
				 names[i-1]=names[i];
			 }
			   --rear;
			 System.out.println(removed+" removed from front");
			 return removed;
		 }
		 else 
			 System.out.println("Qeue is empty !");
			 return null;
	 }
	 String peek() {
		 if(rear>=0) {
		 System.out.println("Peeked at "+ names[0]);
		 return names[0];}
		 else 
			 System.out.println("Que is empty ");
		 return null;
	 }
	 void display() {
		 for (int i=0;i<rear;i++) {
			 System.out.println(names[i]);
		 }
	 }
     void pinsert(String name) {
    	 if(rear<0) {
    		 insert(name);
    	 }
    	 else {
    		 for(int i =size-1;i>=0;i--) {
    			 if(Integer.parseInt(name)>Integer.parseInt(names[i])) {
    				 names[i+1]=names[i];
    				 
    			 }
    			 
    			 
    		 }
    		 rear++;
    		 names[0]=name;
    	 }
     }
	public static void main(String[] args) {
		Qeues q= new Qeues(10);
		
		q.pinsert("32");
		q.pinsert("43");
		q.pinsert("65");
		q.pinsert("76");
		q.pinsert("90");
		
	}

}
