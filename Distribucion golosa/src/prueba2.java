import java.util.HashSet;
import java.util.Set;

public class prueba2 {

	public static void main(String[] args) {
		Set<Integer> set = new HashSet<>();
//		set.add(5);
//		set.add(4);
//		set.add(3);
		System.out.println(set);

	}
	
	public static int menorElemento(Set<Integer> set)
	{
		if( set == null )
			throw new IllegalArgumentException();
		int ret = Integer.MAX_VALUE;
		for(Integer x: set)
			ret = Math.min(ret, x);
		return ret;
	}
}
