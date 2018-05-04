import java.util.*;

interface Language {
	public float getVersion();
}

abstract class Java implements Language{
	protected float version;

	public float getVersion() {
		return version;
	}
}

enum TypeOfLanguage
{
  FUNCTIONAL, IMPERATIVE
}

class J15 extends Java{
    private static String name;
		private TypeOfLanguage type;

    public J15(String name){
				this.name = name;
				this.type = TypeOfLanguage.IMPERATIVE;
    }

    public int assertion(int x) {
        int a = 3;
    		a = a+5;
				assert ( a == 8 );
        return x * a;
    }

		private class InnerClass {
			private int value;
			public InnerClass(int x){
				this.value = x;
			}
		}

		public int loops(int... items) {
			int counter = 0;
			for (int i = 0; i <= 10; i++){
				++counter;
			}

			while (counter > 5){
				counter--;
				continue;
			}

			do {
				counter++;
			} while (counter < 10);
			--counter;

			counter+=1;
			counter-=1;
			counter*=1;
			counter/=1;

			ArrayList<Integer> m = new ArrayList<Integer>();

			for (int x : items){
				m.add(x);
			}


			return counter;
		}

		public void bitwise(){
			int a = 5;
			a = a^3;
			a = a|3;
			a = a&3;
			a = a << 3;

			double b = (double) a;
		}


		public boolean ifStatements(){

			char a = 'a';

			switch(a){
				case 'b':
					a = 'c';
					break;
				default:
					break;
			}



			InnerClass ic = null;
			ic = new InnerClass(3);

			if(false && false && ic != null){
				return true? false : true;
			} else if(true || false && a >= 3){
				return true;
			} else {
				return false;
			}
		}


		public void exceptionThrower() throws Exception{
			int [] a = new int[2];
			int [] b = {1, 2, 3};
			int [] c;
			try {
				a[4] = 4;
			} catch(IndexOutOfBoundsException e){
				throw new Exception("A chained Exception has been thrown.");
			}
		}

}
