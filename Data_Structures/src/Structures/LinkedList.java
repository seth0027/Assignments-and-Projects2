package Structures;

class LinkedList{
	Link firstlink;
	
	LinkedList(){
		firstlink=null;
	}
	
	void insertlink(String name, int sale) {
		Link l =new Link(name,sale);
		l.next=firstlink;
		firstlink=l;
		
		
		
	}
	Link remove() {
		System.out.println("Removing "+firstlink);
		Link ref=firstlink;
		firstlink=firstlink.next;
		return ref;
	}
	
	void displaylist() {
		if(firstlink!=null) {
			Link ref=firstlink;
			while(ref!=null) {
				System.out.println(ref.display()+"\nNext Link is "+ref.next);
				System.out.println();
				ref=ref.next;
			}
		}
	}
	
	Link removelink(String name) {
		Link ref1=firstlink;
		Link ref=firstlink;
		if(firstlink!=null) {
			
			if(ref.bookname.equals(name)) {
				firstlink=firstlink.next;
				System.out.println("Removing ref "+ref+" with value "+name);
			}
			else {
			while(ref!=null) {
				if(ref.bookname.equals(name)) {
					
					ref1.next=ref.next;
					System.out.println("REmoving ref "+ref+" With name "+name);
					
					
				}
				ref1=ref;
				ref=ref.next;
			}
		
			}
		}
		return ref;
	}
	
	void displaylink(String name) {
		
		if(firstlink!=null) {
			Link ref=firstlink;
			while(ref!=null) {
				if(ref.bookname.equals(name)) {
					
					System.out.println("Found the link at "+ref+" and next link is "+ref.next);
				}
				ref=ref.next;
			}
			
		}
		
	}
	public static void main(String[] args) {
		LinkedList l=new LinkedList();
		l.insertlink("HArry puutar", 312);
		l.insertlink("john", 2);
		l.insertlink("megan", 50);
		l.insertlink("HAHHA", 12);
		l.insertlink("Fred", 1);
		
		l.displaylist();
		
	   //l.displaylink("john");
	   l.removelink("HAHHA");
	   l.displaylist();
	   
		
	}
	
	
 }
 



